package hust.mysql.gui;

import hust.mysql.bean.Bill;
import hust.mysql.bean.Goods;
import hust.mysql.bean.User;
import hust.mysql.bean.Vip;
import hust.mysql.dao.BillDAO;
import hust.mysql.difficlt.BarChartDemo;
import hust.mysql.difficlt.PieChartDemo;
import hust.mysql.service.*;
import hust.mysql.utils.ClockThread;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017/5/24.
 *  管理员 界面
 */


public class AdminManagerFrame extends JFrame implements ActionListener,ListSelectionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel,cardPanel,buttonPanel,infoPanel,goodsManagerPanel,
            employeeManagerPanel,dataStatisticsPanel,vipManagerPanel,billManagerPanel;
    private JTable goodsTable,emplpyeeTable,vipTable,billTable;             // 商品表  用户表
    private DefaultTableModel goodsModel,employeeModel,vipModel,billModel;   // 商品表模型  用户表模型
    private JScrollPane goodsScrolPane,employeeScrolPane,vipScrolPane,billScrolPane;
    private JButton goodsManagerButton,emplpyeeManagerButton,dataStatisticsButton,
            vipManagerButton,exitButton,billManagerButton,elseButton;

    // billsManagerPanel 组件
    private JPanel b_NounthPanel;
    private JButton b_SearchButton;
    private JTextField b_SearchFiled;
    private DefaultListSelectionModel b_SelectionModel;     // 商品管理面板 表格 选择模型

    // goodsManagerPanel 组件
    private JPanel g_SouthPanel,g_NounthPanel;
    private JButton g_AddButton,g_DeleteButton,g_UpdateButton,g_SearchButton,g_SearchAllButton;//typeManagerButton;
    private JTextField g_SearchFiled;
    private DefaultListSelectionModel g_SelectionModel;     // 商品管理面板 表格 选择模型
    private static int g_DeleteIndex = -1;

    // employeeManagetPaenl 组件
    private JPanel e_SouthPanel,e_NounthPanel;
    private JButton e_AddButton,e_DeleteButton,e_UpdateButton,e_SearchButton,e_SearchAllButton;
    private JTextField e_SearchFiled;
    private DefaultListSelectionModel e_SelectionModel;    // 员工管理面板  表格 选择模型
    private static int e_DeleteIndex = -1;

    //vip 组件
    private JPanel v_SouthPanel,v_NounthPanel;
    private JButton v_SearchButton;
    private JTextField v_SearchFiled;
    private DefaultListSelectionModel v_SelectionModel;    // 员工管理面板  表格 选择模型

    // dataStatisticsPanel 组件
    private JPanel dataPanel,dataButtonPanel;
    private JPanel PieChartPanel,BarChartPanel;
    private JButton employeeButton,goodsButton;

    // 其他
    private JPanel elsePanel;
    private JLabel aboutLable1,aboutLable2,aboutLable3;

    // 自定义 变量
    private ClockThread clockThread = null;               // 系统时间线程
    private JLabel timeLabel;
    private Map map = null;
    private List<Goods> goodss = null;                    // 获取 数据库 商品信息
    private List<User> employees = null;              // 获取 数据库 员工信息
    private List<Vip> vips = null;                     //获取所有会员信息
    private List<Bill> bills = null;


    // Service 层
    private GoodsService goodsService = new GoodsServiceImp();
    //private GoodsTypeService typeService = ServiceFactory.getGoodsTypeService();
    private EmployeeService employeeService = new EmployeeServiceImp();
    //private AdminService adminService = ServiceFactory.getAdminService();
    private OrderService orderService = new OrderServiceImp();
    private OrderInfoService orderInfoService = new OrderInfoServiceImp();
    private VipService vipService = new VipServiceImp();
    private BillService billService = new BillServiceImp();

    /**
     *  构造方法
     * @param map
     */
    public AdminManagerFrame(Map map){
        this.map = map;                                  // 接收登录传送的 map
        init();
        setTitle("我购物后台管理系统 No.1");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
     *  界面 初始化
     */
    public void  init(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(50,50,58));

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(getGoodsManagerPanel(),"goodsManagerPanel");
        cardPanel.add(getEmployeeManagerPanel(),"employeeManagerPanel");
        cardPanel.add(getDataStatisticsPanel(),"dataStatisticsPanel");
        cardPanel.add(getVipsManagerPanel(),"vipsManagesrPanel");
        cardPanel.add(getBillManagerPanel(),"billManagerPanel");
        cardPanel.add(getAboutPanel(),"aboutPanel");


        mainPanel.add(getButtonPanel(),BorderLayout.NORTH);
        mainPanel.add(getInfoPanel(),BorderLayout.SOUTH);
        mainPanel.add(cardPanel,BorderLayout.CENTER);

        ((JComponent) getContentPane()).setOpaque(false); //将框架强转为容器
        ImageIcon img = new ImageIcon("png//主背景.jpg"); //传入背景图片路径
        JLabel background = new JLabel(img);//将图片放进标签里
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//将标签放进容器里
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());//设置标签的大小
        add(mainPanel);
    }
    public JPanel getAboutPanel(){
        elsePanel = new JPanel(new BorderLayout());
        JPanel elsejp1 = new JPanel(new FlowLayout());
        aboutLable1 = new JLabel("华中科技大学计算机学院数据库课设——夏媛");
        aboutLable2 = new JLabel("更多功能敬请期待！！！");
        elsejp1.add(aboutLable1);
        elsejp1.add(aboutLable2);
        Font ff = new Font("微软雅黑", Font.BOLD, 40);
        aboutLable1.setFont(ff);
        aboutLable2.setFont(ff);
        elsePanel.add(elsejp1,BorderLayout.CENTER);
        return elsePanel;
    }
    /**
     *  功能 按钮 面板
     * @return
     */
    public JPanel getButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        buttonPanel.setBackground(new Color(242,242,242));

        goodsManagerButton = new JButton("商品管理");
        emplpyeeManagerButton = new JButton("员工管理");
        vipManagerButton = new JButton("会员卡管理");
        billManagerButton = new JButton("流水记录");
        dataStatisticsButton = new JButton("数据统计");
        exitButton = new JButton("登出");
        elseButton = new JButton("关于");

        goodsManagerButton.addActionListener(this);
        emplpyeeManagerButton.addActionListener(this);
        dataStatisticsButton.addActionListener(this);
        billManagerButton.addActionListener(this);
        vipManagerButton.addActionListener(this);
        elseButton.addActionListener(this);
        exitButton.addActionListener(this);

        buttonPanel.add(goodsManagerButton);
        buttonPanel.add(emplpyeeManagerButton);
        buttonPanel.add(vipManagerButton);
        buttonPanel.add(billManagerButton);
        buttonPanel.add(dataStatisticsButton);
        buttonPanel.add(elseButton);
        buttonPanel.add(exitButton);


        return buttonPanel;
    }
    /**
     * 信息 面板
     * @return
     */
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
    public JPanel getBillManagerPanel(){
        billManagerPanel = new JPanel();
        billManagerPanel.setBackground(new Color(242,242,242));
        bills = billService.getBillALL();

        billScrolPane = new JScrollPane(getBillssTable(bills));
        billScrolPane.setPreferredSize(new Dimension(950,450));            // 设置滚动面板大小

        b_NounthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));     // 查询 商品面板
        b_NounthPanel.setBackground(new Color(242,242,242));
        b_SearchFiled = new JTextField(60);
        b_SearchFiled.setText("请输入需要查询的收银员id");                                    // 此处未注册 鼠标监听
        b_SearchFiled.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                b_SearchFiled.setText("");
            }
        });
        b_SearchButton = new JButton("查询");
        b_SearchButton.addActionListener(this);
        b_NounthPanel.add(b_SearchFiled);
        b_NounthPanel.add(b_SearchButton);

        billManagerPanel.add(b_NounthPanel,BorderLayout.NORTH);
        billManagerPanel.add(billScrolPane,BorderLayout.CENTER);
        return billManagerPanel;
    }
    public JTable getBillssTable(List<Bill> bills){

        String[] header = {"订单编号","收银员id","商品货号","数目","单价","时间"};    // 表头
        Object[][] objects = new Object[bills.size()][];                                       // 表数据
        int i = 0;
        for (Bill b:bills
        ) {
            Object[] obj = new Object[]{b.getLid(),b.getCid(),b.getGid(),b.getBuy_num(),b.getPrice(),b.getPtime()};
            objects[i] = obj;
            i++;
        }

        billModel = new DefaultTableModel(objects,header);
        billTable = new JTable(billModel){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }
        };

        b_SelectionModel = new DefaultListSelectionModel();            // 初始化选择模型
        b_SelectionModel.addListSelectionListener(this);            // 注册监听
        billTable.setSelectionModel(b_SelectionModel);                // 将模型 添加 到表格中

        return billTable;
    }
    public JPanel getVipsManagerPanel(){
        vipManagerPanel = new JPanel();
        vipManagerPanel.setBackground(new Color(242,242,242));
        vips = vipService.getAll();                                // 获取 数据库 所有商品

        vipScrolPane = new JScrollPane(getVipsTable(vips));
        vipScrolPane.setPreferredSize(new Dimension(950,450));            // 设置滚动面板大小

        v_NounthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));     // 查询 商品面板
        v_NounthPanel.setBackground(new Color(242,242,242));
        v_SearchFiled = new JTextField(60);
        v_SearchFiled.setText("请输入需要查询的会员卡号");                                    // 此处未注册 鼠标监听
        v_SearchFiled.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                v_SearchFiled.setText("");
            }
        });
        v_SearchButton = new JButton("查询");
        v_SearchButton.addActionListener(this);
        v_NounthPanel.add(v_SearchFiled);
        v_NounthPanel.add(v_SearchButton);

        vipManagerPanel.add(v_NounthPanel,BorderLayout.NORTH);
        vipManagerPanel.add(vipScrolPane,BorderLayout.CENTER);
        return vipManagerPanel;
    }
    /**
     *  商品库存管理 面板
     * @return JPanel
     */
    public JPanel getGoodsManagerPanel(){
        goodsManagerPanel = new JPanel();
        goodsManagerPanel.setBackground(new Color(242,242,242));
        goodss = goodsService.getAll();                                // 获取 数据库 所有商品

        goodsScrolPane = new JScrollPane(getGoodsTable(goodss));
        goodsScrolPane.setPreferredSize(new Dimension(950,370));            // 设置滚动面板大小

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

        g_SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        g_SouthPanel.setBackground(new Color(242,242,242));

        g_AddButton = new JButton("添加商品");
        g_DeleteButton = new JButton("删除商品");
        g_UpdateButton = new JButton("修改信息");
        g_SearchAllButton = new JButton("查看所有");
        //typeManagerButton = new JButton("商品类别");

        g_AddButton.addActionListener(this);
        g_DeleteButton.addActionListener(this);
        g_UpdateButton.addActionListener(this);
        g_SearchAllButton.addActionListener(this);
        //typeManagerButton.addActionListener(this);
        g_SouthPanel.add(g_AddButton);
        g_SouthPanel.add(g_DeleteButton);
        g_SouthPanel.add(g_UpdateButton);
        g_SouthPanel.add(g_SearchAllButton);
        //g_SouthPanel.add(typeManagerButton);

        goodsManagerPanel.add(g_NounthPanel,BorderLayout.NORTH);
        goodsManagerPanel.add(goodsScrolPane,BorderLayout.CENTER);
        goodsManagerPanel.add(g_SouthPanel,BorderLayout.SOUTH);
        return goodsManagerPanel;
    }

    /**
     *  设置 表格 数据 面板
     * @return
     */
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
        g_SelectionModel.addListSelectionListener(this);            // 注册监听
        goodsTable.setSelectionModel(g_SelectionModel);                // 将模型 添加 到表格中

        return goodsTable;
    }
    public JTable getVipsTable(List<Vip> vips){

        String[] header = {"会员卡号","持卡人姓名","电话号码","到期时间","累计金额"};    // 表头
        Object[][] objects = new Object[vips.size()][];                                       // 表数据
        int i = 0;
        for (Vip v:vips
        ) {
            Object[] obj = new Object[]{v.getV_id(),v.getV_name(),v.getTelephone(),v.getE_time(),v.getCost()};
            objects[i] = obj;
            i++;
        }

        vipModel = new DefaultTableModel(objects,header);
        vipTable = new JTable(vipModel){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }
        };

        v_SelectionModel = new DefaultListSelectionModel();            // 初始化选择模型
        v_SelectionModel.addListSelectionListener(this);            // 注册监听
        vipTable.setSelectionModel(v_SelectionModel);                // 将模型 添加 到表格中

        return vipTable;
    }
    public void setVipTable(Vip v){
        String[] header = {"会员卡号","持卡人姓名","电话号码","到期时间","累计金额"};    // 表头
        Object[][] objects = {{v.getV_id(),v.getV_name(),v.getTelephone(),v.getE_time(),v.getCost()}};         // 表数据
        vipModel = new DefaultTableModel(objects,header);
        this.vipTable.setModel(vipModel);
    }
    public void setBillTable(List<Bill> bills){
        String[] header = {"订单编号","收银员id","商品货号","数目","单价","时间"};    // 表头
        Object[][] objects = new Object[bills.size()][];
        int i = 0;
        for (Bill b:bills
        ) {
            Object[] obj = new Object[]{b.getLid(),b.getCid(),b.getGid(),b.getBuy_num(),b.getPrice(),b.getPtime()};
            objects[i] = obj;
            i++;
        }
        billModel = new DefaultTableModel(objects,header);
        this.billTable.setModel(billModel);
    }
    /**
     *  设置 JTable 模型
     * @param goods
     * @return
     */
    public void setGoodsTable(Goods goods){
        String[] header = {"商品货号","商品名称","商品单价","商品数量"};    // 表头
        Object[][] objects = {{goods.getG_id(),goods.getG_name(),goods.getPrice(),goods.getAmount()}};         // 表数据
        goodsModel = new DefaultTableModel(objects,header);
        this.goodsTable.setModel(goodsModel);
    }
    /**
     *  重载
     * @param goods
     */
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

    /**
     * 添加 行
     * @param goods
     */
    public void setModel(Goods goods){
        Object[] obj = new Object[]{goods.getG_id(),goods.getG_name(),goods.getPrice(),goods.getAmount()};
        goodsModel.addRow(obj);
    }

    /**
     *  员工管理 面板
     * @return JPanel
     */
    public JPanel getEmployeeManagerPanel(){
        employeeManagerPanel = new JPanel();
        employeeManagerPanel.setBackground(new Color(242,242,242));
        employees = employeeService.getEmployeeAll();

        employeeScrolPane = new JScrollPane(getEmplpyeeTable(employees));
        employeeScrolPane.setPreferredSize(new Dimension(950,370));            // 设置滚动面板大小

        e_NounthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));     // 查询 商品面板
        e_NounthPanel.setBackground(new Color(242,242,242));
        e_SearchFiled = new JTextField(60);
        e_SearchFiled.setText("请输入需要查询员工id");                                    // 此处未注册 鼠标监听
        e_SearchFiled.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                e_SearchFiled.setText("");
            }
        });
        e_SearchButton = new JButton("查询");
        e_SearchButton.addActionListener(this);
        e_NounthPanel.add(e_SearchFiled);
        e_NounthPanel.add(e_SearchButton);

        e_SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        e_SouthPanel.setBackground(new Color(242,242,242));
        e_AddButton = new JButton("添加员工");
        e_DeleteButton = new JButton("删除员工");
        e_UpdateButton = new JButton("修改信息");
        e_SearchAllButton = new JButton("所有员工");
        e_AddButton.addActionListener(this);
        e_DeleteButton.addActionListener(this);
        e_UpdateButton.addActionListener(this);
        e_SearchAllButton.addActionListener(this);
        e_SouthPanel.add(e_AddButton);
        e_SouthPanel.add(e_DeleteButton);
        e_SouthPanel.add(e_UpdateButton);
        e_SouthPanel.add(e_SearchAllButton);

        employeeManagerPanel.add(e_NounthPanel,BorderLayout.NORTH);
        employeeManagerPanel.add(employeeScrolPane,BorderLayout.CENTER);
        employeeManagerPanel.add(e_SouthPanel,BorderLayout.SOUTH);

        return employeeManagerPanel;
    }
    public JTable getEmplpyeeTable(List<User> employees){

        String[] header = {"员工id","员工密码","员工姓名","员工性别","员工年龄","员工权限"};    // 表头
        Object[][] objects = new Object[employees.size()][];                                       // 表数据
        int i = 0;
        for (User employee:employees
        ) {
            Object[] obj = new Object[]{employee.getCid(),employee.getUserpwd(),employee.getCname(),
                        employee.getGender(),employee.getAge(),employee.getFlag()};
            objects[i] = obj;
            i++;
        }
        employeeModel= new DefaultTableModel(objects,header);
        emplpyeeTable = new JTable(employeeModel){
            public boolean isCellEditable(int row,int column){            // 此方法 设置 JTable 可以被选中  不可以被编辑
                return false;
            }
        };
        //        goodsModel.isCellEditable(0,0);                                 // 设置 某个单元格不可编辑
        //        goodsTable.setEnabled(false);                                    // 并不能实现 预期效果

        e_SelectionModel = new DefaultListSelectionModel();            // 初始化选择模型
        e_SelectionModel.addListSelectionListener(this);            // 注册监听
        emplpyeeTable.setSelectionModel(e_SelectionModel);                // 将模型 添加 到表格中

        return emplpyeeTable;
    }

    /***
     * 查询 指定员工 设置JTable 数据
     * @param employee
     */
    public void setEmployeeModel(User employee){
        String[] header = {"员工id","员工密码","员工姓名","员工性别","员工年龄","员工权限"};    // 表头
        Object[][] objects = {{employee.getCid(),employee.getUserpwd(),employee.getCname(),
                employee.getGender(),employee.getAge(),employee.getFlag()}};
        employeeModel = new DefaultTableModel(objects,header);
        emplpyeeTable.setModel(employeeModel);
    }

    /**
     * 添加员工 并且在表格中添加一行
     * @param employee
     */
    public void addEmployeeRow(User employee){
        String[] header = {"员工id","员工密码","员工姓名","员工性别","员工年龄","员工权限"};    // 表头
        Object[] objects = {employee.getCid(),employee.getUserpwd(),employee.getCname(),
                employee.getGender(),employee.getAge(),employee.getFlag()};
        employeeModel.addRow(objects);
    }
    /**
     * 设置 JTable 模型数据
     * @param employees
     */
    public void setEmployeeModel(List<User> employees){
        String[] header = {"员工id","员工密码","员工姓名","员工性别","员工年龄","员工权限"};    // 表头
        Object[][] objects = new Object[employees.size()][];
        int i = 0;
        for (User e:employees
        ) {
            Object[] obj = {e.getCid(),e.getUserpwd(),e.getCname(),
                    e.getGender(),e.getAge(),e.getFlag()};
            objects[i] = obj;
            i++;
        }
        employeeModel = new DefaultTableModel(objects,header);
        emplpyeeTable.setModel(employeeModel);
    }
    /**
     *  数据统计 面板
     * @return JPanel
     */
    public JPanel getDataStatisticsPanel(){
        dataStatisticsPanel = new JPanel(new BorderLayout());
        dataStatisticsPanel.setBackground(new Color(242,242,242));

        // dataStatisticsPanel 组件
//        private JPanel dataPanel,dataButtonPanel;
//        private JPanel PieChartPanel,BarChartPanel;
//        private JButton employeeButton,goodsButton;
        dataPanel = new JPanel();
        dataPanel.setBackground(new Color(242,242,242));
        PieChartPanel  = new JPanel();     // 饼状图
        BarChartPanel = new JPanel();       // 柱状图


        dataButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        employeeButton = new JButton("员工业绩统计");
        goodsButton = new JButton("商品销售统计");

        employeeButton.addActionListener(this);
        goodsButton.addActionListener(this);
        dataButtonPanel.setBackground(new Color(242,242,242));
        dataButtonPanel.add(employeeButton);
        dataButtonPanel.add(goodsButton);


        dataStatisticsPanel.add(dataPanel,BorderLayout.CENTER);
        dataStatisticsPanel.add(dataButtonPanel,BorderLayout.SOUTH);

        return dataStatisticsPanel;
    }

    /**
     *  按钮监听
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  卡片布局 按钮监听  之 显示面板
         */
        if (e.getSource() == goodsManagerButton){
            cardPanel.add(getGoodsManagerPanel(),"goodsManagerPanel");
            cardLayout.show(cardPanel,"goodsManagerPanel");
        }
        if (e.getSource() == emplpyeeManagerButton){
            cardPanel.add(getEmployeeManagerPanel(),"employeeManagerPanel");
            cardLayout.show(cardPanel,"employeeManagerPanel");
        }
        if(e.getSource() == vipManagerButton){
            System.out.println("会员卡");
            cardPanel.add(getVipsManagerPanel(),"vipsManagerPanel");
            cardLayout.show(cardPanel,"vipsManagerPanel");
        }
        if(e.getSource()==billManagerButton){
            cardPanel.add(getBillManagerPanel(),"billManagerPanel");
            cardLayout.show(cardPanel,"billManagerPanel");
        }
        if (e.getSource() == dataStatisticsButton){
            cardPanel.add(getDataStatisticsPanel(),"dataStatisticsPanel");
            cardLayout.show(cardPanel,"dataStatisticsPanel");
        }
        if(e.getSource() == elseButton){
            cardPanel.add(getAboutPanel(),"aboutPanel");
            cardLayout.show(cardPanel,"aboutPanel");
        }
        if(e.getSource() == b_SearchButton){
            String cid = b_SearchFiled.getText();
            List<Bill> billss = billService.searchBillbyCID(cid);
            if (billss != null)
                this.setBillTable(billss);
            else
                JOptionPane.showMessageDialog(null,"该收银员不存在或没有经手流水！");
        }
        /**
         * 会员管理面板   查询
         */
        if(e.getSource() == v_SearchButton){
            String v_id = v_SearchFiled.getText();
            Vip vips = vipService.searchVipbyId(v_id);
            if (vips != null)
                this.setVipTable(vips);
            else
                JOptionPane.showMessageDialog(null,"会员卡不存在！");
        }
        /**
         *  商品管理面板 按钮监听   之  增删改查
         */
        if (e.getSource() == g_AddButton){
            new AddGoodFrame(this);
        }
        if (e.getSource() == g_DeleteButton){
            int isDelete = JOptionPane.showConfirmDialog(null,"是否删除？");
            if (isDelete == JOptionPane.OK_OPTION){
                String g_code = (String) goodsModel.getValueAt(g_DeleteIndex,0);    // 获取 要删除 指定商品的 商品条码
                goodsService.deleteGoods(g_code);                   // 删除 数据库中对应的数据
                goodsModel.removeRow(g_DeleteIndex);                // 删除 表模型中的数据
            }
        }
        if (e.getSource() == g_UpdateButton){
            if (g_DeleteIndex != -1) {
                String g_id = (String) goodsModel.getValueAt(g_DeleteIndex, 0);
                String g_name = (String) goodsModel.getValueAt(g_DeleteIndex, 1);
                Double g_pirce = (Double) goodsModel.getValueAt(g_DeleteIndex, 2);
                Integer g_amount = (Integer) goodsModel.getValueAt(g_DeleteIndex, 3);
                Goods goods = new Goods(g_id, g_name, g_pirce, g_amount);

                new UpdateGoodsFrame(this, goods);              // 创建商品信息修改 窗体
            }else {
                JOptionPane.showConfirmDialog(null,"请选择需要修改的商品");
            }
        }
        if (e.getSource() == g_SearchButton){
            String g_name = g_SearchFiled.getText();
            Goods goods = goodsService.searchGoods(g_name);                   // 查询 该商品
            if (goods != null)
                this.setGoodsTable(goods);
            else
                JOptionPane.showMessageDialog(null,"该商品不存在！");
        }
        if (e.getSource() == g_SearchAllButton){
            goodss = goodsService.getAll();
            this.setGoodsTable(goodss);
        }
        /**
         * 员工管理界面 之 增删改查
         */
        if (e.getSource() == e_AddButton){
            new AddEmployeeFrame(this);
        }
        if (e.getSource() == e_DeleteButton){
            int isDelete = JOptionPane.showConfirmDialog(null,"是否删除？");
            if (isDelete == JOptionPane.OK_OPTION){
                String e_account = (String) employeeModel.getValueAt(e_DeleteIndex,0);    // 获取 要删除 指定商品的 商品条码
                employeeService.deleteEmployee(e_account);                 // 删除 数据库中对应的数据
                employeeModel.removeRow(e_DeleteIndex);                    // 删除 表模型中的数据
            }
        }
        if (e.getSource() == e_UpdateButton){
            if (e_DeleteIndex != -1){
                System.out.println("ready update employee");
                String e_account = (String) employeeModel.getValueAt(e_DeleteIndex,0);
                String e_password = (String) employeeModel.getValueAt(e_DeleteIndex,1);
                String e_nickName = (String) employeeModel.getValueAt(e_DeleteIndex,2);
                String e_gender = (String) employeeModel.getValueAt(e_DeleteIndex,3);
                Integer e_age = (Integer) employeeModel.getValueAt(e_DeleteIndex,4);
                Integer e_flag = (Integer) employeeModel.getValueAt(e_DeleteIndex,5);

                User employee = new User(e_account,e_password,e_nickName,e_gender,e_age,e_flag);
                System.out.println(e_nickName);
                new UpdateEmployeeFrame(this,employee);

            }else {
                JOptionPane.showConfirmDialog(null,"请选择需要修改用户！");
            }
        }
        if (e.getSource() == e_SearchAllButton){
            employees = employeeService.getEmployeeAll();
            setEmployeeModel(employees);
        }
        if (e.getSource() == e_SearchButton){
            String e_account = e_SearchFiled.getText();
            User employee = employeeService.searchEmployee(e_account);
            if (employee != null){
                setEmployeeModel(employee);                 // 重新设置表格 模型数据
            }else {
                JOptionPane.showConfirmDialog(null,"该用户不存在!");
            }
        }
        if(e.getSource() == exitButton){
            dispose();
            new Login().LoginGui();
        }
        /**
         * 数据统计面板 按钮监听
         */

        if (e.getSource() == employeeButton){
            // 饼状图
            // 获取 两组 数据
            List<User> ems = employeeService.getEmployeeAll();
            String[] names = new String[ems.size()];
            Integer[] values = new Integer[ems.size()];
            for (int i = 0; i < ems.size(); i++) {
                names[i] = ems.get(i).getCid();
                values[i] = orderService.getOrderCountByE_id(ems.get(i).getCid());
            }
            PieChartPanel.removeAll();
            PieChartPanel.add(new PieChartDemo(names,values).getPieChartPanel());
            dataPanel.removeAll();
            dataPanel.add(PieChartPanel);
            dataPanel.updateUI();
        }
        if (e.getSource() == goodsButton){
            // 柱状图
            List<Goods> gs = goodsService.getAll();
            String[] names = new String[gs.size()];
            Integer[] values = new Integer[gs.size()];

            for (int i = 0; i < gs.size(); i++) {
                names[i] = gs.get(i).getG_name();
                values[i] = orderInfoService.getOrderInfoCountByG_code(gs.get(i).getG_id());
            }
            BarChartPanel.removeAll();
            BarChartPanel.add(new BarChartDemo(names,values).getChartPanel());

            dataPanel.removeAll();
            dataPanel.add(BarChartPanel);
            dataPanel.updateUI();

        }


    }

    /**
     *  实现ListSelectionListener 接口中的方法
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == g_SelectionModel) {
            if (e.getValueIsAdjusting()) {                                       // 此if语句 用来判断鼠标是为 点击 还是 释放   点击 true 释放 false
                g_DeleteIndex = goodsTable.getSelectedRow();                    // 获取选择的 行号
            }
        }
        if (e.getSource() == e_SelectionModel){
            if (e.getValueIsAdjusting()){
                e_DeleteIndex = emplpyeeTable.getSelectedRow();
            }
        }
    }

    /**
     *  主方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        new AdminManagerFrame(new HashMap());
    }

}

