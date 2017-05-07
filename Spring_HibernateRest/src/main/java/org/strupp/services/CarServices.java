package org.strupp.services;

import java.util.List;

import org.strupp.model.Car;

public interface CarServices {
public boolean addEntity(Car car) throws Exception;
	
	public void updateCar(Car car) throws Exception;
	
	public Car getEntityById(long id) throws Exception;

	public List<Car> getEntityList() throws Exception;

	public boolean deleteCar(long id) throws Exception;
}
