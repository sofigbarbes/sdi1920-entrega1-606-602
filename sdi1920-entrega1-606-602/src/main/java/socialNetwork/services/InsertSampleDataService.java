package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialNetwork.entities.Post;
import socialNetwork.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private FriendRequestService friendReqService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("pedrodiaz@correo.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		User user2 = new User("lucasnuñez@correo.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("mariarodriguez@correo.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("martaalmonte@correo.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("pelayovaldes@correo.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("edwardnuñez@correo.com", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		User user7 = new User("luci", "Lucía", "Blanco");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("sofi", "Sofía", "garcía");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		postService.addService(new Post("luci", "2020-03-11", "Hola Marcos", "HOLAA"));
	}
	
}
