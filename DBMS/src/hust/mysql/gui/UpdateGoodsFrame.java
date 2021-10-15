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
import java.util.List;

/**
 * Created by 戴宪锁 on 2017-05-25.
 */
public class UpdateGoodsFrame extends JFrame implements ActionListener,MouseListener{
    private AdminManagerFrame adminManagerFrame;
    private JPanel mainPanel;
    private JTextField g_CodeFiled,g_NameFiled,g_PirceFiled,g_AmountFiled;
    private JButton addButton,cancelButton;

    // 自定义变量
    private List<Goods> goodList = null;
    private Goods goods = null;

    // Service
    private GoodsService goodsService = new GoodsServiceImp();


    public UpdateGoodsFrame(AdminManagerFrame adminManagerFrame,Goods goods){
        this.goods = goods;
        this.adminManagerFrame = adminManagerFrame;
        init();
        setTitle("修改商品信息");
        setSize(320,350);
        setLocationRelativeTo(null);
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

        g_CodeFiled.setText(goods.getG_id());
        g_NameFiled.setText(goods.getG_name());
        g_AmountFiled.setText(goods.getAmount()+"");
        g_PirceFiled.setText(goods.getPrice()+"");

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        addButton = new JButton("保存");
        cancelButton = new JButton("取消");
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);
        jPanel.add(addButton);
        jPanel.add(cancelButton);

        mainPanel.add(g_CodeFiled);
        mainPanel.add(g_NameFiled);
        mainPanel.add(g_AmountFiled);
        mainPanel.add(g_PirceFiled);
        mainPanel.add(jPanel);

        add(mainPanel);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            String g_code = g_CodeFiled.getText();                             // 获取输入 商品货号
            String g_name = g_NameFiled.getText();                             // 获得输入 商品名称
            Integer amount = Integer.parseInt(g_AmountFiled.getText());        // 获取输入 商品数量
            Double pirce = Double.parseDouble(g_PirceFiled.getText());         // 获取输入 商品单价
            Goods goods = new Goods(g_code,g_name,pirce,amount);
            goodsService.updateGoods(goods);
            goodList = goodsService.getAll();
            this.adminManagerFrame.setGoodsTable(goodList);
//            this.adminManagerFrame.getGoodsTable(goodList);
            dispose();
        }
        if (e.getSource() == cancelButton){
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
