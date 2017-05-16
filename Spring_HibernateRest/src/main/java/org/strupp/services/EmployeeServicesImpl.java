package org.strupp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.strupp.dao.EmployeeDao;
import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.model.Employee;

public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	@Transactional
	public boolean addEntity(Employee employee) throws Exception {
		return employeeDao.addEntity(employee);
	}

	@Override
	public Employee getEntityById(long id) throws NoSuchEmployeeException {
		return employeeDao.getEntityById(id);
	}

	@Override
	public List<Employee> getEntityList() throws Exception {
		return employeeDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return employeeDao.deleteEntity(id);
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		employeeDao.updateEmployee(employee);
	}

}