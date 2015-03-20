package Cafelito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cafelito.DemoApplication;
import cafelito.repo.CoffeeRepository;
import cafelito.repo.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
	@Autowired
	CoffeeRepository repo;
	@Autowired
	OrderRepository orderrepo;

	@Test
	public void Test1() {
		System.out.println(repo.findAll());
	}
	@Test
	public void Test2() {
		System.out.println(orderrepo.findAll());
	}
}
