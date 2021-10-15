package hust.mysql.gui;

import hust.mysql.bean.*;
import hust.mysql.service.*;
import hust.mysql.utils.ClockThread;
import hust.mysql.utils.DateTimeUtil;
import org.jfree.data.xy.YisSymbolic;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashierManagerFrame extends JFrame implements ActionListener {

    Font d = new Font("楷体", Font.BOLD, 22);
    Font f = new Font("楷体", Font.BOLD, 15);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String orderid = simpleDateFormat.format(new Date());
    private Double info_totalcontent = 0.0;

    private CardLayout cardLayout;
    private JPanel mainPanel,cardPanel,buttonPanel,infoPanel,goodsManagerPanel,cashManagerPanel;
    private JTable goodsTable,jTable;              // 商品表  用户表
    private DefaultTableModel goodsModel,dtm;   // 商品表模型  用户表模型
    private JScrollPane goodsScrolPane,cashScrolPane;
    private JButton cashManagerButton,goodsManagerButton,exitButton;

    //商品页面
    private JPanel g_SouthPanel,g_NounthPanel;
    private JButton g_AddButton,g_DeleteButton,g_UpdateButton,g_SearchButton,g_SearchAllButton;//typeManagerButton;
    private JTextField g_SearchFiled;
    private DefaultListSelectionModel g_SelectionModel;     // 商品管理面板 表格 选择模型

    //收银页面
    private DefaultListSelectionModel selectionModel;
    private JPanel c_SouthPanel;
    private JLabel goods_id = new JLabel("商品货号");
    private JLabel goods_num = new JLabel("商品数目");
    private JLabel vip_id = new JLabel("会员卡号");
    private JTextField goods_idField = new JTextField(15);
    private JTextField goods_numField = new JTextField(15);
    private JTextField vip_idField = new JTextField(15);
    private JButton pay;
    private JButton apply_vip;
    private JButton add_goods;
    private JButton add_vip;

    private JPanel goodsIdInput,goodsNumInput,vipIdInput;
    private JPanel addJP,vipJP,payJP,addvipJP;

    //商品信息显示
    private JPanel goodsInfoPanel;
    private JLabel totalcost = new JLabel("总计");
    private JLabel totalcostcontent;
    private JLabel vipuse = new JLabel("会员卡号");
    private String vipuseid = null;
    private JLabel vipusecontent;
    private JLabel untiltime = new JLabel("到期时间");
    private JLabel untiltimecontent;
    private Timestamp vipetime;   //到期时间
    private JLabel cumuamount = new JLabel("累计金额");
    private JLabel cumuamountcontent;
    private Double cumutotalamount;
    private JLabel discount = new JLabel("折扣");
    boolean IsDiscount = false;
    private JLabel discountcontent;
    private JPanel totalcostJP,vipuseJP,discountJP,untiltimeJP,cumuamountJP;

    //my valueables
    public Map map = null;
    private ClockThread clockThread = null;               // 系统时间线程
    private JLabel timeLabel;
    private List<Goods> goodss = null;
    private Vip vip=null;       //使用的会员卡

    //service
    private GoodsService goodsService = new GoodsServiceImp();
    private OrderService orderService = new OrderServiceImp();
    private OrderInfoService orderInfoService = new OrderInfoServiceImp();
    private VipService vipService = new VipServiceImp();

    /**
     * 构造方法
     */
    public CashierManagerFrame(Map map){
        this.map = map;
        init();
        setTitle("超市收银系统 No.1");
        setSize(1000,640);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (clockThread != null){
                    clockThread.stop();                     // 关闭时间线程
                }
                System.exit(1);
            }
        });
    }
    /**
     * 界面初始化
     */
    public void init(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(50,50,58));

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(getCashManagerPanel(),"casheeManagerPanel");  //收银页面
        cardPanel.add(getGoodsManagerPanel(),"goodsManagerPanel");  //库存商品页面

        mainPanel.add(getButtonPanel(),BorderLayout.NORTH);
        mainPanel.add(getInfoPanel(),BorderLayout.SOUTH);   //页面底部的时间等
        mainPanel.add(cardPanel,BorderLayout.CENTER);
        add(mainPanel);
    }

    public JPanel getButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        buttonPanel.setBackground(new Color(242,242,242));

        cashManagerButton = new JButton("收银");
        goodsManagerButton = new JButton("商品信息");
        exitButton = new JButton("登出");

        goodsManagerButton.addActionListener(this);
        cashManagerButton.addActionListener(this);
        exitButton.addActionListener(this);

        buttonPanel.add(cashManagerButton);
        //buttonPanel.add(goodsManagerButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }
    public JPanel getInfoPanel(){
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(242,242,242));

        User admin = (User) map.get("employee");                      // 获取登录时  传递的参数
        JPanel infoOne = new JPanel();
        infoOne.setBackground(new Color(242,242,242));
        JLabel jLabel = new JLabel();
        //jLabel.setText("欢迎登录!\t      ");
        jLabel.setText("欢迎登录!\t      "+admin.getCname());
