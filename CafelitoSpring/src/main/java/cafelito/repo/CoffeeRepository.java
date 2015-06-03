package cafelito.repo;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import cafelito.model.CoffeeShop;

public interface CoffeeRepository extends MongoRepository<CoffeeShop,String> {
	
	CoffeeShop findByAddressLocationNear(Point point);

}
