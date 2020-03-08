package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) or LOWER(r.lastName) LIKE LOWER(?1) or LOWER(r.email) LIKE LOWER(?1) )")
	Page<User> searchUser(Pageable pageable, String searchText);

	Page<User> findAll(Pageable pageable);
}
