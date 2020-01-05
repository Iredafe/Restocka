/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import datechooser.beans.DateChooserCombo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class Expenses {
    PreparedStatement pst;
    ResultSet rs;
    
    public void addExpensesToDb(DateChooserCombo dateExpensesdateChooserCombo, JTextField amountExpensesjTextField,
                           JTextField othersExpensesjTextField, JTextArea descriptionExpensesjTextArea, 
                           JComboBox typeExpensesjComboBox) {
        String date = dateExpensesdateChooserCombo.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
            LocalDate dateExpensesSql = LocalDate.parse(date, formatter);
            String dateExpensesSqlString = dateExpensesSql.toString();
        String sql="INSERT INTO `stockmgt_db`.`Expenses`"
                    +"(`Date`, `Amount Spent`, `Description`, `Type`, `Others`)"
                    +"VALUES (?,?,?,?,?)";
        try{
            pst = MySqlConnection.getConnection().prepareStatement(sql);
            pst.setString(1, dateExpensesSqlString);
            pst.setString(2, amountExpensesjTextField.getText());
            pst.setString(3, descriptionExpensesjTextArea.getText());
            String typeExpenses =typeExpensesjComboBox.getSelectedItem().toString();            
            pst.setString(4,typeExpenses);
            pst.setString(5,othersExpensesjTextField.getText());
            
             if (pst.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Submitted \n");       
                } else {
                    JOptionPane.showMessageDialog(null, "An Error Occured \n");
                }
        }
        catch (HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public void viewExpenses(JTable expensesjTable, DateChooserCombo startDateExpensesdateChooserCombo, 
                             DateChooserCombo endDateExpensesdateChooserCombo) {
        //convert date to localdate and then to string
        String startDate = startDateExpensesdateChooserCombo.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
            LocalDate startDateExpensesSql = LocalDate.parse(startDate, formatter);
            String startDateExpensesSqlString = startDateExpensesSql.toString();
        String endDate = endDateExpensesdateChooserCombo.getText();    
            LocalDate endDateExpensesSql = LocalDate.parse(endDate, formatter);
            String endDateExpensesSqlString = endDateExpensesSql.toString();
        //cast JTable to DefaultTableModel
        DefaultTableModel expensesjTableDt= (DefaultTableModel) expensesjTable.getModel();
        //empty out table
        expensesjTableDt.setRowCount(0);
        //get info about expenses from database
        String queryExpenses = "SELECT * FROM Expenses WHERE Date BETWEEN " + "'" + startDateExpensesSqlString + 
                               "' AND " + "'" + endDateExpensesSqlString + "'";
        try {
            pst= MySqlConnection.getConnection().prepareStatement(queryExpenses);
            rs=pst.executeQuery();
            while (rs.next())
            {
                String Date=rs.getString("Date");
                Double Amount=rs.getDouble("Amount Spent");
                String Description=rs.getString("Description");
                String Type=rs.getString("Type");
                String Other=rs.getString("Others");
                
                //add to table
                expensesjTableDt.addRow(new Object[] {Date, Amount, Description, Type, Other});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
