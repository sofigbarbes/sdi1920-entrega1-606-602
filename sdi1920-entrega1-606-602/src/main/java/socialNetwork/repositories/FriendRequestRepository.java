package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.FriendRequest; 

public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {

	@Query("SELECT r FROM FriendRequest r WHERE r.id.senderEmail = ?1 OR r.id.receiverEmail = ?1  ORDER BY r.id ASC ")
	Page<FriendRequest> findAllByUser(Pageable pageable, String email);


}
