/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.awt.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class ShowPricePOS {
    PreparedStatement pst;
    ResultSet rs;
    public ShowPricePOS(String productNamePOS, JTextField posPriceTextField) {
        String queryGetPrice = "SELECT selling_price FROM products WHERE product_name = " + "'" + productNamePOS + "'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryGetPrice);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                String sellingPricePOS = Double.toString(rs.getDouble("selling_price"));
                posPriceTextField.setText(sellingPricePOS);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
