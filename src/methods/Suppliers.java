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
public class Suppliers {
    PreparedStatement pst;
    ResultSet rs;
    
    public void addVendorToCombo(JComboBox vendorNameJComboBox, JComboBox vendorNameUpdateJComboBox) {
        String queryCombo = "SELECT vendorname FROM suppliers_details";
        try {
            PreparedStatement pst = MySqlConnection.getConnection().prepareStatement(queryCombo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String productCategory = rs.getString("vendorname");
                //add product categories to combobox if not already in combobox
                DefaultComboBoxModel vendorNameDefaultComboBox = (DefaultComboBoxModel) vendorNameJComboBox.getModel();
                if (vendorNameDefaultComboBox.getIndexOf(productCategory)== -1) {
                    vendorNameJComboBox.addItem(productCategory);  
                }
                DefaultComboBoxModel vendorNameUpdateDefaultComboBox = (DefaultComboBoxModel) vendorNameUpdateJComboBox.getModel();
                if (vendorNameUpdateDefaultComboBox.getIndexOf(productCategory)== -1) {
                    vendorNameUpdateJComboBox.addItem(productCategory);  
                }
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
    }
}