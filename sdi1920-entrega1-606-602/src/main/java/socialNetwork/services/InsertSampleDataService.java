package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialNetwork.entities.FriendRequest;
import socialNetwork.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private FriendRequestService friendReqService;


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
		User user7 = new User("prueba", "Lucía", "Blanco");
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

		FriendRequest fr1 = new FriendRequest("edwardnuñez@correo.com", "prueba", false);
		friendReqService.addFriendRequest(fr1);
		FriendRequest fr2 = new FriendRequest("pelayovaldes@correo.com", "edwardnuñez@correo.com", true);
		friendReqService.addFriendRequest(fr2);
		FriendRequest fr3 = new FriendRequest("pelayovaldes@correo.com", "mariarodriguez@correo.com", false);
		friendReqService.addFriendRequest(fr3);
		FriendRequest fr4 = new FriendRequest("prueba", "pelayovaldes@correo.com", true);
		friendReqService.addFriendRequest(fr4);
		FriendRequest fr5 = new FriendRequest("pelayovaldes@correo.com", "prueba", true);
		friendReqService.addFriendRequest(fr5);
		FriendRequest fr6 = new FriendRequest("edwardnuñez@correo.com", "sofi", false);
		friendReqService.addFriendRequest(fr6);
		FriendRequest fr7 = new FriendRequest("sofi", "pelayovaldes@correo.com", false);
		friendReqService.addFriendRequest(fr7);
		FriendRequest fr8 = new FriendRequest("pelayovaldes@correo.com", "sofi", true);
		friendReqService.addFriendRequest(fr8);
		FriendRequest fr9 = new FriendRequest("prueba", "sofi", false);
		friendReqService.addFriendRequest(fr9);
		FriendRequest fr10 = new FriendRequest("mariarodriguez@correo.com", "sofi", true);
		friendReqService.addFriendRequest(fr10);
		FriendRequest fr11 = new FriendRequest("martaalmonte@correo.com", "sofi", true);
		friendReqService.addFriendRequest(fr11);
	}
}
