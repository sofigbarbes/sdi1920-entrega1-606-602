package socialNetwork.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import socialNetwork.services.FriendRequestID;

@Entity 
public class FriendRequest implements Serializable{
	
	@Id FriendRequestID id;

	public FriendRequest(String senderEmail, String receiverEmail) {
		super();
		id=new FriendRequestID(senderEmail, receiverEmail);
	}
	
	public FriendRequest() {
	}
}

