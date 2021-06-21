package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;

import bean.Candidat;
import bean.Vote;
import service.VoteInterface;
import storage.Storage;
import utils.DigitalSignature;

public class VoteImpl extends UnicastRemoteObject implements VoteInterface {

	public VoteImpl() throws RemoteException {
		super();
	}

	@Override
	public String vote(Vote vote, Candidat candidat) throws RemoteException {
		try {
			boolean correct = DigitalSignature.verify(vote.getPublicKey(), vote.getMessage(), vote.getSignature());
			if (!correct) {
				return "signature est incorrect";
			}
			
			
			boolean voted = alreadyVoted(vote);
			// candidat.getPublicKeysVoted().contains(vote.getPublicKey());

			if (voted) {
				return "Vous avez déja voté !!";
			}

						
			Storage.candidats.forEach(c -> {
				if (c.getId() == vote.getCandidatId()) {
					// increase the counter
					c.setCount(c.getCount() + 1);
					// add the public key
					ArrayList<PublicKey> publicKeysVoted = c.getPublicKeysVoted();
					publicKeysVoted.add(vote.getPublicKey());
					candidat.setPublicKeysVoted(publicKeysVoted);
				}
			});

			return "Votre vote a été comptété avec succès.";

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Candidat> getCandidats() throws RemoteException {
		// TODO Auto-generated method stub
		return Storage.candidats;
	}
	
	public boolean alreadyVoted(Vote vote) {
		boolean voted;
		// je boucle sur les pubkey stocker dans les candidats
		for (Candidat candidat_elem : Storage.candidats) {
			 voted = candidat_elem.getPublicKeysVoted().contains(vote.getPublicKey());
			 if (voted == true) {
				 return true;
			 }
		}
		return false;
	}

	@Override
	public Candidat getCandidatById(long id) {

		for (Candidat c : Storage.candidats) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
}
