package databaseproject2024;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;



public class DatabaseProject2024 {

    public static void main(String[] args) {
        // Specify the SQL Server instance and database name
        Scanner kbReader = new Scanner(System.in);
        
        String server = "LAPTOP-EBU3JLQG";  
        String databaseName = "MyLibraryDatabase";  
        
        String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + databaseName + ";integratedSecurity=true;encrypt=false;";
        int menutest = 0;
        
        
        switch (menutest)
        {
            case 1:
                System.out.println("Enter a ");
                
            case 2:
                
            default:
            System.out.println("Please enter a valid command: ");
        
        }
        
        
        
        
        
        
        
    }
    
    
    
    //Interface used to query for books via the search function
    public static void book_search(String title)
    {
        String server = "LAPTOP-EBU3JLQG";  
        String databaseName = "MyLibraryDatabase";  
        String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + databaseName + ";integratedSecurity=true;encrypt=false;";

        // Start the query with IS NULL for check_out_by
        String queryGet = "SELECT title FROM Books WHERE check_out_by IS NULL AND title = '" + title + "'";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection conn = DriverManager.getConnection(url)) {
               
                Statement bookGet = conn.createStatement();

               
                
              
                ResultSet theResult = bookGet.executeQuery(queryGet);

             
                while (theResult.next()) {
                    String titler = theResult.getString("title");
                    System.out.println("Title: " + titler);
                }
            }
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        } 
        catch (SQLException e) 
        {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    
    }
    
    
    //
    
    //Interface used to query for books via the browse function
    public static void book_query(int minPage, int maxPage, String author, String genre)
    { 
        // Reconnect to SQL Database
        String server = "LAPTOP-EBU3JLQG";  
        String databaseName = "MyLibraryDatabase";  
        String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + databaseName + ";integratedSecurity=true;encrypt=false;";

       
        String queryGet = "SELECT title FROM Books WHERE check_out_by IS NULL ";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection conn = DriverManager.getConnection(url)) {
               
                Statement bookGet = conn.createStatement();

               
                if (minPage > 0) {
                    queryGet += " AND page_number >= " + minPage;
                }
                if (maxPage != 0) {
                    queryGet += " AND page_number <= " + maxPage;
                }            
                if (author != null) {
                    queryGet += " AND author = '" + author + "'"; 
                }    
                if (genre != null) {
                    queryGet += " AND genre = '" + genre + "'";
                }

              
                ResultSet theResult = bookGet.executeQuery(queryGet);

             
                while (theResult.next()) {
                    String title = theResult.getString("title");
                    System.out.println("Title: " + title);
                }
            }
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        } 
        catch (SQLException e) 
        {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    

    //Interface used to query for movies
    public static void movie_query(PreparedStatement query)
    {


    }
    
    //Interface to allow for administrative actions
    public static void admin_actions()
    {
    
    
    }
    
    public static void sign_up()
    {
    
    
    }
    
    public static void login(String username, String password)
    {
    
    }
    
    
    
}
