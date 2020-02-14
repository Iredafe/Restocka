/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import datechooser.beans.DateChooserCombo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author iredafe
 */
public class Products {
    PreparedStatement pst;
    ResultSet rs;
    public void addNewProductToDatabase(JComboBox existingProductCategoryJComboBox, JComboBox vendorNameJComboBox,
                              JTextField newProductCategoryJTextField, JTextField productNameJTextField,
                              JTextField batchNumberJTextField, JTextField productIdJTextField,
                              JTextField costPriceJTextField, JTextField sellingPriceJTextField,
                              JTextField quantitySuppliedJTextField, JTextField minimumStockJTextField,
                              DateChooserCombo manufacturingDateDateChooserCombo, DateChooserCombo expiryDateDateChooserCombo,
                              DateChooserCombo supplyDateDateChooserCombo) {
        
            //list of variables from textfields and comboboxes
            String existingProductCategory = (String) existingProductCategoryJComboBox.getSelectedItem();
            String newProductCategory = newProductCategoryJTextField.getText();
            String productCategory = null;
            String productName = productNameJTextField.getText();
            String batchNumber = batchNumberJTextField.getText();
            String productId = productIdJTextField.getText();
            double costPrice = Double.parseDouble(costPriceJTextField.getText());
            double sellingPrice = Double.parseDouble(sellingPriceJTextField.getText());
            double quantitySupplied = Double.parseDouble(quantitySuppliedJTextField.getText());
            double minimumStock = Double.parseDouble(minimumStockJTextField.getText());
            
            String vendorName = (String) vendorNameJComboBox.getSelectedItem();
            String manufacturingDateString = manufacturingDateDateChooserCombo.getText();
            String expiryDateString = expiryDateDateChooserCombo.getText();
            String supplyDateString = supplyDateDateChooserCombo.getText();
            
            //convert date strings to localdate and then back to string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
                LocalDate manufacturingDateLocalDate = LocalDate.parse(manufacturingDateString, formatter);
            String manufacturingDate = manufacturingDateLocalDate.toString();
                LocalDate expiryDateLocalDate = LocalDate.parse(expiryDateString, formatter);
            String expiryDate = expiryDateLocalDate.toString();
                LocalDate supplyDateLocalDate = LocalDate.parse(supplyDateString, formatter);
            String supplyDate = supplyDateLocalDate.toString();
            
            //check to make sure all fields are filled out before inserting into database
            if (existingProductCategory.equals("Select Category") && newProductCategory.equals(null)) {
                JOptionPane.showMessageDialog(null, "Select a product category or enter a new one");
            } else if (vendorName.equals("Select Vendor")) {
                JOptionPane.showMessageDialog(null, "Select a vendor");
            } else if (productName.equals(null) || batchNumber.equals(null) || productId.equals(null) ||
                costPriceJTextField.getText().equals(null) || sellingPriceJTextField.getText().equals(null) || 
                quantitySuppliedJTextField.getText().equals(null) || minimumStockJTextField.getText().equals(null) || 
                vendorName.equals(null) || manufacturingDateString.equals(null) ||
                expiryDateString.equals(null) || supplyDateString.equals(null)) {
                    JOptionPane.showMessageDialog(null, "Make sure you fill out all fields");
            } else {
                //insert into database
                String queryInsert = "INSERT INTO products (product_id, product_category, product_name, "
                            + "vendor_name, batch_no, cost_price, selling_price, quantity_supplied, quantity_remaining, "
                            + "minimum_stock, manufacturing_date, expiry_date, supply_date) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    pst = MySqlConnection.getConnection().prepareStatement(queryInsert);
                    pst.setString(1,productId);
                    if (existingProductCategory.equals("Select Category")) {
                        pst.setString(2, newProductCategory);
                    } else {
                        pst.setString(2, existingProductCategory);
                    }
                    pst.setString(3,productName);
                    pst.setString(4, vendorName);
                    pst.setString(5, batchNumber);
                    pst.setDouble(6, costPrice);
                    pst.setDouble(7,sellingPrice);
                    pst.setDouble(8, quantitySupplied);
                    pst.setDouble(9, quantitySupplied);
                    pst.setDouble(10, minimumStock);
                    pst.setString(11, manufacturingDate);
                    pst.setString(12, expiryDate);
                    pst.setString(13, supplyDate);

                    //check if data was added to database
                    if (pst.executeUpdate()> 0) {
                        JOptionPane.showMessageDialog(null, "Product was successfully added");
                    } else {
                        JOptionPane.showMessageDialog(null,"Product was NOT added");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
    }
    
    public void addNewProductToComboBox(JComboBox productNamePOSjComboBox) {
        String queryProductsCombo = "SELECT product_name, batch_no FROM products";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryProductsCombo);
            rs = pst.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("product_name");
                
                //add products with their batch number to combobox if not already in combobox
                DefaultComboBoxModel productNamePOSjComboBoxDc = (DefaultComboBoxModel) productNamePOSjComboBox.getModel();
                if (productNamePOSjComboBoxDc.getIndexOf(productName)== -1) {
                    productNamePOSjComboBox.addItem(productName);  
                }
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
    }
    
    public void updateProducts(JTextField batchNumberUpdateTextField, JTextField productIdUpdateTextField3,
                              JTextField costPriceUpdateTextField, JTextField sellingPriceUpdateTextField, 
                              JTextField manuDateUpdateTextField, JTextField expiryDateUpdateTextField, 
                              JTextField supplyDateUpdateTextField, JTextField minimumStockUpdateTextField,
                              JTextField quantitySuppliedUpdateTextField, JTextField quantityRemainingUpdateTextField,
                              JComboBox productName_updateJComboBox, JComboBox vendorNameComboBox) {
         //variables
        String batchNoUpdate = batchNumberUpdateTextField.getText();
        String productIdUpdate = productIdUpdateTextField3.getText();
        double costPriceUpdate = Double.parseDouble(costPriceUpdateTextField.getText());
        double sellingPriceUpdate = Double.parseDouble(sellingPriceUpdateTextField.getText());
        double quantityRemainingUpdate = Double.parseDouble(quantityRemainingUpdateTextField.getText());
        String manufacturingDateUpdate = manuDateUpdateTextField.getText();       
        String expiryDateUpdate = expiryDateUpdateTextField.getText();       
        String supplyDateUpdate = supplyDateUpdateTextField.getText();        
        double minimumStockUpdate = Double.parseDouble(minimumStockUpdateTextField.getText());        
        double quantitySuppliedUpdate = Double.parseDouble(quantitySuppliedUpdateTextField.getText());  
        String productNameUpdate = (String) productName_updateJComboBox.getSelectedItem();
        String vendorNameUpdate = (String) vendorNameComboBox.getSelectedItem();
        //update database what is in textfield
        String queryUpdate = "UPDATE products SET product_id = " + "'" + productIdUpdate + "', "
                                               + "batch_no = " + "'" + batchNoUpdate + "', "
                                               + "cost_price = " + "'" + costPriceUpdate + "', "
                                               + "selling_price = " + "'" + sellingPriceUpdate + "', "
                                               + "quantity_supplied = " + "'" + quantitySuppliedUpdate + "', "
                                               + "minimum_stock = " + "'" + minimumStockUpdate + "', "
                                               + "quantity_remaining = " + "'" + quantityRemainingUpdate + "', "
                                               + "manufacturing_date = " + "'" + manufacturingDateUpdate + "', "
                                               + "expiry_date = " + "'" + expiryDateUpdate + "', "
                                               + "supply_date = " + "'" + supplyDateUpdate + "', "
                                               + "vendor_name = " + "'" + vendorNameUpdate + "'"
                                               + "WHERE product_name = " + "'" + productNameUpdate + "' ";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryUpdate);
                if (pst.executeUpdate()> 0) {
                                JOptionPane.showMessageDialog(null,"Product was successfully updated");
                            } else {
                                JOptionPane.showMessageDialog(null,"Product was not updated");
                            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }                 
    }
    
