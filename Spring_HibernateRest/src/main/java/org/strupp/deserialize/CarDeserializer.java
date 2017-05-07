package org.strupp.deserialize;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.node.IntNode;
import org.strupp.model.Car;
import org.strupp.model.Employee;

public class CarDeserializer extends StdDeserializer<Car>{

	protected CarDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Car deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
		JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("empid")).getNumberValue();
        String name = node.get("name").asText();
        //int userId = (Integer) ((DecimalNode) node.get("price")).getNumberValue();
        Car car = new Car();
        car.setName(name);
        Employee emp = new Employee();
        emp.setId(id);
        car.setEmployee(emp);
        return car;
	}

}
