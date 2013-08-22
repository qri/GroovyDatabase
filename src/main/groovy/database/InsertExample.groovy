package database

import groovy.sql.Sql
class insert{
  static void main(String[] args) {
    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
           "root", "com.mysql.jdbc.Driver")

   sql.execute("delete from employee where id=5")


   def eid = 5
   def firstName= "Groovy"
   def lastName = "Test"
   sql.execute("insert into employee (id, first_name, last_name) values (${eid}, ${firstName}, ${lastName})")

	 println "************* After first Insert **************"
   sql.eachRow("select * from employee"){ row ->
      println "employee id: " + row.id + "	" + " First Name: " + row.first_name+ "	" + " Last Name: " + row.last_name + "	" + " Email: " + row.email
   }

   //def val = sql.execute("select * from employee where id = ?", [5])
   //println val

   def nid = 5
   def newLastName = "Proved"
   sql.executeUpdate("update employee set last_name = ? where id = ?", [newLastName, nid])

	 println ' '
	 println "************* After update **************"
	 
   sql.eachRow("select * from employee"){ row ->
      println "employee id: " + row.id + "	" + " First Name: " + row.first_name+ "	" + " Last Name: " + row.last_name + "	" + " Email: " + row.email
   }

   println ' '
	 println "************* Get Last Row **************"
	 
   sql.eachRow("select * from employee"){ row ->
     (0..2).each{ i ->
       print "Field[${i}]: " 
       println row.getAt(i)
     }
     println "Last field using -1 index = " + row.getAt(-1) 
   }
  }
}