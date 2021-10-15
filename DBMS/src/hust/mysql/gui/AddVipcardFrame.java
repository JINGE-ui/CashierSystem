package hust.mysql.gui;

import hust.mysql.bean.Goods;
import hust.mysql.bean.Vip;
import hust.mysql.service.GoodsService;
import hust.mysql.service.GoodsServiceImp;
import hust.mysql.service.VipService;
import hust.mysql.service.VipServiceImp;
import hust.mysql.utils.DateTimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddVipcardFrame extends JFrame implements ActionListener {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CashierManagerFrame cashiermanagerframe;
    private JPanel mainPanel;
    private JTextField vidContent;
    private JLabel vipid;
    private JButton addButton,cancelButton;

    Font f = new Font("楷体", Font.BOLD, 15);


    // Service 层
    private VipService vipservice = new VipServiceImp();

    public AddVipcardFrame(CashierManagerFrame cashiermanagerframe){
        this.cashiermanagerframe = cashiermanagerframe;
        init();
        setTitle("使用会员卡");
        setSize(320,200);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void init(){
        mainPanel = new JPanel(new BorderLayout());
        JPanel jp0 = new JPanel();
        JLabel jl0 = new JLabel(" ");
        jp0.add(jl0);

        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        vipid = new JLabel("会员卡号");
        vipid.setFont(f);
        vidContent = new JTextField(20);
        vidContent.setFont(f);
        jp1.add(vipid);
        jp1.add(vidContent);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        addButton = new JButton("添加");
        addButton.addActionListener(this);
        cancelButton = new JButton("取消");
        cancelButton.addActionListener(this);

        jPanel.add(addButton);
        jPanel.add(cancelButton);

        mainPanel.add(jp0,BorderLayout.NORTH);
        mainPanel.add(jp1,BorderLayout.CENTER);
        mainPanel.add(jPanel,BorderLayout.SOUTH);

        add(mainPanel);
    }


    /**
     * 实现 ActionListener 中的方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){

            if(vidContent.getText().equals("")){
                JOptionPane.showMessageDialog(null,"请输入会员卡号!");
                return;
            }
            String vid = vidContent.getText();
            Vip vip = vipservice.searchVipbyId(vid);
            if(vip==null){
                JOptionPane.showMessageDialog(null,"会员卡号无效!");
                return;
            }
            if(vip.getE_time().before(DateTimeUtil.getTime())) {   //过期了
                Double cost = vip.getCost();
                Timestamp etime = vip.getE_time();
                boolean IsVaild = false;
                while(cost >= 5000.0){
                    Calendar c = Calendar.getInstance();
                    c.setTime(etime);
                    c.add(Calendar.YEAR,1);
                    Timestamp new_etime = new Timestamp(c.getTimeInMillis());   //加一年

                    if(DateTimeUtil.getTime().before(new_etime)){    //恢复时效
                        IsVaild = true;
                        Vip new_vip = new Vip(vip.getV_id(),vip.getV_name(),vip.getTelephone(),
                                new_etime,cost-5000.0);
                        vipservice.updateVip(new_vip);
                        //重置会员卡 累计金额和到期时间 后 提醒
                        JDialog hint = new JDialog();
                        hint.setTitle("会员卡期限重置");
                        JPanel JP = new JPanel(new GridLayout(6,1));
                        JP.add(new JLabel("过期会员卡信息："));
                        JP.add(new JLabel("累计金额："+vip.getCost()));
                        JP.add(new JLabel("到期时间"+df.format(vip.getE_time())));
                        JP.add(new JLabel("重置后会员卡信息："));
                        JP.add(new JLabel("累计金额："+new_vip.getCost()));
                        JP.add(new JLabel("到期时间："+new_vip.getE_time()));
                        hint.add(JP);
                        hint.setSize(250,200);
                        hint.setVisible(true);

                    }
                    cost = cost - 5000.0;
                    etime = new_etime;
                }
                if(!IsVaild) {
                    JOptionPane.showMessageDialog(null, "会员卡号已过期!");
                    return;
                }
            }
            //没有过期
            this.cashiermanagerframe.setVipUseContent(vid);
            this.cashiermanagerframe.setCumuamount(vip.getCost());
            this.cashiermanagerframe.setUntiltime(vip.getE_time());
            this.cashiermanagerframe.setVip(vip);
            dispose();                                                         // 关闭当前窗体
        }
        if(e.getSource()==cancelButton){
            dispose();
        }
    }


}


