/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class PointOfSales {
    PreparedStatement pst;
    ResultSet rs;
    public void generateReceipt(JTextArea receiptJTextArea, JTable POStablejTable, JComboBox productNamePOSjComboBox,
                                JRadioButton cashRadioButton,JRadioButton cardRadioButton, JTextField posQuantityTextField) {
        if (!cardRadioButton.isSelected() && !cashRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null,"Select the method of payment");
        } else {
            /*prepare receipt*/
            //clear text area
            receiptJTextArea.setText(null);

            //prepare shop name and address
            receiptJTextArea.append("\tName of Shop\n\tAddress Line 1\n\t Address Line 2\n\n");

            //prepare invoice number
            String currentDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            Random myRandom = new Random();
            int invoiceSerialNumber;
            String invoiceSerialNumberString;
            String invoiceNumber;

            //check if invoice number has already been generated before
            //create a set
            Set<String> mySet = new HashSet<String>();

            //add invoice numbers from database into set
            String queryInvoiceNumber = "SELECT invoice_number FROM receipts";
            try {
                pst = MySqlConnection.getConnection().prepareStatement(queryInvoiceNumber);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String invoiceNumberDb = rs.getString("invoice_number");
                    mySet.add(invoiceNumberDb);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            //check if new invoice number is already in database
            do {
                invoiceSerialNumber = myRandom.nextInt(9999);
                invoiceSerialNumberString = String.format("%04d", invoiceSerialNumber);
                invoiceNumber = currentDate + invoiceSerialNumberString;
            }
            while (mySet.contains(invoiceNumber));
            receiptJTextArea.append("Invoice Number: " + invoiceNumber);

            //prepare receipt date and time
            receiptJTextArea.append("\t" + LocalDate.now()+ "   "+ LocalTime.now() + "\n\n");

            //prepare lines of receipt that contain items and prices
            //variables
            int row;
            String productNameTable;
            double totalSales = 0;
            double quantityTable, priceTable, totalTable,discountTable, totalAfterDiscountTable;
            String totalTableFormatted, priceTableFormatted, totalAfterDiscountTableFormatted, totalSalesFormatted = null;
            String discountTableString;
            String productIdDataBase = null;
            //String productNamePOS = (String) productNamePOSjComboBox.getSelectedItem();
            double quantityRemainingPOS = 0;
            String cashPayment = cashRadioButton.getText();
            String cardPayment = cardRadioButton.getText();
            //double posQuantity = Double.parseDouble(posQuantityTextField.getText());
            for (row = 0; row < POStablejTable.getRowCount(); row++) {
                productNameTable = (String)POStablejTable.getValueAt(row, 0);
                quantityTable = (double) POStablejTable.getValueAt(row, 1);
                priceTable = (double) POStablejTable.getValueAt(row, 2);
                totalTable = (double) POStablejTable.getValueAt(row, 3);
                discountTable = (double) POStablejTable.getValueAt(row, 4);
                totalAfterDiscountTable = (double) POStablejTable.getValueAt(row, 5);
                totalSales = totalSales + totalAfterDiscountTable;
                
                //put the values in the right decimal format
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                totalTableFormatted = formatter.format(totalTable);
                priceTableFormatted = formatter.format(priceTable);
                totalAfterDiscountTableFormatted = formatter.format(totalAfterDiscountTable);
                totalSalesFormatted = formatter.format(totalSales);
                
                //check if there is any discount
                if (discountTable == 0) {
                    discountTableString = "";
                } else {
                    discountTableString = Double.toString(discountTable) + "% off";
                }
                //
                if (totalSales == totalAfterDiscountTable) {
                    totalTableFormatted = "";
                }
                
                //add items and details to receipt
                receiptJTextArea.append(quantityTable + " x\n" + productNameTable + "\t" + 
                                        priceTableFormatted + "\t" + totalTableFormatted + "\t" + discountTableString + "\t" + 
                                        totalAfterDiscountTableFormatted + "\n");
                //update quantity remaining on products table
                //get quantity remaining from database
                String queryQuantitySupplied = "SELECT quantity_remaining FROM products "
                                         + "WHERE product_name = " + "'" + productNameTable + "'";
                try {
                    pst = MySqlConnection.getConnection().prepareStatement(queryQuantitySupplied);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        quantityRemainingPOS = rs.getDouble("quantity_remaining");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                //update quantity remaining after making sales
                double newQuantityRemainingPOS = quantityRemainingPOS - quantityTable;
                String queryUpdateQuantity = "UPDATE products SET quantity_remaining = " + "'" + newQuantityRemainingPOS + "' "
                                           + "WHERE product_name = " + "'" + productNameTable + "'";
                try {
                    pst = MySqlConnection.getConnection().prepareStatement(queryUpdateQuantity);
                    pst.executeUpdate();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                //add to sales table 
                //get product id of products
                String queryProductId = "SELECT product_id FROM products "
                                             + "WHERE product_name = " + "'" + productNameTable + "'";
                try {
                    pst = MySqlConnection.getConnection().prepareStatement(queryProductId);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        productIdDataBase = rs.getString("product_id");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
        
                //add productId with other product details to database    
                String queryaddtoSales = "INSERT INTO sale (invoice_number, product_id, quantity, discount, total) "
                                      + "VALUES (?,?,?,?,?)";
                try {
                    pst = MySqlConnection.getConnection().prepareStatement(queryaddtoSales);
                    pst.setString(1, invoiceNumber);
                    pst.setString(2, productIdDataBase);
                    pst.setDouble(3, quantityTable);
                    pst.setDouble(4, discountTable);
                    pst.setString(5, totalAfterDiscountTableFormatted); 
                    pst.executeUpdate();
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                }    
            }

            //line with total and contact
            receiptJTextArea.append("\t\t\t\t-------------\n");
            receiptJTextArea.append("\t\t\tTotal\t" + totalSalesFormatted+"\n\n");
            receiptJTextArea.append("\t\tTel: 0815 595 4575\n\t\tEmail: dafpriseg@gmail.com");
            
            /*add invoice to database*/
            String queryAddReceipt = "INSERT INTO receipts (invoice_number, date_made, total, payment_method) "
                                       + "VALUES (?,?,?,?)";
            try {
                pst = MySqlConnection.getConnection().prepareStatement(queryAddReceipt);
                pst.setString(1, invoiceNumber);
                pst.setString(2, LocalDate.now()+ " "+ LocalTime.now());
                pst.setString(3,totalSalesFormatted);
                if (cashRadioButton.isSelected()) {
                    pst.setString(4, cashPayment);
                } else if (cardRadioButton.isSelected()) {
                    pst.setString(4, cardPayment);
                }
                //check if data was added to database
                if (pst.executeUpdate()> 0) {
                    JOptionPane.showMessageDialog(null, "Receipt was successfully added");
                } else {
                    JOptionPane.showMessageDialog(null,"Receipt was NOT added");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
    }
    
    public void clearReceipt(JTextArea receiptJTextArea) {
        int userConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the receipt?", 
                                                                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (userConfirmation == JOptionPane.YES_OPTION) {
                receiptJTextArea.setText(null);
            }
    }
}