    public void showProductForUpdate(JComboBox productName_updateJComboBox, JTextField batchNumberUpdateTextField,
                                     JTextField productIdUpdateTextField3, JTextField costPriceUpdateTextField,
                                     JTextField sellingPriceUpdateTextField, JTextField manuDateUpdateTextField,
                                     JTextField expiryDateUpdateTextField, JTextField supplyDateUpdateTextField,
                                     JTextField quantityRemainingUpdateTextField, JTextField minimumStockUpdateTextField,
                                     JTextField quantitySuppliedUpdateTextField, JComboBox vendorNameUpdateJComboBox) {

        //get selected product name as a String
        String productNameUpdate = (String) productName_updateJComboBox.getSelectedItem();
        
        //get details of product from database
        String queryProductComboBoxUpdate = "SELECT * FROM products "
                     + "WHERE product_name = " + "'" + productNameUpdate + "'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryProductComboBoxUpdate);
            rs = pst.executeQuery();
            while (rs.next()) {
                //get details of products from db as String variables
                String productIdUpdate = rs.getString("product_id");
                String batchNoUpdate = rs.getString("batch_no");
                String vendorNameUpdate = rs.getString("vendor_name");
                String costPriceUpdateString = String.valueOf(rs.getDouble("cost_price"));
                String sellingPriceUpdateString = String.valueOf(rs.getDouble("selling_price"));               
                String quantitySuppliedUpdateString = String.valueOf(rs.getDouble("quantity_supplied"));
                String quantityRemainingUpdateString = String.valueOf(rs.getDouble("quantity_remaining"));
                String minimumStockUpdateString = String.valueOf(rs.getDouble("minimum_stock"));              
                String manuDateUpdate = rs.getString("manufacturing_date");
                String expiryDateUpdate = rs.getString("expiry_date");
                String supplyDateUpdate = rs.getString("supply_date");
                
                //display details of products in the assigned textboxes
                batchNumberUpdateTextField.setText(batchNoUpdate);
                productIdUpdateTextField3.setText(productIdUpdate);
                costPriceUpdateTextField.setText(costPriceUpdateString);
                sellingPriceUpdateTextField.setText(sellingPriceUpdateString);
                manuDateUpdateTextField.setText(manuDateUpdate);       
                expiryDateUpdateTextField.setText(expiryDateUpdate);       
                supplyDateUpdateTextField.setText(supplyDateUpdate); 
                quantityRemainingUpdateTextField.setText(quantityRemainingUpdateString);
                minimumStockUpdateTextField.setText(minimumStockUpdateString);        
                quantitySuppliedUpdateTextField.setText(quantitySuppliedUpdateString); 
                vendorNameUpdateJComboBox.setSelectedItem(vendorNameUpdate);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
