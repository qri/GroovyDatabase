package database

import groovy.sql.Sql
class GroovySqlExample1{
  static void main(String[] args) {
    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
           "root", "com.mysql.jdbc.Driver")
    sql.eachRow("select * from employee"){ row ->
       println row.id + " " + row.first_name+ " " + row.last_name + "	" + row.email
    }
  }
}
