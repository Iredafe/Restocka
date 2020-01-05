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
public class Payments {
    PreparedStatement pst;
    ResultSet rs;
    public void viewPayments(DateChooserCombo startDatePaymentdateChooserCombo, 
                             DateChooserCombo endDatePaymentdateChooserCombo, JTable paymentjTable) {
        //convert date to localdate and then to string
        String startDate = startDatePaymentdateChooserCombo.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
            LocalDate startDatePaymentLocalDate = LocalDate.parse(startDate, formatter);
            String startDatePayment = startDatePaymentLocalDate.toString();
        String endDate = endDatePaymentdateChooserCombo.getText();    
            LocalDate endDatePaymentLocalDate = LocalDate.parse(endDate, formatter);
            String endDatePayment = endDatePaymentLocalDate.toString();
        //cast JTable to DefaultTableModel
        DefaultTableModel paymentjTableDt= (DefaultTableModel) paymentjTable.getModel();
        //empty table
        paymentjTableDt.setRowCount(0);
        //get info about expenses from database
        String queryExpenses = "SELECT * FROM payments WHERE Date BETWEEN " + "'" + startDatePayment + 
                               "' AND " + "'" + endDatePayment + "'";
        try {
            pst= MySqlConnection.getConnection().prepareStatement(queryExpenses);
            rs=pst.executeQuery();
            while (rs.next())
            {
                String datePayment =rs.getString("Date");
                String supplierNamePayment =rs.getString("Supplier Name");
                Double balancePayment=rs.getDouble("Balance Payment");
                Double balanceReceivable =rs.getDouble("Balance Receiveable");
                String signedByPayment =rs.getString("Signed By");
                String paymentType = rs.getString("Payment Type");
                Double amountPayment =rs.getDouble("Amount");
                String namePayment =rs.getString("Name");
                
                //add to table
                paymentjTableDt.addRow(new Object[] {datePayment, supplierNamePayment, balancePayment, 
                                                     balanceReceivable, signedByPayment, paymentType, 
                                                     amountPayment, namePayment});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
