package org.strupp.dao;

import java.util.List;

import org.strupp.model.Car;

public interface CarDao {
public boolean addEntity(Car employee) throws Exception;
	
	public void updateEntity(Car employee) throws Exception;
	
	public Car getEntityById(long id) throws Exception;

	public List<Car> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}
