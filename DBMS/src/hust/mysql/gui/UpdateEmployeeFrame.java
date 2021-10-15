package hust.mysql.gui;

import hust.mysql.bean.User;
import hust.mysql.service.EmployeeService;
import hust.mysql.service.EmployeeServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017-05-25.
 */
public class UpdateEmployeeFrame extends JFrame implements ActionListener{
    private AdminManagerFrame adminManagerFrame;
    private User employee;

    private JPanel mainPanel;
    private JTextField e_AccountFiled,e_PasswordFiled,e_NickNameFiled,e_genderFiled,e_ageFiled,e_flagFiled;
    private JButton addButton,cancelButton;

    // Service
    private EmployeeService employeeService = new EmployeeServiceImp();

    // 自定义变量
    private List<User> employees = null;

    public UpdateEmployeeFrame(AdminManagerFrame adminManagerFrame,User employee){
        this.adminManagerFrame = adminManagerFrame;
        this.employee = employee;
        init();
        setTitle("修改信息");
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
        e_genderFiled = new JTextField();
        e_ageFiled = new JTextField();
        e_flagFiled = new JTextField();

        System.out.println(employee);
        e_AccountFiled.setText(employee.getCid());
        e_PasswordFiled.setText(employee.getUserpwd()+"");
        e_NickNameFiled.setText(employee.getCname()+"");
        e_genderFiled.setText(employee.getGender()+"");
        e_ageFiled.setText(employee.getAge()+"");
        e_flagFiled.setText(employee.getFlag()+"");



        addButton = new JButton("保存");
        cancelButton = new JButton("重置");               // 此处名称不规范  此按钮 为批量添加按钮
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        jPanel.add(addButton);
        jPanel.add(cancelButton);

        mainPanel.add(e_AccountFiled);
        mainPanel.add(e_PasswordFiled);
        mainPanel.add(e_NickNameFiled);
        mainPanel.add(e_genderFiled);
        mainPanel.add(e_ageFiled);
        mainPanel.add(e_flagFiled);
        mainPanel.add(jPanel);

        add(mainPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){// 保存
            String e_Account = e_AccountFiled.getText();
            String e_Password = e_PasswordFiled.getText();
            String e_NickName = e_NickNameFiled.getText();
            String e_gender = e_genderFiled.getText();
            int age = Integer.parseInt(e_ageFiled.getText());
            int flag = Integer.parseInt(e_flagFiled.getText());
            User employee = new User(e_Account,e_Password,e_NickName,e_gender,age,flag);

            employeeService.updateEmployee(employee);      // 将数据 持久化 到数据库
            employees = employeeService.getEmployeeAll();
            this.adminManagerFrame.setEmployeeModel(employees);
            dispose();
        }
        if (e.getSource() == cancelButton){
            e_AccountFiled.setText(employee.getCid());
            e_PasswordFiled.setText(employee.getUserpwd());
            e_NickNameFiled.setText(employee.getCname());
            e_genderFiled.setText(employee.getGender());
            e_ageFiled.setText(employee.getAge()+"");
            e_flagFiled.setText(employee.getFlag()+"");
        }
    }
}
