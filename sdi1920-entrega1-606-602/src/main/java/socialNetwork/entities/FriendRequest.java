package socialNetwork.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import socialNetwork.services.FriendRequestID;

@Entity
public class FriendRequest implements Serializable {

	@Id
	FriendRequestID id;

	boolean accepted;

	public FriendRequest(String senderEmail, String receiverEmail, boolean accepted) {
		super();
		id = new FriendRequestID(senderEmail, receiverEmail);

		this.accepted=accepted;
	}

	public FriendRequest() {
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public String getSenderEmail() {
		return id.getSender();
	}
	public String getReceiverEmail() {
		return id.getReceiver();
	}
}
