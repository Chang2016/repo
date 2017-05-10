package org.strupp.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.strupp.serializer.CarSerializer;

@Entity
@Table(name = "employee")
//@JsonIgnoreProperties({ /*"cars",*/ "hibernateLazyInitializer", "handler" })
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	@NotBlank(message = "firstName field can not be blank")
    @Length(max = 45, message = "firstName should not be more than 45 characters")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "lastName field can not be blank")
	@Length(max = 45, message = "lastName should not be more than 45 characters")
	private String lastName;

	@Column(name = "email")
	@Length(max = 45, message = "email should not be more than 10 characters")
	private String email;

	@Column(name = "phone")
	@Length(max = 45, message = "phone should not be more than 10 characters")
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "employee")
	@JsonManagedReference
	@JsonSerialize(using = CarSerializer.class)
//	@JsonDeserialize(using = CarDeserializer.class)
	private List<Car> cars = new LinkedList<Car>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}