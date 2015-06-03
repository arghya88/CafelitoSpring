package cafelito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
	@RequestMapping(value = "nearest/latitude/{latitude}/longitude/{longitude}", method = RequestMethod.GET)
	public Object getNearest(@PathVariable("latitude") double latitude,@PathVariable("longitude") double longitude) throws Exception{


		Point point = new Point(longitude,latitude);
		//Point point = new Point(77.6193081,12.9240806);
		CoffeeShop  coffeeShop=coffeeshoprepo.findByAddressLocationNear(point);
		return coffeeShop;
	}

	@RequestMapping(value = "/{order}",method = RequestMethod.POST)
	public Order saveOrder(@RequestBody Order order){
		orderrepo.save(order);
		return order;

	}
}
