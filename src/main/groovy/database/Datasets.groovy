package database

import groovy.sql.Sql

class Datasets{
  static void main(String[] args) {
    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
          "root", "com.mysql.jdbc.Driver")
          
    sql.execute("delete from employee where id=6")          
          
    def employees = sql.dataSet("employee")
    employees.add(id:"6", first_name:"John", last_name:"Smith", email:"js@test.de")
    employees.each{ employee ->
     println employee.id + " " + employee.first_name
    }
  }
}
