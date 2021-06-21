package bean;

import java.io.Serializable;
import java.security.PublicKey;

public class Vote implements Serializable {
	private static final long serialVersionUID = 9122254685397780304L;
	private long candidatId;
	private byte[] signature;
	private PublicKey publicKey;
	private String message;

	public long getCandidatId() {
		return candidatId;
	}

	public void setCandidatId(long candidatId) {
		this.candidatId = candidatId;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
