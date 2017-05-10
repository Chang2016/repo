package org.strupp.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class EmployeeResponse extends ResourceSupport {
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String phone;
	
	@JsonCreator
	public EmployeeResponse(@JsonProperty String firstName, 
			@JsonProperty String lastName, @JsonProperty String email, @JsonProperty String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	
}
