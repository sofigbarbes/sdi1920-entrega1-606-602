package socialNetwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import socialNetwork.entities.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {

	@Query("SELECT r FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='false') ")
	Page<FriendRequest> findRequestToUser(Pageable pageable, String email);
 
	@Query("SELECT r FROM FriendRequest r WHERE (r.id.receiverEmail = ?1 and r.accepted='true') ")
	Page<FriendRequest> findFriendsOfUser(Pageable pageable, String email);

	Page<FriendRequest> findAll(Pageable pageable);

	@Query("SELECT r FROM FriendRequest r WHERE (LOWER(r.id.receiverEmail) LIKE LOWER(?1) and r.accepted='true') ")
	Page<FriendRequest> searchFriend(Pageable pageable, String string);

	@Query("SELECT r FROM FriendRequest r WHERE r.id.senderEmail=?1 AND r.id.receiverEmail=?2")
	FriendRequest findRequest(String sender, String receiver);

	@Transactional
	@Modifying
	@Query("UPDATE FriendRequest r SET r.accepted=true WHERE r.id.senderEmail=?1 AND r.id.receiverEmail=?2")
	void acceptRequest(String sender, String receiver);

}
