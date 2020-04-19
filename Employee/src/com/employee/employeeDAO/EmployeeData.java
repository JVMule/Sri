package com.employee.employeeDAO;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.employee.employeeModel.Employee;

public class EmployeeData {
	

	
	private static final Map<String, Employee> empMap =  new HashMap< String, Employee>();
	
	public static Employee getEmpno(String empNo) { //Static Method & no need to create object for static methods
		Connection connection = EmployeeConnection.getConnection(); // connecting to db using EmployeeConnection class
		
		Employee emp = new Employee(); // Creating Object for employee class to call instance Methods
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee where Empno =" +empNo);
				if (rs.next())
				{
					emp.setEmpNo(rs.getString("empNo"));
					emp.setEmpName(rs.getString("empName"));
					emp.setEmpRole(rs.getString("empRole"));
				}
					return emp; // returning the retrieved data to employee function
		}
		catch(SQLException ex) {
			ex.printStackTrace(); // printStackTrace shows the predefined error. 
		}
		return null;

	}
	
	 // Set<Employee> -- unordered collection of objects
	public static Set<Employee> getallEmp() {
		Connection connection = EmployeeConnection.getConnection(); // connecting to db using EmployeeConnection class
		
		
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
			
			Set <Employee> employees = new HashSet<Employee>();
				while (rs.next())
				{
					Employee emp1 = extractemployeeFromResultSet(rs); 
					
					employees.add(emp1);
				}
					return employees; // returning the retrieved data to employee function
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
		
	}


	private static Employee extractemployeeFromResultSet(ResultSet rs) throws SQLException {
		Employee emp = new Employee(); 
		emp.setEmpNo(rs.getString("empNo"));
		emp.setEmpName(rs.getString("empName"));
		emp.setEmpRole(rs.getString("empRole"));
		return emp;
	}

	

	public static Employee  addEmployee(Employee emp2) {
		
				
		int status= 0;
		
		try {
			Connection connection = EmployeeConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement( "INSERT INTO employee VALUES(?,?,?)");
			
			ps.setString(1, emp2.getEmpNo());
			ps.setString(2, emp2.getEmpName()); 
			ps.setString(3,emp2.getEmpRole());
			 status =  ps.executeUpdate();
			 System.out.println(status +"row inserterd sucessfully" );
		}
		catch (SQLException ex) { System.out.println(ex.getMessage()); 
		
		}
			
	 		return  emp2;
		 
	}
	
	
	public static Employee deleteEmployee(String empNo) {
		 //Static Method & no need to create object for static methods
		
		Connection connection = EmployeeConnection.getConnection(); // connecting to db using EmployeeConnection class
		Employee emp = new Employee();
		
		try {
			Statement stmt = connection.createStatement();
			int  rs = stmt.executeUpdate("delete from employee where Empno =" +empNo);
	 	    emp.getEmpNo();
						
			 System.out.println("No of Rows deleted is " +rs );			
			 }
		
		catch(SQLException ex) {
			ex.printStackTrace(); // printStackTrace shows the predefined error. 
		}
		return emp ;
		
		

	
	}
		
}


//Reference Simple CRUD example with Java RESTful Web Service
//https://o7planning.org/en/11207/simple-crud-example-with-java-restful-web-service