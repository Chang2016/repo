package org.strupp.services;

import java.util.List;

import org.strupp.model.Employee;

public interface DataServices {
	public boolean addEntity(Employee employee) throws Exception;

	public Employee getEntityById(long id) throws Exception;

	public List<Employee> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}
