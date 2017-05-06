package org.strupp.deserialize;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.strupp.model.Car;

public class CarDeserializer extends JsonDeserializer<List<Car>>{

	@Override
	public List<Car> deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
