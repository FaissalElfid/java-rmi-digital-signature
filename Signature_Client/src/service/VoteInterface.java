package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import bean.Candidat;
import bean.Vote;

public interface VoteInterface extends Remote {

	public String vote(Vote vote, Candidat candidat) throws RemoteException;

	public ArrayList<Candidat> getCandidats() throws RemoteException;

	// public Candidat getCandidatById(long id, Candidat candidat) throws RemoteException;

	public Candidat getCandidatById(long id)  throws RemoteException;

	// String vote(Vote vote, Candidat candidat) throws RemoteException;

}
