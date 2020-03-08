package socialNetwork.services;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendRequestID implements Serializable{
	String senderEmail;
	String receiverEmail;
	
	public FriendRequestID(String sender, String receiver) {
		this.senderEmail=sender;
		this.receiverEmail=receiver;
	}

	public FriendRequestID() {
		
	}
}
