package hust.mysql.difficlt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PieChartDemo {
    private ChartPanel panel;
    private String[] names;
    private Integer[] values;

    /**
     * 创建饼状图的步骤如下： 1、创建一个饼状的实例，注意传参的格式，还有需要注意的是此时的数据集应该是defaultPieDataset，
     * 而不是CategoryDataset格式 2、获得饼状图的所在区域 3、设置两个格式化的数据格式，为后面的床架饼状图的实例做基础
     * 4、细节方面是对无数据、零值、负值等情况的处理 5、最后就是设置在出现汉字的地方进行字体内容的设置了（同样的，这是为了防止出现乱码的状况）
     */
    public PieChartDemo(String[] names,Integer[] values) {
        this.names = names;
        this.values = values;
        DefaultPieDataset dataset = getDataset(names,values);

        JFreeChart chart = ChartFactory.createPieChart3D("员工业绩统计", dataset,
                true, false, false);

        PiePlot piePlot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");
        NumberFormat nf = NumberFormat.getInstance();

        StandardPieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
                "{0} {2}",                   //获得StandardPieSectionLabelGenerator对象,生成的格式，{0}表示section名，
                //{1}表示section的值，{2}表示百分比。可以自定义
                nf, df);
        piePlot.setLabelGenerator(generator);// 设置百分比
        piePlot.setLabelFont(new Font("黑体", Font.ITALIC, 20));

        // 当饼状图内额米有数据时，作如下数据中设置
        piePlot.setNoDataMessage("此时并没有任何数据可用");
        piePlot.setCircular(false);
        piePlot.setLabelGap(0.02D);

        piePlot.setIgnoreNullValues(true);// 设置不显示空位
        piePlot.setIgnoreZeroValues(true);// 设置不显示负值或零值

        panel = new ChartPanel(chart, true);
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 20));


    }

    /**
     * 需要注意的是在向数据集中添加数据的时候调用的是dataset.setvalue()方法，而不是柱状图中的addValue()方法
     * 这一点应该尤其注意一下，以免在使用的时候出现错误
     * @return
     */
    private DefaultPieDataset getDataset(String[] names,Integer[] values) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < names.length; i++) {
            dataset.setValue(names[i],values[i]);
        }
        return dataset;
    }

    public ChartPanel getPieChartPanel() {
        return panel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setLayout(new FlowLayout());
////        frame.add(new PieChartDemo().getPieChartPanel());
//        frame.setSize(1000, 700);
//        frame.setVisible(true);
//    }



}
