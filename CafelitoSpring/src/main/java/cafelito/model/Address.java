package cafelito.model;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Address {
	private  String street, city, zip;
	private  @GeoSpatialIndexed Point location;
	
	public Address(String street, String city, String zip, Point location) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.location = location;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	

}
