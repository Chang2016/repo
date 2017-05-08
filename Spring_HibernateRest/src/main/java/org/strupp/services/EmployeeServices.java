package org.strupp.services;

import java.util.List;

import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.model.Employee;

public interface EmployeeServices {
	public boolean addEntity(Employee employee) throws Exception;
	
	public void updateEmployee(Employee employee) throws Exception;
	
	public Employee getEntityById(long id) throws NoSuchEmployeeException;

	public List<Employee> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}
