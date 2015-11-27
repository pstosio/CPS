/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsControllers;

import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author koperingnet
 */
public class LineChartController extends JFrame {
    
    /**
     * Method for create line chart.
     * 
     * @param t
     * @param values
     * @return 
     */
    public JFreeChart printChart(ArrayList<Integer> t, ArrayList<Double> values) {

    // Prepare the data set
    XYSeries xySeries = new XYSeries("Number & Square Chart");
    
    double[] val = new double[values.size()];
    for (int i = 0; i < val.length; i++) {
        xySeries.add(i, values.get(i));
    }
        
        
      
    XYDataset xyDataset = new XYSeriesCollection(xySeries);
    
    //Create the chart
    JFreeChart chart = ChartFactory.createXYLineChart(
        "Wykres liniowy", "Czas (t)", "Aplituda (A)", xyDataset,
        PlotOrientation.VERTICAL, false, true, false);

//    //Render the frame
//    ChartFrame chartFrame = new ChartFrame("Wykres sygnalu ", chart);
//    chartFrame.setVisible(true);
//    chartFrame.setSize(800, 600);
    
    return chart;
  }
}
