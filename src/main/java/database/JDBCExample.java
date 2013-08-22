package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
  public static void main(String[] args) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company",
           "root", "root");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select * from employee");
      while (rs.next()) {
        System.out.println("Employee id: " + rs.getLong(1) + "	" +
            " first name: " + rs.getString(2) + "	" +
            " last name: " + rs.getString(3) + "	" +
            " email: " + rs.getString(4));
      }
    }catch(SQLException e){
      e.printStackTrace();
    }catch(ClassNotFoundException e){
      e.printStackTrace();
    }finally{
      try{rs.close();}catch(Exception e){}
      try{stmt.close();}catch(Exception e){}
      try{con.close();}catch(Exception e){}
   }
  }
}
