package cafelito.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Order")
public class Order {
	@Id
    private String id;

    private String drinker;
    private String size;
    private String[] selectedOptions;
    private long coffeeShopId;
    private DrinkType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDrinker() {
		return drinker;
	}
	public void setDrinker(String drinker) {
		this.drinker = drinker;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public long getCoffeeShopId() {
		return coffeeShopId;
	}
	public void setCoffeeShopId(long coffeeShopId) {
		this.coffeeShopId = coffeeShopId;
	}
	public DrinkType getType() {
		return type;
	}
	public void setType(DrinkType type) {
		this.type = type;
	}
    

}
