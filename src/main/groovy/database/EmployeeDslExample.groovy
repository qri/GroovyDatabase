package database

import java.util.Map;

import groovy.sql.Sql
import groovy.xml.MarkupBuilder

class Employees{
	def ID
	def firstName
	def lastName
	def email
	def dept
}

class EmployeeDsl {
	static employeesList = [:]

	static populate(self, employeeData) {
		//println "Employee ${employeeData.firstName} ${employeeData.lastName} with email " +
		//		"${employeeData.email} has employee ID " +
		//		"${employeeData.ID} and works in " +
		//		"${employeeData.dept} department."

		if(employeesList[employeeData.dept] == null){
			employeesList[employeeData.dept] = [employeeData]} else{
			employeesList[employeeData.dept] << employeeData
		}
	}

	static getId(self) {
		def newEmp = new Employees(ID:self)
		return newEmp
	}
	static firstName(self, name) {
		self.firstName = name
		return self
	}
	static lastName(self, name) {
		self.lastName = name
		return self
	}
	static email(self, email) {
		self.email = email
		return self
	}
	static dept(self, dept) {
		self.dept = dept
		return self
	}

	static showEmployees(self, dept){
		employeesList[dept].each { employeeData ->
			println "Employee '${employeeData.firstName} ${employeeData.lastName}' (" +
					"${employeeData.email}), employee ID " +
					"${employeeData.ID}, works in " +
					"'${employeeData.dept}' department."
		}
	}

	static generateEmpXML(self, file){
		def out = new File(file)
		def writer = new FileWriter( out )
		def xml = new MarkupBuilder(writer)

		xml.employees {
			employeesList.each { employeeDept ->
				employeeDept.each { employeeData2 ->
					employeeData2.each { employeeData ->
					xml.employee(id:employeeData.ID){
						firstName(employeeData.firstName)
						lastName(employeeData.lastName)
						email(employeeData.email)
					}
					}
				}				
			}
		}
	}
}


use(EmployeeDsl){
	def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
			"root", "com.mysql.jdbc.Driver")
	sql.eachRow("select * from employee"){ row ->
		populate row.id.id.firstName(row.first_name).lastName(row.last_name).email(row.email).dept("finance")
	}
	//println row.id + " " + row.first_name+ " " + row.last_name + "	" + row.email

	populate 91.id.firstName("John").lastName("Jr.").email("john@test.de").dept("finance")
	populate 92.id.firstName("Michael").lastName("Smith").email("Michael@test.de").dept("management")
	populate 93.id.firstName("Robert").lastName("Hood").email("Robert@test.de").dept("management")
	populate 94.id.firstName("James").lastName("II").email("James@test.de").dept("management")
	populate 95.id.firstName("George").lastName("Prince").email("George@test.de").dept("management")

	//println ' '
	println '***** Employees in Finance Department *****'
	println ' '
	showEmployees "finance"

	println ' '
	println '***** Employees in management Department *****'
	println ' '
	showEmployees "management"	
	
	//generateEmpXML "out.xml"
}
/*	static void main(String[] args) {
 def sql = Sql.newInstance("jdbc:mysql://localhost:3306/company", "root",
 "root", "com.mysql.jdbc.Driver")
 sql.eachRow("select * from employee"){ row ->
 println row.id + " " + row.first_name+ " " + row.last_name + "	" + row.email
 }
 }
 */