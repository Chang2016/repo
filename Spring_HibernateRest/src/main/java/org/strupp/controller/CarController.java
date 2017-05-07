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
import org.strupp.model.Car;
import org.strupp.model.Employee;
import org.strupp.model.Status;
import org.strupp.services.CarServices;
import org.strupp.services.EmployeeServices;

@Controller
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarServices carServices;

	static final Logger logger = Logger.getLogger(CarController.class);

	/* Submit form in Spring Restful Services */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(final @RequestBody Car car) {
		try {
			carServices.addEntity(car);
			return new Status(1, "car added Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}

	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status updateCar(final @RequestBody Car car, @PathVariable("id") long id) {
		try {
			car.setId(id);
			carServices.updateCar(car);
			return new Status(1, "Employee updated !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}
	/* Ger a single objct in Json form in Spring Rest Services */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Car getCar(@PathVariable("id") long id) {
		Car car = null;
		try {
			car = carServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Car> getEmployee() {

		List<Car> carList = null;
		try {
			carList = carServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return carList;
	}

	/* Delete an object from DB in Spring Restful Services */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteCar(@PathVariable("id") long id) {

		try {
			carServices.deleteCar(id);
			return new Status(1, "Car deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
}
