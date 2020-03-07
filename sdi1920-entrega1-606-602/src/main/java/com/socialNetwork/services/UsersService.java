package com.socialNetwork.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialNetwork.entities.User;
import com.socialNetwork.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword((user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByDni(String dni) {
		return usersRepository.findByDni(dni);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public List<User> searchUsersByNameAndSurname(String searchText, User user) {
		List<User> users = new LinkedList<User>();
		searchText = "%" + searchText + "%";

		if (user.getRole().equals("ROLE_ADMIN")) {
			users = usersRepository.searchByNameAndSurname(searchText);
		}
		return users;
	}
}
