package cafelito.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import cafelito.model.Order;

public interface OrderRepository extends MongoRepository<Order,String> {

}
