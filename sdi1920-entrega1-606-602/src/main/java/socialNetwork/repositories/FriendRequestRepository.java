package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.FriendRequest; 

public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {

	@Query("SELECT r FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='false') ")
	Page<FriendRequest> findRequestToUser(Pageable pageable, String email);
	
	@Query("SELECT r FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='true') ")
	Page<FriendRequest> findFriendsOfUser(Pageable pageable, String email);
	
	Page<FriendRequest> findAll(Pageable pageable);

}
