package methods;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class Invoice {
    PreparedStatement pst;
    ResultSet rs;
    public void addToCart (JComboBox productNameInvoiceJComboBox, JTextField quantityInvoiceJTextField,
                                           JTextField unitPriceInvoiceJTextField, JTextField discountInvoiceJTextField,
                                           JTable invoiceJTable) {
    //variables
        String productNameInvoice = productNameInvoiceJComboBox.getSelectedItem().toString();
        double discountInvoice;
        
    //make sure all fields are filled out
        if (productNameInvoice.equals("Select Product")) {
            JOptionPane.showMessageDialog(null, "Select a product");
        } else if (quantityInvoiceJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter a quantity");
        } else {
            try {    
                double priceInvoice = Double.parseDouble(unitPriceInvoiceJTextField.getText());
    //check if the discount field is empty
                if (discountInvoiceJTextField.getText().equals("")) {
                    discountInvoice = 0;
                } else {
                    discountInvoice = Double.parseDouble(discountInvoiceJTextField.getText());
                }   
                double quantityInvoice = Double.parseDouble(quantityInvoiceJTextField.getText());
                double totalInvoice = priceInvoice*quantityInvoice;
    //calculate total after discount
                double totalAfterDiscountInvoice = priceInvoice*((100 - discountInvoice)/100)*quantityInvoice;
    //add details of sale to table
                DefaultTableModel invoiceDefaultTable=(DefaultTableModel) invoiceJTable.getModel();
                Vector invoiceVector = new Vector();
                invoiceVector.add(productNameInvoice);
                invoiceVector.add(quantityInvoice);
                invoiceVector.add(priceInvoice);
                invoiceVector.add(totalInvoice);
                invoiceVector.add(discountInvoice);
                invoiceVector.add(totalAfterDiscountInvoice);

                //add row to table
                invoiceDefaultTable.addRow(invoiceVector);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Your quantity and discount must be a whole number or decimal");
            }
        }
    }
    
    public void deleteFromCart(JTable POSjTable) {
        int selectedRow = POSjTable.getSelectedRow();
        //cast Jtable to default table
        DefaultTableModel POSdefaultTable=(DefaultTableModel) POSjTable.getModel();
        //check if any row in the table was selected
        if (POSjTable.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select the row in the table you want to delete.");
        } else {
            //ask user to confirm that they want to clear all
            int userConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete from cart?", 
                                                                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (userConfirmation == JOptionPane.YES_OPTION) {
                //delete row from jtable
                POSdefaultTable.removeRow(selectedRow);
            }
        }
    }
    
    public void clearAll(JTextField posQuantityTextField, JTextField posPriceTextField,
                        JTextField posDiscountTextField, JTextField posTotalTextField, 
                        JComboBox productNamePOSjComboBox) {
        //ask user to confirm that they want to clear all
        int userConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all?", 
                                                                    "Confirm", JOptionPane.YES_NO_OPTION);
        if (userConfirmation == JOptionPane.YES_OPTION) {
            posQuantityTextField.setText(null);
            posPriceTextField.setText(null);
            posDiscountTextField.setText(null);
            posTotalTextField.setText(null);
            productNamePOSjComboBox.setSelectedItem("Select Product");
        }
    }
    
    public void linkProductAndBatchNumberCombos(JComboBox productNameInvoiceJComboBox, JComboBox batchNumberInvoiceJComboBox) {
        String productNameInvoice = (String) productNameInvoiceJComboBox.getSelectedItem();
        if (productNameInvoice.equals("Select Category")) {
            batchNumberInvoiceJComboBox.removeAllItems();
        } else {
            batchNumberInvoiceJComboBox.removeAllItems();
            //add products to combobox with product names based on selected category
            String queryProductCategoryComboBoxUpdate = "SELECT batch_no FROM products "
                                                          + "WHERE product_name = " + "'" + productNameInvoice + "'";
            try {
                pst = MySqlConnection.getConnection().prepareStatement(queryProductCategoryComboBoxUpdate);
                rs = pst.executeQuery();
                        
                while (rs.next()) {
                    String batchNumberDatabase = rs.getString("batch_no");
                            
                    //add product names to combobox if not already in combobox
                    DefaultComboBoxModel batchNumberInvoiceDefaultComboBox = (DefaultComboBoxModel) batchNumberInvoiceJComboBox.getModel();
                    if (batchNumberInvoiceDefaultComboBox.getIndexOf(batchNumberDatabase)== -1) {
                        batchNumberInvoiceJComboBox.addItem(batchNumberDatabase);  
                    }
                }
            } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
