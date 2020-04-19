package com.employee.employeeService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.employee.employeeDAO.EmployeeData;
import com.employee.employeeModel.Employee;

@Path("/employees")

public class EmpService {

	

	@GET
	@Path("/{empNo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Employee getEmployee(@PathParam("empNo") String empNo)
	{
		return EmployeeData.getEmpno(empNo);  //Reference to Static Method 
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Set <Employee> getEmployees_JSON()
	{
		Set <Employee> listofEmployees = EmployeeData.getallEmp();
	
	return listofEmployees;
		}
	
	@POST
	@Path("/emp")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Employee> addEmployees(List<Employee> employees) {
	    List<Employee> result = new ArrayList<>();

	    for (Employee employee : employees) {
	        result.add(EmployeeData.addEmployee(employee));
	    }

	    return result;
	}
	
	@DELETE
	@Path("/{empNo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Employee deleteEmployee(@PathParam("empNo") String empNo)
	{
		return EmployeeData.deleteEmployee(empNo);  //Reference to Static Method 
	}
}
