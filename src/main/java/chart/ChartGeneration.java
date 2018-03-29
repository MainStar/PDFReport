package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class ChartGeneration {

    private JFreeChart chart;
    private PiePlot plot;

    public JFreeChart generateChartPie(int successful, int failed, int skipped){

        double testNumber = successful + failed + skipped;
        System.out.println(successful/testNumber*100);

        DefaultPieDataset dataset = new DefaultPieDataset();
        if (successful != 0) {
            dataset.setValue(String.valueOf("Successful: " + successful), (successful/testNumber)*100);
        }else {
            dataset.setValue(String.valueOf("Successed: " + 0), 0);
        }
        if (failed != 0) {
            dataset.setValue(String.valueOf("Failed: " + failed), (failed/testNumber)*100);
        }else {
            dataset.setValue(String.valueOf("Failed: " + 0), 0);
        }
        if (skipped != 0){
            dataset.setValue(String.valueOf("Skipped: " + skipped), (skipped/testNumber)*100);
        }else {
            dataset.setValue(String.valueOf("Skipped: " + 0), 0);
        }

        JFreeChart chart = ChartFactory.createPieChart3D("", dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setForegroundAlpha(0.6f);
        plot.setCircular(true);
        plot.setSectionPaint(0, Color.decode("#019244"));
        plot.setSectionPaint(1, Color.decode("#EE6044"));
        plot.setSectionPaint(2, Color.decode("#F0AD4E"));
        plot.setBackgroundPaint(Color.white);
        Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        plot.setLabelOutlinePaint(transparent);
        plot.setLabelBackgroundPaint(transparent);
        plot.setLabelShadowPaint(transparent);
        plot.setLabelLinkPaint(Color.GRAY);
        Font font = new Font("SansSerif", Font.PLAIN, 10);
        plot.setLabelFont(font);
        //PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        //plot.setLabelGenerator(gen);

        return chart;
    }
}
