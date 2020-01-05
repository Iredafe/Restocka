package stockmgtpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class MySqlConnection {

    public static Connection getConnection(){
        
    Connection con = null;

    String url= "jdbc:mysql://localhost:3306/stockmgt_db";
    
    String username= "root";
    
    String password = "";
       

    try{
    Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection(url, username, password);
    
    
    
}
catch(Exception e){JOptionPane.showMessageDialog(null, e);


}
return con;    
    }
}
