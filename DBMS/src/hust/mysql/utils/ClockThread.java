package hust.mysql.utils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 戴宪锁 on 2017/3/21.
 */
public class ClockThread extends Thread{
    private JLabel jLabel;

    public void setjLabel(JLabel jLabel){
        this.jLabel = jLabel;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            Date date = new Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeString = simple.format(date);
            jLabel.setText(timeString);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("线程中断异常");
            }
        }
    }
}

