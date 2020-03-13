package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import socialNetwork.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) or LOWER(r.lastName) LIKE LOWER(?1) or LOWER(r.email) LIKE LOWER(?1) )")
	Page<User> searchUser(Pageable pageable, String searchText);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.email) NOT LIKE LOWER(?1) and r.role NOT LIKE 'ROLE_ADMIN')")
	Page<User> listUsers(Pageable pageable, String email);

	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.email IN (SELECT r.id.senderEmail FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='true') )")
	Page<User> findFriendsOfUser(Pageable pageable, String email);
	
	@Query("SELECT u FROM User u WHERE u.email IN (SELECT r.id.senderEmail FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='false' and r.show='true') )")
	Page<User> findRequestToUser(Pageable pageable, String email);
	
	@Query("SELECT u FROM User u WHERE u.email IN (SELECT r.id.receiverEmail FROM FriendRequest r WHERE (r.id.senderEmail = ?1 and r.accepted='false' and r.show='true') )")
	Page<User> findRequestByUser(Pageable pageable, String email);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.email) NOT LIKE LOWER(?1))")
	Page<User> listUsersAdmin(Pageable pageable, String email);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.email=?1")
	void deleteByEmail(String email);
	
}
