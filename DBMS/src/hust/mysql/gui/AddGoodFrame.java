package hust.mysql.gui;

import hust.mysql.bean.Goods;
import hust.mysql.service.GoodsService;
import hust.mysql.service.GoodsServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AddGoodFrame extends JFrame implements MouseListener,ActionListener{
    private AdminManagerFrame adminManagerFrame;
    private JPanel mainPanel;
    private JTextField g_CodeFiled,g_NameFiled,g_PirceFiled,g_AmountFiled;
    private JButton addButton;//batchButton;
    private JComboBox<String> jComboBox;              // 商品类别 选择框


    // Service 层
    private GoodsService goodsService = new GoodsServiceImp();

    public AddGoodFrame(AdminManagerFrame adminManagerFrame){
        this.adminManagerFrame = adminManagerFrame;
        init();
        setTitle("添加商品");
        setSize(320,350);
        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void init(){
        mainPanel = new JPanel(new GridLayout(6,1,0,20));

        g_CodeFiled = new JTextField();
        g_NameFiled = new JTextField();
        g_AmountFiled = new JTextField();
        g_PirceFiled = new JTextField();

        g_CodeFiled.addMouseListener(this);
        g_NameFiled.addMouseListener(this);
        g_AmountFiled.addMouseListener(this);
        g_PirceFiled.addMouseListener(this);

        g_CodeFiled.setText("商品货号");
        g_NameFiled.setText("商品名称");
        g_AmountFiled.setText("商品数量");
        g_PirceFiled.setText("商品单价");

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        addButton = new JButton("添加");
        addButton.addActionListener(this);
        jPanel.add(addButton);
        //jPanel.add(batchButton);

        mainPanel.add(g_CodeFiled);
        mainPanel.add(g_NameFiled);
        mainPanel.add(g_AmountFiled);
        mainPanel.add(g_PirceFiled);
        mainPanel.add(jPanel);

        add(mainPanel);
    }


//
//    public static void main(String[] args) throws Exception {
//        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        new AddGoodsFrame();
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == g_CodeFiled){
            g_CodeFiled.setText("");
        }
        if (e.getSource() == g_NameFiled){
            g_NameFiled.setText("");
        }
        if (e.getSource() == g_AmountFiled){
            g_AmountFiled.setText("");
        }
        if (e.getSource() == g_PirceFiled){
            g_PirceFiled.setText("");
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

    /**
     * 实现 ActionListener 中的方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){

            String g_code = g_CodeFiled.getText();                             // 获取输入 商品货号
            String g_name = g_NameFiled.getText();                             // 获得输入 商品名称
            Integer amount = Integer.parseInt(g_AmountFiled.getText());        // 获取输入 商品数量
            Double pirce = Double.parseDouble(g_PirceFiled.getText());         // 获取输入 商品单价
            Goods goods = new Goods(g_code,g_name,pirce,amount);

            goodsService.addGoods(goods);                                      // 将数据 持久化到数据库
            this.adminManagerFrame.setModel(goods);                            // 增加一行
            dispose();                                                         // 关闭当前窗体
        }
        /*
        if (e.getSource() == batchButton){
            // 批量添加 。。。点击按钮 弹出文件选择器 在本地选择excl 文件
            JFileChooser jFileChooser = new JFileChooser();
            String filePath = "";
            File file = null;
            int n = jFileChooser.showOpenDialog(null);
            if (n == JFileChooser.APPROVE_OPTION){
                file = jFileChooser.getSelectedFile();
                filePath = file.getAbsolutePath();
                System.out.println("绝对路径："+filePath);
                List<Goods> goodss = goodsService.getAllGoodsByExcl(filePath);
                for (Goods good :goodss
                ) {
                    goodsService.addGoods(good);
                    this.adminManagerFrame.setModel(good);                            // 增加一行
                }
                dispose();
            }else if (n == JFileChooser.CANCEL_OPTION){
                JOptionPane.showConfirmDialog(null,"没有选择文件");
            }else {
                JOptionPane.showConfirmDialog(null,"操作出现错误");
            }
        }

         */
    }
}

