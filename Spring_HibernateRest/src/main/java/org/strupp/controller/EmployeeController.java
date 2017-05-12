package org.strupp.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.model.Employee;
import org.strupp.model.Status;
import org.strupp.representation.EmployeeResource;
import org.strupp.representation.assembler.EmployeeResourceAssembler;
import org.strupp.services.EmployeeServices;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeResourceAssembler assembler;
	
	@Autowired
	EmployeeServices employeeServices;
	
	static final Logger logger = Logger.getLogger(EmployeeController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addEmployee(@Valid final @RequestBody Employee employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Set<ConstraintViolation<Employee>> constraintViolations = validateInput(employee);
			throw new ConstraintViolationException(constraintViolations);
	    } else {
			try {
				employeeServices.addEntity(employee);
				//Link zur neu erstellten Ressource im Link-Header
				HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(linkTo(methodOn(getClass()).getEmployee(employee.getId())).toUri());
		        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				HttpHeaders headers = new HttpHeaders();
				return new ResponseEntity<Void>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateEmployee(@Valid final @RequestBody Employee employee, BindingResult bindingResult, @PathVariable("id") long id) {
		if (bindingResult.hasErrors()) {
			Set<ConstraintViolation<Employee>> constraintViolations = validateInput(employee);
			throw new ConstraintViolationException(constraintViolations);
	    } else {
			try {
				employee.setId(id);
				employeeServices.updateEmployee(employee);
				return new Status(1, "Employee updated !");
			} catch (Exception e) {
				 e.printStackTrace();
				return new Status(0, e.toString());
			}
	    }
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<EmployeeResource> getEmployee(@PathVariable("id") long id) throws NoSuchEmployeeException {
		Employee employee = null;
		employee = employeeServices.getEntityById(id);
		EmployeeResource response = assembler.toResource(employee);
		return new ResponseEntity<EmployeeResource>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public HttpEntity<List<EmployeeResource>> getEmployees() {
		List<EmployeeResource> response = new ArrayList<>();
		List<Employee> employeeList = null;
		try {
			employeeList = employeeServices.getEntityList();
			employeeList.forEach(e -> {
				EmployeeResource res = assembler.toResource(e);
				response.add(res);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<EmployeeResource>>(response, HttpStatus.OK);
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
	
	private Set<ConstraintViolation<Employee>> validateInput(Employee employee) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
		constraintViolations.forEach(cv -> logger.error(  cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage()) );
        return constraintViolations;
	}
	
//	lokaler ExceptionHandler
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
