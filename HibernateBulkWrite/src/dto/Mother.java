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
@Table(name="mother")
public class Mother {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="gmid_fk")
	private GrandMother mother;
	private String name;
	private int age;
	@OneToMany(mappedBy="mother", cascade = CascadeType.ALL)
	private Collection<Daughter> daughters = new LinkedList<Daughter>();
	
	public Mother() {}
	public Mother(String name) {
		this.name = name;
	}
	
	public void addDaughter(Daughter d) {
		this.daughters.add(d);
		d.setMother(this);
	}
	
	public GrandMother getMother() {
		return mother;
	}

	public void setMother(GrandMother mother) {
		this.mother = mother;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Daughter> getDaughters() {
		return daughters;
	}

	public void setDaughters(Collection<Daughter> daughters) {
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