//        jLabel.setForeground(new Color(200,200,200));
        infoOne.add(jLabel);

        JPanel infoTwo = new JPanel();
        infoTwo.setBackground(new Color(242,242,242));
        timeLabel = new JLabel();
//        timeLabel.setForeground(new Color(200,200,200));
        infoTwo.add(timeLabel);

        clockThread = new ClockThread();
        clockThread.setjLabel(timeLabel);
        clockThread.start();

        infoPanel.add(infoOne,BorderLayout.WEST);
        infoPanel.add(infoTwo,BorderLayout.EAST);
        return infoPanel;
    }

    public JPanel getGoodsManagerPanel(){
        goodsManagerPanel = new JPanel();
        goodsManagerPanel.setBackground(new Color(242,242,242));
        goodss = goodsService.getAll();                                // 获取 数据库 所有商品

        goodsScrolPane = new JScrollPane(getGoodsTable(goodss));
        goodsScrolPane.setPreferredSize(new Dimension(950,450));            // 设置滚动面板大小

        g_NounthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));     // 查询 商品面板
        g_NounthPanel.setBackground(new Color(242,242,242));
        g_SearchFiled = new JTextField(60);
        g_SearchFiled.setText("请输入需要查询商品名称");                                    // 此处未注册 鼠标监听
        g_SearchFiled.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                g_SearchFiled.setText("");
            }
        });
        g_SearchButton = new JButton("查询");
        g_SearchButton.addActionListener(this);
        g_NounthPanel.add(g_SearchFiled);
        g_NounthPanel.add(g_SearchButton);

        goodsManagerPanel.add(g_NounthPanel,BorderLayout.NORTH);
        goodsManagerPanel.add(goodsScrolPane,BorderLayout.CENTER);
        return goodsManagerPanel;
    }
    public JTable getGoodsTable(List<Goods> goodss){

        String[] header = {"商品货号","商品名称","商品单价","商品数量"};    // 表头
        Object[][] objects = new Object[goodss.size()][];                                       // 表数据
        int i = 0;
        for (Goods g:goodss
        ) {
            Object[] obj = new Object[]{g.getG_id(),g.getG_name(),g.getPrice(),g.getAmount()};
            objects[i] = obj;
            i++;
        }

        goodsModel = new DefaultTableModel(objects,header);
        goodsTable = new JTable(goodsModel){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }
        };
        //        goodsModel.isCellEditable(0,0);                                 // 设置 某个单元格不可编辑
        //        goodsTable.setEnabled(false);                                    // 并不能实现 预期效果

        g_SelectionModel = new DefaultListSelectionModel();            // 初始化选择模型
        //g_SelectionModel.addListSelectionListener(this);            // 注册监听
        goodsTable.setSelectionModel(g_SelectionModel);                // 将模型 添加 到表格中

        return goodsTable;
    }
    public void setCashTable(){  //将收银表格重置为空
        String[] tableHeader = {"订单编号","商品货号","商品名称","数量","单价","时间"};
        Object[][] objects = new Object[][]{};
        dtm = new DefaultTableModel(objects,tableHeader);
        jTable = new JTable(dtm){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }               // 设置表格不可编辑
        };
        selectionModel = new DefaultListSelectionModel();
        //selectionModel.addListSelectionListener(this);
        jTable.setSelectionModel(selectionModel);
        cashScrolPane.setViewportView(jTable);//装载表格
        cashScrolPane.setColumnHeaderView(jTable.getTableHeader());//设置头部（HeaderView部分）

    }
    public JPanel getCashManagerPanel(){
        cashManagerPanel = new JPanel();
        cashManagerPanel.setBackground(new Color(242,242,242));

        cashScrolPane = new JScrollPane();
        cashScrolPane.setPreferredSize(new Dimension(950,400));            // 设置滚动面板大小

        String[] tableHeader = {"订单编号","商品货号","商品名称","数量","单价","时间"};
        Object[][] objects = new Object[][]{};
        dtm = new DefaultTableModel(objects,tableHeader);
        jTable = new JTable(dtm){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }               // 设置表格不可编辑
        };
        selectionModel = new DefaultListSelectionModel();
        //selectionModel.addListSelectionListener(this);
        jTable.setSelectionModel(selectionModel);
        cashScrolPane.setViewportView(jTable);//装载表格
        cashScrolPane.setColumnHeaderView(jTable.getTableHeader());//设置头部（HeaderView部分）

        //收银页面下方 输入面板
        c_SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        c_SouthPanel.setBackground(new Color(242,242,242));
        c_SouthPanel.setPreferredSize(new Dimension(950,100));


        pay = new JButton("结账");
        apply_vip = new JButton("办理会员卡");
        add_goods = new JButton("添加");
        add_vip = new JButton("使用会员卡");
        pay.addActionListener(this);
        apply_vip.addActionListener(this);
        add_goods.addActionListener(this);
        add_vip.addActionListener(this);
        add_goods.setFont(d);
        apply_vip.setFont(d);
        pay.setFont(d);
        add_vip.setFont(d);

        addJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addJP.setPreferredSize(new Dimension(80,40));
        addJP.add(add_goods);

        vipJP = new JPanel();
        vipJP.setPreferredSize(new Dimension(220,40));
        vipJP.add(apply_vip);

        payJP = new JPanel();
        payJP.setPreferredSize(new Dimension(80,40));
        payJP.add(pay);

        addvipJP = new JPanel();
        addvipJP.setPreferredSize(new Dimension(220,40));
        addvipJP.add(add_vip);

        c_SouthPanel.setLayout(new GridLayout(2,3));
        c_SouthPanel.add(getGoodsIdInput());
        c_SouthPanel.add(getGoodsNumInput());
        c_SouthPanel.add(addJP);
        c_SouthPanel.add(addvipJP);
        c_SouthPanel.add(vipJP);
        c_SouthPanel.add(payJP);

        cashManagerPanel.add(cashScrolPane,BorderLayout.NORTH);
        cashManagerPanel.add(getGoodsInfoPanel(),BorderLayout.CENTER);
        cashManagerPanel.add(c_SouthPanel,BorderLayout.SOUTH);

        return cashManagerPanel;

    }
    public JPanel getGoodsInfoPanel(){
        goodsInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        goodsInfoPanel.setBackground(new Color(242,242,242));
        goodsInfoPanel.setPreferredSize(new Dimension(950,30));
        goodsInfoPanel.setLayout(new GridLayout(1,4));

        totalcostcontent = new JLabel();
        vipusecontent = new JLabel();
        discountcontent = new JLabel();
        untiltimecontent = new JLabel();
        cumuamountcontent = new JLabel();


        vipuseJP = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        vipuse.setFont(f);
        vipusecontent.setText("无");
        vipusecontent.setFont(f);
        vipuseJP.add(vipuse);
        vipuseJP.add(vipusecontent);

        discountJP = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        discount.setFont(f);
        discountcontent.setText("无");
        discountcontent.setFont(f);
        discountJP.add(discount);
        discountJP.add(discountcontent);

        untiltimeJP = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        untiltime.setFont(f);
        untiltimecontent.setText("");
        untiltimecontent.setFont(f);
        untiltimeJP.add(untiltime);
        untiltimeJP.add(untiltimecontent);

        cumuamountJP = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        cumuamount.setFont(f);
        cumuamountcontent.setText("");
        cumuamountcontent.setFont(f);
        cumuamountJP.add(cumuamount);
        cumuamountJP.add(cumuamountcontent);

        totalcostJP = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        totalcost.setFont(f);
        totalcostcontent.setText("0.0");
        totalcostcontent.setFont(f);
        totalcostJP.add(totalcost);
        totalcostJP.add(totalcostcontent);

        goodsInfoPanel.add(vipuseJP);
        goodsInfoPanel.add(untiltimeJP);
        goodsInfoPanel.add(cumuamountJP);
        goodsInfoPanel.add(totalcostJP);
        return goodsInfoPanel;
    }
    public JPanel getGoodsIdInput(){
        goodsIdInput = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        goods_id.setFont(d);
        goods_idField.setFont(f);
        goodsIdInput.add(goods_id);
        goodsIdInput.add(goods_idField);
        return goodsIdInput;
    }
    public JPanel getGoodsNumInput(){
        goodsNumInput = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        goods_num.setFont(d);
        goods_numField.setFont(f);
        goodsNumInput.add(goods_num);
        goodsNumInput.add(goods_numField);
        return goodsNumInput;
    }

    public void setGoodsTable(Goods goods){
        String[] header = {"商品货号","商品名称","商品单价","商品数量"};    // 表头
        Object[][] objects = {{goods.getG_id(),goods.getG_name(),goods.getPrice(),goods.getAmount()}};         // 表数据
        goodsModel = new DefaultTableModel(objects,header);
        this.goodsTable.setModel(goodsModel);
    }
    public void setGoodsTable(List<Goods> goods){
        String[] header = {"商品货号","商品名称","商品单价","商品数量"};    // 表头
        Object[][] objects = new Object[goods.size()][];                                       // 表数据
        int i = 0;
        for (Goods g:goods
        ) {
            Object[] obj = new Object[]{g.getG_id(),g.getG_name(),g.getPrice(),g.getAmount()};
            objects[i] = obj;
            i++;
        }
        goodsModel = new DefaultTableModel(objects,header);
        this.goodsTable.setModel(goodsModel);
    }
    public void setVipUseContent(String vid){
        IsDiscount = true;   //使用了会员卡
        vipuseid = vid;   //记录会员卡号
        vipusecontent.setText(vid);
        //discountcontent.setText("9折");
    }
    public void setVip(Vip vip){
        this.vip = vip;
    }
    public void setUntiltime(Timestamp t){
        vipetime = t;
        String str = df.format(t);
        untiltimecontent.setText(str);
    }
    public void setCumuamount(Double a){
        cumutotalamount = a;
        cumuamountcontent.setText(a+"");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==goodsManagerButton){
            cardPanel.add(getGoodsManagerPanel(),"goodsManagerPanel");
            cardLayout.show(cardPanel,"goodsManagerPanel");
        }
        if(e.getSource()==cashManagerButton){
            cardPanel.add(getCashManagerPanel(),"cashManagerPanel");
            cardLayout.show(cardPanel,"cardManagerPanel");
        }
        if(e.getSource()==g_SearchButton){
            String g_name = g_SearchFiled.getText();
            Goods goods = goodsService.searchGoods(g_name);
            if(goods != null){
                this.setGoodsTable(goods);
            }else{
                JOptionPane.showMessageDialog(null,"该商品不存在！");
            }
        }
        if (e.getSource() == g_SearchAllButton){
            goodss = goodsService.getAll();
            this.setGoodsTable(goodss);
        }
        if(e.getSource()==exitButton){
            dispose();
            new Login().LoginGui();
        }
        if(e.getSource()==add_vip){
            new AddVipcardFrame(this);
        }
        if(e.getSource()==apply_vip){
            new ApplyVipFrame(this,false,"办理会员卡——收费50元");
        }
        if(e.getSource()==add_goods){  //添加商品   减少库存
            //不能为空
            if("".equals(goods_idField.getText()) || goods_idField.getText() == null){
                JOptionPane.showMessageDialog(null,"请输入商品货号!");
                return;
            }
            if("".equals(goods_numField.getText()) || goods_numField.getText() == null){
                JOptionPane.showMessageDialog(null,"请输入商品数目!");
                return;
            }
            String id = goods_idField.getText();
            Integer num = Integer.parseInt(goods_numField.getText());
            Goods g = goodsService.searchGoodsByCode(id);
            if(g==null){
                JOptionPane.showMessageDialog(null,"商品不存在或已下架!");
                return;
            }
            if(g.getAmount()<num){
                JOptionPane.showMessageDialog(null,"商品库存不足!");
                return;
            }
            Object[] info = {"b"+orderid,id,g.getG_name(),num,g.getPrice(),DateTimeUtil.getTime()};
            goodsService.updateGoods(id,num);   //修改库存
            //因为sells表参照了income表，所以结账时才能修改sells表
            //OrderInfo orderinfo = new OrderInfo(id,orderid,num);
            //orderInfoService.updateOrderInfo(orderinfo);  //修改sells表
            //结账时再修改数据库 sells表 ？？？
            //如果该商品已经加入
            /*
            for(int i=0;i<dtm.getRowCount();i++){
                String gid = (String) dtm.getValueAt(i,0);
                if(gid.equals(id)){  //更改第i行的数目

                }
            }
             */
            dtm.addRow(info);
            //修改金额总计
            info_totalcontent += num * g.getPrice();
            totalcostcontent.setText(info_totalcontent+"");
            goods_idField.setText("");
            goods_numField.setText("");
        }
        if(e.getSource() == pay){   //结账  修改income表
            if(info_totalcontent == 0.0){
                JOptionPane.showMessageDialog(null,"没有任何商品!");
                return;
            }

            User admin = (User) map.get("employee");                      // 获取登录时  传递的参数
            Timestamp etime = new Timestamp(new Date().getTime());    //结账时间
            Double real_content = 0.0;    //实际收款
            if(IsDiscount){
                real_content = info_totalcontent*0.9;
            }else{
                real_content = info_totalcontent;
            }
            Order order = new Order("b"+orderid,admin.getCid(),info_totalcontent,real_content,vipuseid,etime);
            System.out.println(order);
            orderService.addOrder(order);
            //再修改sells表
            for(int i=0;i<dtm.getRowCount();i++){
                String lid = (String) dtm.getValueAt(i,0);
                String gid = (String) dtm.getValueAt(i,1);
                int amount = (int) dtm.getValueAt(i,3);
                OrderInfo orderInfo = new OrderInfo(gid,lid,amount);
                orderInfoService.updateOrderInfo(orderInfo);    //更新sells表
            }
            //修改会员表记录  已定义触发器 会自动修改
            /*
            if(IsDiscount){ //使用了会员卡
                vipService.updateVipAmount(vip,real_content);
            }

             */

            int rowCount = dtm.getRowCount();
            System.out.println(rowCount);
            new ReceiptFrame(rowCount,dtm,info_totalcontent,"b"+orderid,etime,vipuseid);


            JOptionPane.showMessageDialog(null,"叮！入账"+real_content);
            if(info_totalcontent >= 1000.0 && !IsDiscount){
                Object[] options = {"是","否"};
                int x = JOptionPane.showOptionDialog(null,"有一次免费办会员卡的机会,是否办理：",
                        "福利赠送",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if(x==0){
                    new ApplyVipFrame(this,true,"赠送会员卡");
                }
            }
            this.setCashTable();  //清空表
            orderid = simpleDateFormat.format(new Date()); //产生新的随机数作为订单编号
            info_totalcontent = 0.0;
            totalcostcontent.setText("");
            vipusecontent.setText("");
            untiltimecontent.setText("");
            cumuamountcontent.setText("");
            IsDiscount = false;
            vipuseid = null;
            vip = null;

            return;

        }

    }

    public static void main(String args[]){

        new CashierManagerFrame(new HashMap());
    }
}