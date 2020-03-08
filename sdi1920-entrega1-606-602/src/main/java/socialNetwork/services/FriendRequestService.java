package socialNetwork.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		Page<FriendRequest> result = friendReqRep.findFriendsOfUser(pageable, user.getEmail());
		/*Page<FriendRequest> requests = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());
		LinkedList<FriendRequest> content = new LinkedList<FriendRequest>();
		requests=friendReqRep.findAll(pageable);
	
		for (FriendRequest fr : requests.getContent()) {
			if(fr.getReceiverEmail().equals(user.getEmail()) && fr.isAccepted()) {
				content.add(fr);
			}
		}
		
		Page<FriendRequest> result = new PageImpl<FriendRequest>(content);*/
		return result;
	}

	public Page<FriendRequest> getRequests(Pageable pageable, String email) {
		Page<FriendRequest> result = friendReqRep.findRequestToUser(pageable, email);
		return result;
	}
	
	
	public Page<FriendRequest> searchFriend(Pageable pageable, String searchtext, String email) {
		Page<FriendRequest> users = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());

		LinkedList<FriendRequest> content = new LinkedList<FriendRequest>();
		users = friendReqRep.searchFriend(pageable, "%" + searchtext + "%");
		for (FriendRequest u : users.getContent()) {
			if (!u.getSenderEmail().equals(email)) {
				content.add(u);
			}
		}
		Page<FriendRequest> result = new PageImpl<FriendRequest>(content);
		return result;
	}


	
	

}
