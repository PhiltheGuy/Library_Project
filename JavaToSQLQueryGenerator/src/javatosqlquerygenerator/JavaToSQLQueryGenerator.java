
//Author: Philemon Holmes II
//Project: The_Library_Database
//
package javatosqlquerygenerator;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;



 
public class JavaToSQLQueryGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    //Frame Declaration
    Frame Author_Frame = new Frame("");
    Frame Genre_frame = new Frame("");
    Frame Page_num_frame = new Frame("");
    
    
    
    //End Frame Declaration
    
    //Button Declaration
        //Genre Button Declaration
        Button tab = new Button("Genre"); //Brings out tab of genre
        Button fantasy = new Button("Fantasy");
        Button dystopia = new Button("Dystopia");
        Button biography = new Button("Biography");
        Button scifi = new Button("Sci-Fi");
        Button horror = new Button("Horror");
        Button comedy = new Button("Comedy");    
        //
        
        //Author Tab Button
        
        
        
        
    //End button declaration
    
    

    //Adding buttons to frame
    
    //End adding buttons to frame



    
        
        //Query Generator
        int iii = 1;
        String queryPass = "SELECT title FROM book WHERE (";
        String genreType = "";
        String author = "";
        int minPage = 0;
        int maxPage = 9999999;
        String branchAt = ""; 
        
        
        
        if(/*genre button active*/ iii == 1)
        {
          queryPass = queryPass + " AND " + ( "genre = " + genreType);   
        }
        else if(iii != 1)
        {
          queryPass = queryPass + " AND " + ( "author = " + author);
        
        }
        else if (iii < 2)
        {
            queryPass = queryPass + " AND " + ( "page_number  " + author);
            
        }
        
        
        
        
        
        
        
    }
    
    
    //SQL Connection
    public static Connection getConexao() throws SQLException 
    {
        try {
            // Register the MySQL JDBC driver (necessary only for older versions of JDBC, but harmless here)
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated driver class for newer MySQL versions
            
            // Return a connection to the 'world' database on the MySQL server running locally
            return (javatosqlquerygenerator.Connection) DriverManager.getConnection("jdbc:mysql://localhost/LAPTOP-EBU3JLQG\\SQLEXPRESS", "root", "rootadmin");
        } catch (ClassNotFoundException e) {
            // Exception handling if the MySQL JDBC driver is not found
            throw new SQLException("MySQL JDBC Driver not found. Make sure the MySQL JDBC jar is added to your classpath.", e);
        } catch (SQLException e) {
            // Handle SQL exceptions related to the connection
            throw new SQLException("Connection failed. Please check your database credentials and URL.", e);
        }
    }
    
    
    
    

    //private static final String URL = "jdbc:sqlserver://LAPTOP-EBU3JLQG\\SQLEXPRESS:1433;databaseName=your_database_name";
    //private static final String USERNAME = "LAPTOP-EBU3JLQG\\Holme"; 
    //private static final String PASSWORD = ""; 

    private static final String URL = "jdbc:sqlserver://LAPTOP-EBU3JLQG\\SQLEXPRESS;databaseName=your_database_name;integratedSecurity=true;";

    public static Connection getConnection() throws SQLException {
        try {
      
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //return (javatosqlquerygenerator.Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return (javatosqlquerygenerator.Connection) DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }


    
    
    
   
}
