package socialNetwork.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import socialNetwork.entities.User;
import socialNetwork.repositories.UsersRepository;


@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	public List<User> getListUsers(String email){
		List<User> users = getUsers();
		List<User> result = new ArrayList<User>();
		for (User u : users) {
			if(!u.getEmail().equals(email) && !u.getRole().equals("ROLE_ADMIN")) {
				result.add(u);
			}
		}
		return result;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public List<User> searchUser(String searchtext, String email){
		List<User> result = new ArrayList<User>();
		List<User> users = usersRepository.searchUser("%"+searchtext+"%");
		for (User u : users) {
			if(!u.getEmail().equals(email) && !u.getRole().equals("ROLE_ADMIN")) {
				result.add(u);
			}
		}
		return result;
	}
}
