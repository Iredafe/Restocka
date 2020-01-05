/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import stockmgtpackage.MySqlConnection;
import javax.swing.JTextArea;



/**
 *
 * @author Iredafe
 */
public class MainDashboardProperties {
ResultSet rs;
PreparedStatement pst;

 public void addToProductCatalogInDashboard (JTextArea productCatalogDashboardTextArea){   

//this code below populates the product catalogue text area in dashboard
productCatalogDashboardTextArea.setText(null);



String queryForProductCatalog = "SELECT product_category,"
        + "product_name,quantity_remaining FROM products";

      try {
        
            pst = MySqlConnection.getConnection().prepareStatement(queryForProductCatalog);
            rs = pst.executeQuery();
           
           
           while (rs.next()) {

                //get product catalog details from db as String variables
                
                
                String productCategoryString = rs.getString("product_category");
                String productNameString = rs.getString("product_name");
              Double quantityRemainingString = rs.getDouble("quantity_remaining");
                
           

          productCatalogDashboardTextArea.append("    \nProduct Catalogue \n\n"
                  + "These are the Products that are \n "
                  + "currently available in the store:\n\n" +
                
        "Product Category:"+productCategoryString+"\n" +
        "Product Name:\t"+productNameString+"\n" +
        "Stock Remaining:"+quantityRemainingString+"\n\n"+    
                
        "=================="
 );

           }}
catch(Exception e){
      JOptionPane.showMessageDialog(null, e);
          
        }

    
    
    
}
 
 
 public void addToProductsAboutToExpireInDashboard (JTextArea expiringSoonDashboardJTextArea){
 
 
 //this code below populates the "products about to expire" text area in dashboard

      expiringSoonDashboardJTextArea.setText(null);

String productsAboutToExpireQuery = "SELECT product_category,"
   + "product_name,batch_no,expiry_date FROM products WHERE "
   + "expiry_date BETWEEN CURDATE() AND (CURDATE() + INTERVAL 1 MONTH)";


      try {
        
            pst = MySqlConnection.getConnection().prepareStatement(productsAboutToExpireQuery);
            rs = pst.executeQuery();
           
           
           while (rs.next()) {

                //get expiring soon product details from db as String variables
                
                
                String productCategoryString = rs.getString("product_category");
                String productNameString = rs.getString("product_name");
                String batchNoString = rs.getString("batch_no");
                String expiryDateString = rs.getString("expiry_date");


          expiringSoonDashboardJTextArea.append("    \nProducts Expiring Soon \n\n"
                  + "These are the Products that are \n "
                  + "about to expire:\n\n" +
                
        "Product Category:"+productCategoryString+"\n" +
        "Product Name:\t"+productNameString+"\n" +
        "Batch Number:\t"+batchNoString+"\n" +
        "Expiry Date:"+expiryDateString+"\n\n"+    
                
        "=================="
 );

           }}
catch(Exception e){
      JOptionPane.showMessageDialog(null, e);
          
        }
 
 }

public void addToProductsDueForRestockInInDashboard (JTextArea productsDueForRestockDashboardJTextArea){


//this code below populates the "products due for restock" text area in dashboard

      productsDueForRestockDashboardJTextArea.setText(null);

String productsDueForRestockQuery = "SELECT product_category,"
   + "product_name,quantity_remaining,minimum_stock FROM products WHERE "
   + "quantity_remaining <= minimum_stock + 25";


      try {
        
            pst = MySqlConnection.getConnection().prepareStatement(productsDueForRestockQuery);
            rs = pst.executeQuery();
           
           
           while (rs.next()) {

                //get products due for restock details from db as String variables
                
                
                String productCategoryString = rs.getString("product_category");
                String productNameString = rs.getString("product_name");
                String minimumStockString = rs.getString("minimum_stock");
                String qttyRemainingString = rs.getString("quantity_remaining");


         productsDueForRestockDashboardJTextArea.append("    \nProducts that need Restock \n\n"
                  + "These are the Products that are \n "
                  + "almost running out:\n\n" +
                
        "Product Category:"+productCategoryString+"\n" +
        "Product Name:\t"+productNameString+"\n" +
        "Minimum Stock Limit:"+minimumStockString+"\n" +
        "Stock Available:"+qttyRemainingString+"\n\n"+    
                
        "=================="
 );

           }}
catch(Exception e){
      JOptionPane.showMessageDialog(null, e);
          
        }
      
}


public void addToProductsDueForReshelfInInDashboard (JTextArea productsDueForReshelfDashboardJTextArea){


//this code below populates the "products due for reshelf" text area in dashboard

productsDueForReshelfDashboardJTextArea.setText(null);

String productsDueForReshelfQuery = "SELECT product_category,"
   + "product_name,quantity_in_shelf,minimum_shelf FROM products WHERE "
   + "quantity_in_shelf <= minimum_shelf + 25";


      try {
        
            pst = MySqlConnection.getConnection().prepareStatement(productsDueForReshelfQuery);
            rs = pst.executeQuery();
           
           
           while (rs.next()) {

                //get products due for reshelf details from db as String variables
                
                
                String productCategoryString = rs.getString("product_category");
                String productNameString = rs.getString("product_name");
                String minimumShelfString = rs.getString("minimum_shelf");
                String shelfQttyRemainingString = rs.getString("quantity_in_shelf");


          productsDueForReshelfDashboardJTextArea.append("    \nProducts that need Reshelving \n\n"
                  + "These are the Products that are \n "
                  + "almost running out on the Shelf:\n\n" +
                
        "Product Category:"+productCategoryString+"\n" +
        "Product Name:\t"+productNameString+"\n" +
        "Minimum Shelf Limit:"+minimumShelfString+"\n" +
        "Quantity in Shelf:"+shelfQttyRemainingString+"\n\n"+    
                
        "=================="
 );

           }}
catch(Exception e){
      JOptionPane.showMessageDialog(null, e);
          
        }
      
}




}