package hust.mysql.difficlt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import java.awt.*;

/**
 * Created by 戴宪锁 on 2017-05-27.
 *  柱状图 测试类
 *  参考文献：http://blog.csdn.net/Marksinoberg/article/details/50096325
 */
public class BarChartDemo {
    private ChartPanel panel;
    private String[] names;
    private Integer[] values;


    /**
     * 在柱状图中创建表格的步骤如下： 1、创建表格chart,需要注意相关参数的含义， 2、传进去的数据集是CategoryDataset格式
     * 3、获得表格区域块，设置横轴，纵轴及相关字体（防止出现乱卡的状况）
     * 4、设置chart的图例legend，并设置条目的字体格式（同样是为了防止出现乱码）
     */
    public BarChartDemo(String[] names,Integer[] values) {
        this.names = names;
        this.values = values;
        CategoryDataset dataset = (CategoryDataset) getDataset(names,values);
        JFreeChart chart = ChartFactory.createBarChart3D("商品销售统计", "横坐标",
                "纵坐标", dataset, PlotOrientation.VERTICAL, true, false, false);

        CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setLabelFont(new Font("宋体", Font.BOLD, 20));
        axis.setTickLabelFont(new Font("宋体", Font.BOLD, 20));

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 20));

        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 20));
        chart.getTitle().setFont(new Font("黑体", Font.ITALIC, 22));

        panel = new ChartPanel(chart, true);
    }

    public ChartPanel getChartPanel() {
        return panel;
    }

    /**
     * 需要注意的是在向数据集中添加数据的时候 使用的是dataset.addValue()方法，而在饼状图的数据集添加数据的过程中，使用的是dataset.setValue()方法
     * 这一点应该尤其注意。以免出错！
     * @return
     */
    private static Dataset getDataset(String[] names, Integer[] values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < names.length; i++) {
            dataset.addValue(values[i],names[i],"");
        }
//        dataset.addValue(100, "张10", "幼儿园");
        return dataset;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setLayout(new FlowLayout());
////        frame.add(new BarChartDemo().getChartPanel());
//        frame.setSize(1000, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }



}

