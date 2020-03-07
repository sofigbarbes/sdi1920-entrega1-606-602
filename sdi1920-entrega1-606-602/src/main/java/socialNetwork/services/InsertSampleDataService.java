package socialNetwork.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialNetwork.entities.User;


@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("99999990A", "Pedro", "Díaz");
		user1.setPassword("123456");
		User user2 = new User("99999991B", "Lucas", "Núñez");
		user2.setPassword("123456");
		User user3 = new User("99999992C", "María", "Rodríguez");
		user3.setPassword("123456");
		User user4 = new User("99999993D", "Marta", "Almonte");
		user4.setPassword("123456");
		User user5 = new User("99999977E", "Pelayo", "Valdes");
		user5.setPassword("123456");
		User user6 = new User("99999988F", "Edward", "Núñez");
		user6.setPassword("123456");
	}
}
