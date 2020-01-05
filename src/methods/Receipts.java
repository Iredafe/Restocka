/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import stockmgtpackage.MySqlConnection;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bulouere
 */
public class Receipts {
    PreparedStatement pst;
    ResultSet rs;
    public void addInvoiceNumbersToCombo(JComboBox invoiceNumbersJComboBox) {
        //get invoice numbers from database
        String queryGetInvoiceNumber = "SELECT invoice_number FROM receipts";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryGetInvoiceNumber);
            rs = pst.executeQuery();
            while (rs.next()) {
                String invoiceNumber = rs.getString("invoice_number");
                //add them to the combo box
                DefaultComboBoxModel invoiceNumbersDefaultComboBox = (DefaultComboBoxModel) invoiceNumbersJComboBox.getModel();
                if (invoiceNumbersDefaultComboBox.getIndexOf(invoiceNumber)== -1) {
                        invoiceNumbersJComboBox.addItem(invoiceNumber);  
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void viewReceipt(JComboBox invoiceNumbersJComboBox, JTextField invoiceNumberReceiptsJTextField,
                            JTextField dateTimeReceiptsJTextField, JTextField totalReceiptsJTextField,
                            JTextField paymentMethodReceiptsJTextField, JTable salesJTable) {
        //get value of selected invoice number
        String invoiceNumber = (String) invoiceNumbersJComboBox.getSelectedItem();
        //display details of receipt based on selected invoice number
        //get details of invoice from database
        String queryGetInvoice = "SELECT * FROM receipts WHERE invoice_number = " + "'" + invoiceNumber + "' ";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryGetInvoice);
            rs = pst.executeQuery();
            while (rs.next()) {
                String invoiceNumberDatabase = rs.getString("invoice_number");
                String dateMadeDatabase = rs.getString("date_made");
                String totalDatabase = rs.getString("total");
                String paymentMethodDatabase = rs.getString("payment_method");
                //display details of invoice in textfields
                invoiceNumberReceiptsJTextField.setText(invoiceNumberDatabase);
                dateTimeReceiptsJTextField.setText(dateMadeDatabase);
                totalReceiptsJTextField.setText(totalDatabase);
                paymentMethodReceiptsJTextField.setText(paymentMethodDatabase);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        //add sales info table based on selected invoice number
        DefaultTableModel salesJTableDt= (DefaultTableModel) salesJTable.getModel();
        //clear table
        salesJTableDt.setRowCount(0);
        String queryGetSale = "SELECT * FROM sale INNER JOIN products ON products.product_id = sale.product_id WHERE sale.invoice_number = " +"'"+invoiceNumber+"'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryGetSale);
            rs = pst.executeQuery();
            while (rs.next()) {
                String productIdDatabase = rs.getString("product_id");
                Double quantityDatabase = rs.getDouble("quantity");
                Double discountDatabase = rs.getDouble("discount");
                String totalDatabase = rs.getString("total");
                String productNameDatabase = rs.getString("product_name");
                String batchNumberDatabase = rs.getString("batch_no");
                //add to table        
                salesJTableDt.addRow(new Object[] {productIdDatabase, productNameDatabase, 
                                                   batchNumberDatabase, quantityDatabase, 
                                                   discountDatabase, totalDatabase});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
