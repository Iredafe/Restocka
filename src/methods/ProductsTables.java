/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stockmgtpackage.MySqlConnection;

/**
 *
 * @author bulouere
 */
public class ProductsTables {
    PreparedStatement pst;
    ResultSet rs;
    public void addProductsToTable(JTable productsjTable, JComboBox productNamejComboBox) {
        String productName = (String) productNamejComboBox.getSelectedItem();
        //cast table to defaulttablemodel
        DefaultTableModel productsjTableDt=(DefaultTableModel) productsjTable.getModel();
        //empty table
        productsjTableDt.setRowCount(0);
        //fetch products from database
        String queryAddProductsToTable = "SELECT * FROM products WHERE product_name = " + "'" + productName + "'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryAddProductsToTable);
            rs = pst.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productCategory = rs.getString("product_category");
                       productName = rs.getString("product_name");
                String vendorName = rs.getString("vendor_name");
                String batchNo = rs.getString("batch_no");
                Double costPrice = rs.getDouble("cost_price");
                Double sellingPrice = rs.getDouble("selling_price");
                Double quantitySupplied = rs.getDouble("quantity_supplied");
                Double quantityRemaining = rs.getDouble("quantity_remaining");
                Double minimumStock = rs.getDouble("minimum_stock");
                String manufacturingDate = rs.getString("manufacturing_date");
                String expiryDate = rs.getString("expiry_date");
                String supplyDate = rs.getString("supply_date");
                
                //add selected products to table
                productsjTableDt.addRow(new Object[] {
                    productId, productCategory, productName, vendorName, batchNo, costPrice, 
                    sellingPrice, quantitySupplied, quantityRemaining, minimumStock, 
                    manufacturingDate, expiryDate, supplyDate
                }); 
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void addAllProductsToTable (JTable productsjTable) {
         //cast table to defaulttablemodel
        DefaultTableModel productsjTableDt=(DefaultTableModel) productsjTable.getModel();
        //fetch products from database
        String queryAddAllProductsToTable = "SELECT * FROM products";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryAddAllProductsToTable);
            rs = pst.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productCategory = rs.getString("product_category");
                String productName = rs.getString("product_name");
                String vendorName = rs.getString("vendor_name");
                String batchNo = rs.getString("batch_no");
                Double costPrice = rs.getDouble("cost_price");
                Double sellingPrice = rs.getDouble("selling_price");
                Double quantitySupplied = rs.getDouble("quantity_supplied");
                Double quantityRemaining = rs.getDouble("quantity_remaining");
                Double minimumStock = rs.getDouble("minimum_stock");
                String manufacturingDate = rs.getString("manufacturing_date");
                String expiryDate = rs.getString("expiry_date");
                String supplyDate = rs.getString("supply_date");
                
                //add selected products to table
                productsjTableDt.addRow(new Object[] {
                    productId, productCategory, productName, vendorName, batchNo, costPrice, 
                    sellingPrice, quantitySupplied, quantityRemaining, minimumStock, 
                    manufacturingDate, expiryDate, supplyDate
                }); 
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void deleteProductsFromTable(JTable productsDeletejTable) {
        //cast table to defaulttablemodel
        DefaultTableModel productsDeletejTableDt=(DefaultTableModel) productsDeletejTable.getModel();
        
        //get column values at selected row
        int selectedRow = productsDeletejTable.getSelectedRow();
        String productIdDeleteTable = (String) productsDeletejTableDt.getValueAt(selectedRow, 0);
        String productNameDeleteTable = (String) productsDeletejTableDt.getValueAt(selectedRow, 2);
        String batchNoDeleteTable = (String) productsDeletejTableDt.getValueAt(selectedRow, 4);
        
        //delete selected row from database
        String queryDelete = "DELETE FROM products WHERE product_id = " + "'" + productIdDeleteTable + "' "
                            + "AND product_name = " + "'" + productNameDeleteTable + "' AND "
                            + "batch_no = " + "'" + batchNoDeleteTable + "'";
        try {
            pst = MySqlConnection.getConnection().prepareStatement(queryDelete);
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product was deleted");
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        //delete row from table
        productsDeletejTableDt.removeRow(selectedRow);
    }
}
