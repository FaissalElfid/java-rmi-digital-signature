package bean;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;

public class Candidat implements Serializable {
	private long id;
	private String fullName;
	private int count;
	private  ArrayList<PublicKey> publicKeysVoted = new ArrayList<PublicKey>();
	
	public ArrayList<PublicKey> getPublicKeysVoted() {
		return publicKeysVoted;
	}
	
	public void setPublicKeysVoted(ArrayList<PublicKey> publicKeysVoted) {
		this.publicKeysVoted = publicKeysVoted;
	}
	
	public Candidat() {
		this.count = 0;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
