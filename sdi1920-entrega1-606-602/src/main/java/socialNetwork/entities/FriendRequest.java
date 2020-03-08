package socialNetwork.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import socialNetwork.services.FriendRequestID;

@Entity
public class FriendRequest implements Serializable {

	@Id
	FriendRequestID id;
	boolean accepted;

	public FriendRequest(String senderEmail, String receiverEmail) {
		super();
		id = new FriendRequestID(senderEmail, receiverEmail);
	}

	public FriendRequest() {
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
