package database

import groovy.sql.Sql

class GroovySqlExample2 {
  static void main(String[] args) {
    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
           "root", "com.mysql.jdbc.Driver")
    sql.eachRow("select * from employee"){ 
      println it.first_name + " ${it.last_name}"
    }
  }
}
