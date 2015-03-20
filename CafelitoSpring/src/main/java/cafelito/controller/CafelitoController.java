package cafelito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cafelito.model.CoffeeShop;
import cafelito.model.Order;
import cafelito.repo.CoffeeRepository;
import cafelito.repo.OrderRepository;

@RestController
@RequestMapping("/coffeeshop")
public class CafelitoController {
	@Autowired
	OrderRepository orderrepo;
	@Autowired
	CoffeeRepository coffeeshoprepo;
	@Autowired
	MongoTemplate mongoTemplate;
	@RequestMapping(value = "nearest/{latitude}/{longitude}", method = RequestMethod.GET)
	public Object getNearest(@PathVariable("latitude") double latitude,@PathVariable("longitude") double longitude){
		

		Point point = new Point(longitude,latitude);
		CoffeeShop coffeeShop =
				mongoTemplate.findOne(new Query(
			        Criteria.where("location").nearSphere(point)),
			        CoffeeShop.class);
		double[] coordinates=coffeeShop.getLocation().getCoordinates();
		System.out.println("Long"+coordinates[0]+"Lat"+coordinates[1]+"Id"+coffeeShop.getName());
		return coffeeShop;
		//return coffeeshoprepo.findByLocationNear(point);
		
	}
	@RequestMapping(value = "/{order}",method = RequestMethod.POST)
	public Order saveOrder(@RequestBody Order order){
		orderrepo.save(order);
		return order;
		
	}
}
