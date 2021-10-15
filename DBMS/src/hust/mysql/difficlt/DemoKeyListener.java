package hust.mysql.difficlt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DemoKeyListener extends JFrame {
    private JPanel jPanel;
    private JTextField jTextField;
    private JButton jButton;
    private JLabel jLabel;

    public DemoKeyListener(){
        init();
        setTitle("键盘事件");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init(){
        jPanel = new JPanel(new GridLayout(3,1));
        jTextField = new JTextField();
        jButton = new JButton("点击");
        jLabel = new JLabel("键盘");

        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("wenbenkang： "+KeyEvent.getKeyText(e.getKeyCode()));
                System.out.println(e.getKeyCode());
            }
        });
//        this.addKeyListener(this);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog(null,"请输入数量：");
                System.out.println(s);
                jTextField.requestFocus();                          // 请求焦点  这个非常重要
            }
        });

        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jButton);
        add(jPanel);
    }

    public static void main(String[] args) {
        new DemoKeyListener();
    }
}

