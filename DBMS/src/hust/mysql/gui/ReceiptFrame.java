package hust.mysql.gui;

import hust.mysql.bean.Vip;
import hust.mysql.service.GoodsService;
import hust.mysql.service.GoodsServiceImp;
import hust.mysql.service.VipService;
import hust.mysql.service.VipServiceImp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReceiptFrame extends JFrame {
    private JPanel mainPanel,ReceiptPanel,north_info,south_info,vipPanle;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 自定义变量
    private Integer rowCount;
    private DefaultTableModel dtm;
    private Double total;
    private String lid;
    private Timestamp etime;
    private String vid=null;
    private boolean Isdiscount=false;
    private Double real_total;

    private GoodsService goodsService = new GoodsServiceImp();
    private VipService vipService = new VipServiceImp();

    public ReceiptFrame(int rowCount, DefaultTableModel dtm, Double total, String lid, Timestamp etime, String vid){
        this.total = total;
        this.rowCount = rowCount;
        this.dtm = dtm;
        this.lid = lid;
        this.etime = etime;
        if(vid!=null){
            this.vid = vid;
            Isdiscount = true;
            real_total = total*0.9;
        }else{
            real_total = total;
        }
        init();
        //setLayout(null);
        setTitle("超市小票");
        setSize(380,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init(){
        ReceiptPanel = new JPanel(new BorderLayout());

        north_info = new JPanel(new GridLayout(2,1));
        JPanel info1 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        JPanel info2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        info1.add(new JLabel("账单编号："));
        info1.add(new JLabel(lid));
        info2.add(new JLabel("时间："));
        info2.add(new JLabel(df.format(etime)));
        north_info.add(info1);
        north_info.add(info2);

        mainPanel = new JPanel(new GridLayout((rowCount + 4),1,5,5));
        mainPanel.add(north_info);

        JPanel info3 = new JPanel(new GridLayout(1,3));
        info3.add(new JLabel("商品名"));
        info3.add(new JLabel("单价"));
        info3.add(new JLabel("数目"));
        mainPanel.add(info3);

        JPanel[] jPanels = new JPanel[rowCount];
        JLabel[] gids = new JLabel[rowCount];
        JLabel[] gname = new JLabel[rowCount];
        JLabel[] gamount = new JLabel[rowCount];
        JLabel[] gprice = new JLabel[rowCount];

        for (int i = 0; i < (rowCount); i++) {

            jPanels[i] = new JPanel(new GridLayout(1,3,0,0));

            String id = (String) dtm.getValueAt(i,1);
            gids[i] = new JLabel();
            gids[i].setText(id);

            String name = (String) dtm.getValueAt(i,2);
            gname[i] = new JLabel();
            gname[i].setText(name);

            int num = (Integer) dtm.getValueAt(i,3);
            gamount[i] = new JLabel();
            gamount[i].setText(num+"");

            double price = (Double) dtm.getValueAt(i,4);
            gprice[i] = new JLabel();
            gprice[i].setText(price+"");

            //jPanels[i].add(gids[i]);
            jPanels[i].add(gname[i]);
            jPanels[i].add(gprice[i]);
            jPanels[i].add(gamount[i]);

            mainPanel.add(jPanels[i]);
        }
        //south_panel add in mainPanle
        south_info = new JPanel(new GridLayout(3,1));
        JPanel s_info1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        s_info1.add(new JLabel("总计："));
        s_info1.add(new JLabel(total+""));

        JPanel s_info2 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        s_info2.add(new JLabel("折扣："));
        if(Isdiscount){
            s_info2.add(new JLabel("9折"));
        }else{
            s_info2.add(new JLabel("无"));
        }
        JPanel s_info3 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        s_info3.add(new JLabel("实收金额："));
        s_info3.add(new JLabel(real_total+""));
        south_info.add(s_info1);
        south_info.add(s_info2);
        south_info.add(s_info3);

        mainPanel.add(south_info);

        //使用了会员卡才会显示：
        if(Isdiscount){
            vipPanle = new JPanel(new GridLayout(4,1));
            Vip vip = vipService.searchVipbyId(vid);
            JPanel vipinfo = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
            vipinfo.add(new JLabel("---会员卡信息---"));

            JPanel vipinfo1 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
            vipinfo1.add(new JLabel("会员卡号:"));
            vipinfo1.add(new JLabel(vid));

            JPanel vipinfo2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
            vipinfo2.add(new JLabel("累计金额:"));
            vipinfo2.add(new JLabel(vip.getCost()+""));

            JPanel vipinfo3 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
            vipinfo3.add(new JLabel("到期时间:"));
            vipinfo3.add(new JLabel(df.format(vip.getE_time())));
            vipPanle.add(vipinfo);
            vipPanle.add(vipinfo1);
            vipPanle.add(vipinfo2);
            vipPanle.add(vipinfo3);
            mainPanel.add(vipPanle,BorderLayout.SOUTH);
        }
        //north_info and south_info are added to mainpanel(表格布局)
        //mainpanel is added to ReceiptPanel(BorderLayout.NORTH)
        //能力有限，不知道怎么更好地布局
        ReceiptPanel.add(mainPanel,BorderLayout.NORTH);

        add(ReceiptPanel);
    }

}

