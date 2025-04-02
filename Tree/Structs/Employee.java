package Tree.Structs;

/**
 * Employee represents an Employee - which we will be storing in the nodes 
*/
public class Employee implements Comparable<Employee> {
	
	private String name;
	private Integer id;
	private String position;

	public Employee(String name, Integer id, String position) {

		this.name = name;
		this.id = id;
		this.position = position;

	}
	
	// Accessors/Mutators
	
	public String getName() {
		
		return this.name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public Integer getId() {

		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	public String getPosition() {

		return this.position;

	}

	public void setPosition(String position) {

		this.position = position;

	}

	// Comparable Stuff

	public boolean equals(Employee otherEmployee) {

		return this.name.equals(otherEmployee.name) && this.id == otherEmployee.id && this.position.equals(otherEmployee.position);

	}
	
	public int compareTo(Employee otherEmployee) {

		return this.id.compareTo(otherEmployee.id);

	}

	// Misc

	@Override
	public String toString() {

		return this.name + " " + this.id + " " + this.position;

	}

}