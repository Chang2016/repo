package dto;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="daughter")
public class Daughter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="mid_fk")
	private Mother mother;
	private String name;
	private int age;
	@OneToMany(mappedBy="mother", cascade = {CascadeType.ALL})
	private Collection<GrandDaughter> daughters = new LinkedList<GrandDaughter>();
	
	public Daughter() {}
	public Daughter(String name) {
		this.name = name;
	}
	
	public void addDaughter(GrandDaughter gd) {
		this.daughters.add(gd);
		gd.setMother(this);
	}
	
	public Mother getMother() {
		return mother;
	}

	public void setMother(Mother mother) {
		this.mother = mother;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<GrandDaughter> getDaughters() {
		return daughters;
	}

	public void setDaughters(Collection<GrandDaughter> daughters) {
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
