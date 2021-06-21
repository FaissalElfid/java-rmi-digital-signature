package bean;

import java.io.Serializable;
import java.security.KeyPair;

public class Voter implements Serializable {
	private static final long serialVersionUID = 5436466431301179900L;
	// le voter est le client
	private String fullName;
	private KeyPair keyPair;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

}
