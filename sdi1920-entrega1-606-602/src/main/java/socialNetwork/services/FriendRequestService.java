package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import socialNetwork.entities.FriendRequest;
import socialNetwork.entities.User;
import socialNetwork.repositories.FriendRequestRepository;

@Service
public class FriendRequestService {
	@Autowired
	public FriendRequestRepository friendReqRep;

	public void addFriendRequest(FriendRequest fr) {
		friendReqRep.save(fr);
	}
	

	@PostConstruct
	public void init() {
	}

	public Page<FriendRequest> getRequestsForUser(Pageable pageable, User user) {
		Page<FriendRequest> result = friendReqRep.findFriendsOfUser(pageable, user.getEmail());
		return result;
	}

	public Page<FriendRequest> getRequests(Pageable pageable, String email) {
		Page<FriendRequest> result = friendReqRep.findRequestToUser(pageable, email);
		return result;
	}

	public void acceptRequest( String sender, String receiver) {
		friendReqRep.acceptRequest1(sender, receiver);
		friendReqRep.acceptRequest2(sender, receiver);
	}


	
	

}
