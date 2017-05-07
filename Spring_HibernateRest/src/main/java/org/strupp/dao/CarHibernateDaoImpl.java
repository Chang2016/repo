package org.strupp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.strupp.model.Car;
import org.strupp.model.Employee;

public class CarHibernateDaoImpl implements CarDao {
	
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addEntity(Car car) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.persist(car);
		tx.commit();
		session.flush();
		session.close();
		return false;
	}

	@Override
	public void updateEntity(Car car) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(car);
//		session.update(employee.getCars().iterator().next());
		tx.commit();
		session.close();
	}

	@Override
	public Car getEntityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Car car = (Car) session.load(Car.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return car;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Car> carList = session.createCriteria(Car.class).list();
		tx.commit();
		session.close();
		return carList;
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

}
