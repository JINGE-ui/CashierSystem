package hust.mysql.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

import hust.mysql.service.EmployeeServiceImp;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Login {
    //初始化字体
    Font d = new Font("楷体", Font.BOLD, 22);
    Font f = new Font("楷体", Font.BOLD, 15);
    // 初始化对象
    JFrame logingui = new JFrame("用户登录界面");
    JLabel userlogin = new JLabel("超市收银系统用户登录");
    JLabel username = new JLabel("用户名：");
    JLabel password = new JLabel("密 码：");
    JLabel usertyle = new JLabel("用户类型");
    JTextField name = new JTextField();
    JTextField pwd = new JPasswordField();
    JComboBox box = new JComboBox(new String[]{"管理员","收银员"} );
    JButton login = new JButton("登录");
    //给User类初始化对象user
    //User user = new User();
    private String id;
    private String userpwd;
    private int flag=2;
    private EmployeeServiceImp LoginService = new EmployeeServiceImp();

    public void LoginGui() {
        // 设置对象
        logingui.setBounds(450, 200, 550, 350);
        //设置退出
        logingui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消框架格式
        logingui.setLayout(null);
        //设置位置、大小和字体
        userlogin.setBounds(160, 30, 250, 30);
        userlogin.setFont(d);
        username.setBounds(110, 80, 100, 30);
        username.setFont(f);
        password.setBounds(110, 120, 100, 30);
        password.setFont(f);
        usertyle.setBounds(110, 160, 100, 30);
        usertyle.setFont(f);
        name.setBounds(200, 80, 180, 30);
        name.setFont(f);
        pwd.setBounds(200, 120, 180, 30);
        box.setBounds(200, 160, 100, 30);
        box.setFont(f);
        login.setBounds(190, 220, 110, 30);
        login.setFont(f);
        // 添加对象
        logingui.add(userlogin);
        logingui.add(username);
        logingui.add(password);
        logingui.add(usertyle);
        logingui.add(name);
        logingui.add(pwd);
        logingui.add(box);
        logingui.add(login);
        // 窗体可视化
        logingui.setVisible(true);
        logingui.setResizable(false);
        //设置登录图形界面的背景图片
        ((JComponent) logingui.getContentPane()).setOpaque(false); //将框架强转为容器
        ImageIcon img = new ImageIcon("png//主背景.jpg"); //传入背景图片路径
        JLabel background = new JLabel(img);//将图片放进标签里
        logingui.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//将标签放进容器里
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());//设置标签的大小
        //给下拉框设置选择监听事件
        box.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //判断选择选项是否和下拉框数据一致
                if(box.getSelectedItem().equals("管理员")){
                    //设置标志量的值
                    flag = 2;
                }else{
                    flag = 1;
                }
            }
        });
        //给登录按钮设置监听事件
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //提取文本框里的用户名和密码
                String name_text = name.getText();
                String pwd_text = pwd.getText();
                Map map = null;
                System.out.println("id="+name_text+",pwd="+pwd_text+",flag="+flag);
                //将得到的值存入user对象里面
                id = name_text;
                userpwd = pwd_text;

                //不能为空
                if("".equals(id) || id == null || "请输入账号".equals(id)){
                    JOptionPane.showMessageDialog(null,"请输入用户名!","登录失败",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if("".equals(userpwd) || userpwd == null || "请输入密码".equals(userpwd)){
                    JOptionPane.showMessageDialog(null,"请输入密码!","登录失败",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                map = LoginService.isLogin(id,userpwd,flag);
                if (map.get("info").equals("登录成功!")){
                    //界面转换，隐藏原来界面
                    logingui.setVisible(false);
                    if(flag==2){
                        //新的页面
                        new AdminManagerFrame(map);
                    }else{
                        new CashierManagerFrame(map);
                    }
                    //dispose();
                }else {
                    JOptionPane.showMessageDialog(null,map.get("info"),"登录失败",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    //整个程序执行的入口
    public static void main(String[] args) {
        //美化
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }



        Login l = new Login();
        l.LoginGui();
    }
}

