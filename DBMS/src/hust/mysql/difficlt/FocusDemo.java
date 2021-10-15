package hust.mysql.difficlt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FocusDemo extends JFrame {

    public FocusDemo()
    {
        MyTextField txt1 = new MyTextField("test1");
        MyTextField txt2 = new MyTextField("test2");
        MyTextField txt3 = new MyTextField("test3");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(txt1);
        getContentPane().add(txt2);
        getContentPane().add(txt3);
    }
    public static void main(String[] args)
    {
        FocusDemo f = new FocusDemo();
        f.setSize(300, 300);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        f.pack();
        f.setVisible(true);
    }
    class MyTextField extends JTextField
    {
        public MyTextField(String str)
        {
            super(str);
            this.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        e.consume();
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                    }
                }
            });
        }
    }
}
