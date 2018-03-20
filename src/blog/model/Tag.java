package blog.model;

public class Tag {

	
	private int id;
	private String name;
	private int number;
		
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

	public Tag() {
		super();		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number= number;
	}
	
}
