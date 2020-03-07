package socialNetwork.services;

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
		User user1 = new User("pedrodiaz@correo.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		User user2 = new User("lucasnuñez@correo.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		User user3 = new User("mariarodriguez@correo.com", "María", "Rodríguez");
		user3.setPassword("123456");
		User user4 = new User("martaalmonte@correo.com", "Marta", "Almonte");
		user4.setPassword("123456");
		User user5 = new User("pelayovaldes@correo.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		User user6 = new User("edwardnuñez@correo.com", "Edward", "Núñez");
		user6.setPassword("123456");
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}
