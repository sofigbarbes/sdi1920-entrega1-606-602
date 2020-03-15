package socialNetwork.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void signUp(String email) {
		log.info("{} has been registered", email);
	}
	
	public void logIn(String email) {
		log.info("{} has logged in", email);
	}
	
	public void listUsers(String email) {
		log.info("{} is listing users", email);
	}

	public void sendReq(String senderEmail, String email) {
		log.info("{} has sent friend request to {}", senderEmail, email);
	}

	public void delete(String email) {
		log.info("{} has been deleted", email);
	}

	public void listFriends(String email) {
		log.info("{} is listing friends", email);
	}

	public void listReqs(String email) {
		log.info("{} is listing requests", email);
	}

	public void acceptReq(String myEmail, String email) {
		log.info("{} accepting request of {}", myEmail, email);
	}

	public void listPost(String email) {
		log.info("{} is listing posts", email);	
	}

	public void addPost(String email) {
		log.info("{} has added a post", email);
	}

	public void seeFriendPost(String me, String email) {
		log.info("{} is listing {}'s posts", me, email);
	}
	
	
}
