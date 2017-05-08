package org.strupp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.model.Employee;
import org.strupp.model.Status;
import org.strupp.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	static final Logger logger = Logger.getLogger(EmployeeController.class);

	/* Submit form in Spring Restful Services */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(final @RequestBody Employee employee) {
		try {
			employeeServices.addEntity(employee);
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}

	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status updateEmployee(final @RequestBody Employee employee, @PathVariable("id") long id) {
		try {
			employee.setId(id);
			employeeServices.updateEmployee(employee);
			return new Status(1, "Employee updated !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}
	/* Ger a single objct in Json form in Spring Rest Services */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Employee getEmployee(@PathVariable("id") long id) throws NoSuchEmployeeException {
		Employee employee = null;
//		try {
			employee = employeeServices.getEntityById(id);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return employee;
	}

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployee() {

		List<Employee> employeeList = null;
		try {
			employeeList = employeeServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	/* Delete an object from DB in Spring Restful Services */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		try {
			employeeServices.deleteEntity(id);
			return new Status(1, "Employee deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	
//	@ExceptionHandler(NoSuchEmployeeException.class)
//	private @ResponseBody noSuchEmployee(HttpServletRequest request, Exception ex) {
//		logger.error("Requested URL=" + request.getRequestURL());
//		logger.error("Exception Raised=" + ex);
//		
//		ExceptionJSONInfo response = new ExceptionJSONInfo();
//		response.setUrl(request.getRequestURL().toString());
//		response.setMessage(ex.getMessage());
//		
//		return response;
//	}
}
