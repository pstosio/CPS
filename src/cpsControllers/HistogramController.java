package cpsControllers;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import java.awt.*;
import java.util.ArrayList;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class HistogramController {

    public JFreeChart createHistogram(ArrayList<Integer> time, ArrayList<Double> values){

HistogramDataset dataset = new HistogramDataset();
dataset.setType(HistogramType.FREQUENCY);

//dataset.addSeries("H1", double[], 20);
double[] target = new double[values.size()];
 for (int i = 0; i < target.length; i++) {
    target[i] = values.get(i);                
 }
//dataset.addSeries("H1", target, 0, 0.0, time.size());
dataset.addSeries("H1", target, time.size());

        String plotTitle = "Histogram";
        String xAxis = "Czas (t)";
        String yAxis = "Aplituda (A)";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        
        
        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis,
                dataset, orientation, show, toolTips, urls);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setRange(-20, 20);
        XYBarRenderer xybr = new XYBarRenderer();
        xybr.setMargin(15);
        xybr.setShadowVisible(true);
        chart.setBackgroundPaint(Color.white);
        
        

        return chart;
    }

    public JFreeChart createHistogram(ArrayList<Integer> time, ArrayList<Double> listSignalValues) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
