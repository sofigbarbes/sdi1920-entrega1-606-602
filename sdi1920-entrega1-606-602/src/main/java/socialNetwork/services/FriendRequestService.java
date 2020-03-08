package socialNetwork.services;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
		Page<FriendRequest> requests = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());
		requests = friendReqRep.findAllByUser(pageable, user.getEmail());

		return requests;
	}
}
