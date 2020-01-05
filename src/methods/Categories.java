/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class Categories {
    PreparedStatement pst;
    ResultSet rs;
    public void addCategoryToCombo(JComboBox productCategory_UpdateJComboBox, 
                          JComboBox existingProductCategoryComboBox, JComboBox productCategoryDeleteComboBox, 
                          JComboBox productCategoryViewjComboBox) {
        String queryCombo = "SELECT product_category FROM products";
        try {
            PreparedStatement pst = MySqlConnection.getConnection().prepareStatement(queryCombo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String productCategory = rs.getString("product_category");
                //add product categories to combobox if not already in combobox
                DefaultComboBoxModel productCategory_UpdateJComboBoxDc = (DefaultComboBoxModel) productCategory_UpdateJComboBox.getModel();
                if (productCategory_UpdateJComboBoxDc.getIndexOf(productCategory)== -1) {
                    productCategory_UpdateJComboBox.addItem(productCategory);  
                }
                DefaultComboBoxModel existingProductCategoryComboBoxDc = (DefaultComboBoxModel) existingProductCategoryComboBox.getModel();
                if (existingProductCategoryComboBoxDc.getIndexOf(productCategory)== -1) {
                    existingProductCategoryComboBox.addItem(productCategory);  
                }
                DefaultComboBoxModel productCategoryDeleteComboBoxDc = (DefaultComboBoxModel) productCategoryDeleteComboBox.getModel();
                if (productCategoryDeleteComboBoxDc.getIndexOf(productCategory)== -1) {
                    productCategoryDeleteComboBox.addItem(productCategory);  
                }
                DefaultComboBoxModel productCategoryViewjComboBoxDc = (DefaultComboBoxModel) productCategoryViewjComboBox.getModel();
                if (productCategoryViewjComboBoxDc.getIndexOf(productCategory)== -1) {
                    productCategoryViewjComboBox.addItem(productCategory);  
                }
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
    }
    
    public void linkCategoryAndProductCombos(JComboBox productCategory_UpdateJComboBox, JComboBox productName_updateJComboBox) {
        String productCategoryUpdate = (String) productCategory_UpdateJComboBox.getSelectedItem();
        if (productCategoryUpdate.equals("Select Category")) {
            productName_updateJComboBox.removeAllItems();
        } else {
            productName_updateJComboBox.removeAllItems();
            //add products to combobox with product names based on selected category
            String queryProductCategoryComboBoxUpdate = "SELECT product_name FROM products "
                                                          + "WHERE product_category = " + "'" + productCategoryUpdate + "'";
            try {
                pst = MySqlConnection.getConnection().prepareStatement(queryProductCategoryComboBoxUpdate);
                rs = pst.executeQuery();
                        
                while (rs.next()) {
                    String productNameUpdate = rs.getString("product_name");
                            
                    //add product names to combobox if not already in combobox
                    DefaultComboBoxModel productNameComboBoxDc = (DefaultComboBoxModel) productName_updateJComboBox.getModel();
                    if (productNameComboBoxDc.getIndexOf(productNameUpdate)== -1) {
                        productName_updateJComboBox.addItem(productNameUpdate);  
                    }
                }
            } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
