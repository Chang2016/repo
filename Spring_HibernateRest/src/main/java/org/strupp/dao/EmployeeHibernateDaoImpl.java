package org.strupp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.model.Employee;

public class EmployeeHibernateDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employee employee) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		System.out.println("before " + employee.getId());
//		session.saveOrUpdate(employee);
		session.persist(employee);
//		Serializable slz = session.save(employee);
//		Long after = (Long) slz;
//		System.out.println("after " + after.longValue());
		tx.commit();
		session.flush();
		session.close();

		return false;
	}
	
	@Override
	public Employee getEntityById(long id) throws NoSuchEmployeeException {
		session = sessionFactory.openSession();
		Query query = session.createQuery("select e from Employee e where e.id = :id");
		query.setLong("id", id);
		Employee employee = (Employee) query.uniqueResult();
		if(employee == null)
			throw new NoSuchEmployeeException(id);
		return employee;
	}
	
//	@Override
//	public Employee getEntityById(long id) throws NoSuchEmployeeException {
//		session = sessionFactory.openSession();
//		Employee employee = (Employee) session.load(Employee.class, new Long(id));
//		tx = session.getTransaction();
//		session.beginTransaction();
//		tx.commit();
//		return employee;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employee> employeeList = session.createCriteria(Employee.class).list();
		tx.commit();
		session.close();
		return employeeList;
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employee.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(employee);
//		session.update(employee.getCars().iterator().next());
		tx.commit();
		session.close();
	}

}
