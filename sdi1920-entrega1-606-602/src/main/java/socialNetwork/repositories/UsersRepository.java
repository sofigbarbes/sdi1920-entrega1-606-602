package socialNetwork.repositories;

import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
