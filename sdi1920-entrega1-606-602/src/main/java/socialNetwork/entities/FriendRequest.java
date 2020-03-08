package socialNetwork.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import socialNetwork.services.FriendRequestID;

@Entity 
public class FriendRequest implements Serializable{
	
	@Id FriendRequestID id;
	boolean accepted;

	public FriendRequest(String senderEmail, String receiverEmail, boolean accepted) {
		super();
		this.id=new FriendRequestID(senderEmail, receiverEmail);
		this.accepted=accepted;
	}
	
	public FriendRequest() {
	}
}

