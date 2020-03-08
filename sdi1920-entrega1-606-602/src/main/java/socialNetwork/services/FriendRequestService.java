package socialNetwork.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialNetwork.entities.FriendRequest;
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
}
