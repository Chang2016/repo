package org.strupp.serializer;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.strupp.model.Car;

public class CarSerializer extends JsonSerializer<List<Car>> {

	@Override
	public void serialize(List<Car> cars, JsonGenerator jgen, SerializerProvider prov)
			throws IOException, JsonProcessingException {
		jgen.writeStartArray();
        for (Car car : cars) {
            jgen.writeStartObject();
            jgen.writeObjectField("id", car.getId());
            jgen.writeObjectField("name", car.getName());
            jgen.writeObjectField("price", car.getPrice());
            jgen.writeEndObject();    
        }
        jgen.writeEndArray();
	}

}
