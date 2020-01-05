/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import datechooser.beans.DateChooserCombo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class CashFlows {
    PreparedStatement pst;
    ResultSet rs;
    public void viewCashFlows(DateChooserCombo startDateCashFlowsdateChooserCombo, 
                              DateChooserCombo endDateCashFlowsdateChooserCombo, JTable cashFlowsjTable) {
        //convert date to localdate and then to string
        String startDate = startDateCashFlowsdateChooserCombo.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
            LocalDate startDateCashFlowsLocalDate = LocalDate.parse(startDate, formatter);
            String startDateCashFlows = startDateCashFlowsLocalDate.toString();
        String endDate = endDateCashFlowsdateChooserCombo.getText();    
            LocalDate endDateCashFlowsLocalDate = LocalDate.parse(endDate, formatter);
            String endDateCashFlows = endDateCashFlowsLocalDate.toString();
        //cast JTable to DefaultTableModel
        DefaultTableModel cashFlowsjTableDt= (DefaultTableModel) cashFlowsjTable.getModel();
        //empty out table
        cashFlowsjTableDt.setRowCount(0);
        //get info about expenses from database
        String queryExpenses = "SELECT * FROM CashFlows WHERE Date BETWEEN " + "'" + startDateCashFlows + 
                               "' AND " + "'" + endDateCashFlows + "'";
        try {
            pst= MySqlConnection.getConnection().prepareStatement(queryExpenses);
            rs=pst.executeQuery();
            while (rs.next())
            {
                String date=rs.getString("Date");
                Double amount=rs.getDouble("Amount");
                String details=rs.getString("Details");
                String category=rs.getString("Category");
                String flowType =rs.getString("Flow Type");
                
                //add to table
                
                cashFlowsjTableDt.addRow(new Object[] {date, amount, details, category, flowType});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
