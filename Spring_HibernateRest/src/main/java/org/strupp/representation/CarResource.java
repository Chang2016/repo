package org.strupp.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarResource extends ResourceSupport {
	private String name;
	private String price;
	
	@JsonCreator
	public CarResource(@JsonProperty String name, @JsonProperty String price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}
}
