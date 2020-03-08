package socialNetwork.services;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}

	public Page<User> getListUsers(Pageable pageable, String email) {
		Page<User> users = getUsers(pageable);
		LinkedList<User> content = new LinkedList<User>();

		for (User u : users.getContent()) {
			if (!u.getEmail().equals(email) && !u.getRole().equals("ROLE_ADMIN")) {
				content.add(u);
			}
		}
		Page<User> result = new PageImpl<User>(content);
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

	public Page<User> searchUser(Pageable pageable, String searchtext, String email) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());

		LinkedList<User> content = new LinkedList<User>();
		users = usersRepository.searchUser(pageable, "%" + searchtext + "%");
		for (User u : users.getContent()) {
			if (!u.getEmail().equals(email) && !u.getRole().equals("ROLE_ADMIN")) {
				content.add(u);
			}
		}
		Page<User> result = new PageImpl<User>(content);
		return result;
	}

}
