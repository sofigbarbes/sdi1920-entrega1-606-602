package socialNetwork.repositories;

import org.springframework.data.repository.CrudRepository;

import socialNetwork.entities.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {


}
