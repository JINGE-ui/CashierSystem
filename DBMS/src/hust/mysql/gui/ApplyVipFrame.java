package hust.mysql.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import hust.mysql.bean.*;
import hust.mysql.service.*;
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
import java.util.Date;


public class ApplyVipFrame extends JFrame implements MouseListener,ActionListener {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CashierManagerFrame cashiermanagerframe;
    private JPanel mainPanel;
    private JTextField vname,telephone;
    private JButton addButton,cancelButton;
    private boolean IsFree = false;
    private User admin;
    private OrderService orderService = new OrderServiceImp();
    private OrderInfoService orderInfoService = new OrderInfoServiceImp();

    private Dialog vipDialog;
    private JPanel vipPanel;

    Font f = new Font("楷体", Font.BOLD, 15);


    // Service 层
    private VipService vipservice = new VipServiceImp();

    public ApplyVipFrame(CashierManagerFrame cashiermanagerframe,boolean IsFree,String titil){
        this.cashiermanagerframe = cashiermanagerframe;
        this.IsFree = IsFree;
        this.admin = (User) cashiermanagerframe.map.get("employee");
        init();
        setTitle(titil);
        setSize(320,200);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void init(){
        mainPanel = new JPanel(new GridLayout(3,1,0,20));

        vname = new JTextField();
        telephone = new JTextField();

        vname.addMouseListener(this);
        telephone.addMouseListener(this);

        vname.setText("姓名(非必填)");
        telephone.setText("电话号码(必填)");

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        addButton = new JButton("添加");
        addButton.addActionListener(this);
        cancelButton = new JButton("取消");
        cancelButton.addActionListener(this);

        jPanel.add(addButton);
        jPanel.add(cancelButton);

        mainPanel.add(vname);
        mainPanel.add(telephone);
        mainPanel.add(jPanel);

        add(mainPanel);
    }


    /**
     * 实现 ActionListener 中的方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){

            if(telephone.getText().equals("") || telephone.getText().equals("电话号码(必填)")){
                JOptionPane.showMessageDialog(null,"请输入电话号码!");
                return;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String vid = "v"+simpleDateFormat.format(new Date());

            Timestamp stime = new Timestamp(System.currentTimeMillis());   //办理时间
            Calendar c = Calendar.getInstance();
            c.setTime(stime);
            c.add(Calendar.YEAR,1);   //  加一  年
            Timestamp etime = new Timestamp(c.getTimeInMillis());
            System.out.println("到期时间"+etime);

            if(vname.getText().equals("") || vname.getText().equals("姓名(非必填)")){
                Vip vip = new Vip(vid,null,telephone.getText(),etime,0.0);
                vipservice.insertVip(vip);
            }else{
                Vip vip = new Vip(vid,vname.getText(),telephone.getText(),etime,0.0);
                vipservice.insertVip(vip);
            }

            //打印会员卡办理的小票
            String orderid = simpleDateFormat.format(new Date());   //订单编号
            vipDialog = new JDialog();
            vipDialog.setTitle("办理成功！");
            vipDialog.setSize(new Dimension(300,250));
            vipDialog.setResizable(false);
            JPanel info1 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
            JPanel info2 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
            JPanel info3 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
            JPanel info4 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));

            JLabel orderidjl = new JLabel("账单编号:");
            JLabel orderidcon = new JLabel("b"+orderid);
            info4.add(orderidjl);
            info4.add(orderidcon);

            JLabel vipid = new JLabel("所得会员卡号:");
            JLabel vipcontent = new JLabel(vid);
            info1.add(vipid);
            info1.add(vipcontent);

            JLabel etimejl = new JLabel("到期时间:");
            JLabel etimecon = new JLabel(df.format(etime));
            info2.add(etimejl);
            info2.add(etimecon);

            JLabel amountjl = new JLabel("收款:");
            JLabel amountcon = new JLabel("50.0");
            info3.add(amountjl);
            info3.add(amountcon);
            vipPanel = new JPanel(new GridLayout(4,1));
            vipPanel.add(info1);
            vipPanel.add(info2);
            if(!IsFree){    //会员卡不是免费发放的,收款并记录
                Timestamp vtime = stime;    //办理会员卡的时间即收款时间
                Order order = new Order("b"+orderid,admin.getCid(),50.0,50.0,vtime);
                System.out.println(order);
                orderService.addOrder(order);
                OrderInfo orderInfo = new OrderInfo("v000","b"+orderid,1);
                orderInfoService.updateOrderInfo(orderInfo);    //更新sells表

                vipPanel.add(info4);
                vipPanel.add(info3);
            }
            vipDialog.add(vipPanel);
            vipDialog.setVisible(true);

            dispose();                                                         // 关闭当前窗体
        }
        if(e.getSource()==cancelButton){
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vname){
            vname.setText("");
        }
        if (e.getSource() == telephone){
            telephone.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

