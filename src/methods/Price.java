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
public class Price {
    PreparedStatement pst;
    ResultSet rs;
    public void showPricePOS(String productNamePOS, JTextField posPriceTextField) {
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
    
    public void showPriceInvoice(String batchNumberInvoice, JTextField unitPriceInvoiceJTextField) {
        String queryGetPrice = "SELECT selling_price FROM products WHERE batch_no = " + "'" + batchNumberInvoice + "'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryGetPrice);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                String sellingPriceInvoice = Double.toString(rs.getDouble("selling_price"));
                unitPriceInvoiceJTextField.setText(sellingPriceInvoice);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void linkQuantityAndTotalPrice(JTextField posPriceTextField, JTextField posQuantityTextField,
                                          JTextField posTotalTextField) {
        double posPrice = Double.parseDouble(posPriceTextField.getText());
        double posQuantity = Double.parseDouble(posQuantityTextField.getText());
        Double totalPrice = posPrice*posQuantity;
        String totalPriceString = totalPrice.toString();
        posTotalTextField.setText(totalPriceString);
    }
}
