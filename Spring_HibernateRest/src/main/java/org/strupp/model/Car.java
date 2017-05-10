package org.strupp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "car")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Car implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3053170578688402743L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Length(max = 45)
	private String name;
	@Column(precision=7, scale=2)
	private BigDecimal price;
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "EMPID")
//	@PrimaryKeyJoinColumn(name="EMPID", referencedColumnName="ID")
	@JsonBackReference
	private Employee employee;

	public Car() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
