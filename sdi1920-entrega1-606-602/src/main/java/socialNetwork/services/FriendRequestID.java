package socialNetwork.services;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendRequestID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String senderEmail;
	String receiverEmail;
	
	
	public FriendRequestID(String sender, String receiver) {
		this.senderEmail=sender;
		this.receiverEmail=receiver;
	}

	public FriendRequestID() {
		
	}
	
	public String getSender() {
		return senderEmail;
	}

	public String getReceiver() {
		return receiverEmail;
	}

}
