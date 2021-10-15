package hust.mysql.gui;

import hust.mysql.bean.User;
import hust.mysql.service.EmployeeService;
import hust.mysql.service.EmployeeServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017-05-25.
 */
public class AddEmployeeFrame extends JFrame implements ActionListener,MouseListener{
    private AdminManagerFrame adminManagerFrame;
    private JPanel mainPanel;
    private JTextField e_AccountFiled,e_PasswordFiled,e_NickNameFiled,e_gender,e_age,e_flag;
    private JButton addButton,cancelButton;

    // Service
    private EmployeeService employeeService = new EmployeeServiceImp();

    // 自定义变量
    private List<User> employees = null;

    public AddEmployeeFrame(AdminManagerFrame adminManagerFrame){
        this.adminManagerFrame = adminManagerFrame;
        init();
        setTitle("添加用户");
        setSize(320,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void init(){
        mainPanel = new JPanel(new GridLayout(7,1,0,20));

        e_AccountFiled = new JTextField();
        e_PasswordFiled = new JTextField();
        e_NickNameFiled = new JTextField();
        e_gender = new JTextField();
        e_age = new JTextField();
        e_flag = new JTextField();

        e_AccountFiled.setText("用户账户");
        e_PasswordFiled.setText("用户密码");
        e_NickNameFiled.setText("用户姓名");
        e_gender.setText("性别");
        e_age.setText("年龄");
        e_flag.setText("权限");

        e_AccountFiled.addMouseListener(this);
        e_PasswordFiled.addMouseListener(this);
        e_NickNameFiled.addMouseListener(this);
        e_gender.addMouseListener(this);
        e_age.addMouseListener(this);
        e_flag.addMouseListener(this);

        addButton = new JButton("添加");
        cancelButton = new JButton("取消");               // 此处名称不规范  此按钮 为批量添加按钮
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        jPanel.add(addButton);
        jPanel.add(cancelButton);

        mainPanel.add(e_AccountFiled);
        mainPanel.add(e_PasswordFiled);
        mainPanel.add(e_NickNameFiled);
        mainPanel.add(e_gender);
        mainPanel.add(e_age);
        mainPanel.add(e_flag);
        mainPanel.add(jPanel);

        add(mainPanel);
    }

    /**
     * 按钮监听
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            String e_Account = e_AccountFiled.getText();
            String e_Password = e_PasswordFiled.getText();
            String e_NickName = e_NickNameFiled.getText();
            String gender = e_gender.getText();
            String ages = e_age.getText();
            String flags = e_flag.getText();
            if("".equals(e_Account) || e_Account == null || "用户账户".equals(e_Account)){
                JOptionPane.showMessageDialog(null,"请输入账户名!","添加失败",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if("".equals(e_Password) || e_Password == null || "用户密码".equals(e_Password)){
                JOptionPane.showMessageDialog(null,"请输入密码!","添加失败",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if("".equals(flags) || flags == null || "权限".equals(flags)){
                JOptionPane.showMessageDialog(null,"请设置权限!","添加失败",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Integer flag = Integer.parseInt(flags);
            if(gender.equals("性别") || "".equals(gender)){
                gender=null;
            }
            if(e_NickName.equals("用户姓名") || "".equals(e_NickName)){
                e_NickName=null;
            }
            if(ages.equals("年龄") || "".equals(ages)){
                ages=null;
            }
            Integer age = null;
            if(ages != null){
                age = Integer.parseInt(ages);
            }


            User employee = new User(e_Account,e_Password,e_NickName,gender,age,flag);
            System.out.println("添加员工："+employee);
            employeeService.addEmployee(employee);
            this.adminManagerFrame.addEmployeeRow(employee);
            dispose();
        }
        if (e.getSource() == cancelButton){              // 批量添加
            dispose();
            //JOptionPane.showConfirmDialog(null,"您好，正在完善。请谅解！");
        }

    }

    /**
     *  鼠标监听
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == e_AccountFiled){
            e_AccountFiled.setText("");
        }
        if (e.getSource() == e_PasswordFiled){
            e_PasswordFiled.setText("");
        }
        if (e.getSource() == e_NickNameFiled){
            e_NickNameFiled.setText("");
        }
        if (e.getSource() == e_age){
            e_age.setText("");
        }
        if (e.getSource() == e_gender){
            e_gender.setText("");
        }
        if (e.getSource() == e_flag){
            e_flag.setText("");
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

