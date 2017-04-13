package dto;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grandmother")
public class GrandMother {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	@OneToMany(mappedBy="mother")//, cascade = {CascadeType.ALL})
	private Collection<Mother> daughters = new LinkedList<Mother>();
	
	public GrandMother() {}
	public GrandMother(String name) {
		this.name = name;
	}
	
	public void addDaughter(Mother m) {
		this.daughters.add(m);
		m.setMother(this);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Mother> getDaughters() {
		return daughters;
	}

	public void setDaughters(Collection<Mother> daughters) {
		this.daughters = daughters;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
}
