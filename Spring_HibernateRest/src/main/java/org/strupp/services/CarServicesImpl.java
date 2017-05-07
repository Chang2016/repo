package org.strupp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.strupp.dao.CarDao;
import org.strupp.model.Car;

public class CarServicesImpl implements CarServices {
	
	@Autowired
	CarDao carDao;
	
	@Override
	public boolean addEntity(Car car) throws Exception {
		return carDao.addEntity(car);
	}

	@Override
	public void updateCar(Car car) throws Exception {
		carDao.updateEntity(car);
	}

	@Override
	public Car getEntityById(long id) throws Exception {
		return carDao.getEntityById(id);
	}

	@Override
	public List<Car> getEntityList() throws Exception {
		return carDao.getEntityList();
	}

	@Override
	public boolean deleteCar(long id) throws Exception {
		return carDao.deleteEntity(id);
	}

}
