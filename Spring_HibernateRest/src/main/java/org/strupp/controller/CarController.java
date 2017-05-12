package org.strupp.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strupp.model.Car;
import org.strupp.model.Status;
import org.strupp.representation.CarResource;
import org.strupp.services.CarServices;

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
	public HttpEntity<CarResource> getCar(@PathVariable("id") long id) {
		Car car = null;
		CarResource response = null;
		try {
			car = carServices.getEntityById(id);
			response = new CarResource(car.getName(), car.getPrice().toString());
			response.add(linkTo(methodOn(CarController.class).getCar(id)).withSelfRel());
//			response.add(linkTo(methodOn(CarController.class).getCar(id) ).withRel("car"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<CarResource>(response, HttpStatus.OK);
	}

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public HttpEntity<List<CarResource>> getCars() {
		List<CarResource> response = new ArrayList<>();
		List<Car> carList = null;
		try {
			carList = carServices.getEntityList();
			carList.forEach(c -> {
				CarResource car = new CarResource(c.getName(), c.getPrice().toString());
				car.add(linkTo(methodOn(CarController.class).getCars()).withSelfRel());
				response.add(car);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<CarResource>>(response, HttpStatus.OK);
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
