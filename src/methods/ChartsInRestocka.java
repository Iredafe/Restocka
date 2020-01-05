/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author Iredafe
 */
public class ChartsInRestocka {
    
public void addToCharts (JPanel salesChartPanel){

      try{
     String lineChartQuery = "select `Quantity`, `date_of_transaction` from `pointofsales`";
    JDBCCategoryDataset dataset = new JDBCCategoryDataset(MySqlConnection.getConnection(),lineChartQuery);        
     
     JFreeChart chart = ChartFactory.createLineChart("Sales Chart","Date",
              "Quantity", dataset, PlotOrientation.VERTICAL, false, true, false);
   
     ChartPanel panel = new ChartPanel(chart); 
    salesChartPanel.removeAll();
    salesChartPanel.add(panel);
    salesChartPanel.validate();
    
}catch(Exception e){
    
    JOptionPane.showMessageDialog(null, e);
      
}





}
}