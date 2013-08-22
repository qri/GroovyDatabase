package database

import groovy.sql.Sql
class DBStatusReport{
  static void main(String[] args) {
     def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
        "root", "com.mysql.jdbc.Driver")

     def uptime = ""
     def questions = ""
     sql.eachRow("show status"){ status ->
        if(status.variable_name == "Uptime"){
           uptime =  status[1]
        }else if (status.variable_name == "Questions"){
           questions =  status[1]
        }
     }
     println "Uptime for Database: " + uptime
     println "Number of Queries: " + questions
     println "Queries per Minute = " + Integer.valueOf(questions) / Integer.valueOf(uptime)

     int insertnum = 0
     int selectnum = 0
     int updatenum = 0
     sql.eachRow("show status like 'Com_%'"){ status ->
        if(status.variable_name == "Com_insert"){
           insertnum =  Integer.valueOf(status[1])
        }else if (status.variable_name == "Com_select"){
           selectnum =  Integer.valueOf(status[1])
        }else if (status.variable_name == "Com_update"){
           updatenum =  Integer.valueOf(status[1])
       }
    }
    println "% Queries Inserts = " + 100 * (insertnum / Integer.valueOf(uptime))
    println "% Queries Selects = " + 100 * (selectnum / Integer.valueOf(uptime))
    println "% Queries Updates = " + 100 * (updatenum / Integer.valueOf(uptime))
  }
}
