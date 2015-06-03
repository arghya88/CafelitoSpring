package cafelito.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CoffeeShop")
public class CoffeeShop {
	private @Id String id;
    private Address address;
    private String name;

    public CoffeeShop(String name,Address address) {
    	this.id=null;
    	this.name=name;
    	this.address=address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
    
}
