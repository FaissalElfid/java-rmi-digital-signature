package clients;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import bean.Candidat;
import bean.Vote;
import bean.Voter;
import service.VoteInterface;
import utils.DigitalSignature;

public class Client2 {
	public static void main(String args[]) {
		// Recherche de l'objet distant
		String url = "rmi://localhost/vote";
		try {
			VoteInterface voteInterface = (VoteInterface) Naming.lookup(url);

			// création d'un voter
			Voter voter = new Voter();
			voter.setKeyPair(DigitalSignature.generateKeyPair());
			voter.setFullName("Faissal EL FID 2");

			// afficher tous les candidats existants
			ArrayList<Candidat> candidats = voteInterface.getCandidats();
			System.out.println("Choisissez le candidat que vous voulez !!");
			candidats.forEach(c -> {
				System.out.println("candidate id :" + c.getId() + "  full name: " + c.getFullName() + " ");
			});

			// saisit des votes
			for (int i = 0; i < 2; i++) {

				Scanner sc = new Scanner(System.in);
				long candidateId = sc.nextLong(); // le vote se fait grace a l'id du candidat

				String msg = voter.getFullName() + String.valueOf(candidateId); // le message à signer

				byte[] signMessage = DigitalSignature.signMessage(voter.getKeyPair().getPrivate(), msg); // signer le message
				
				// initier le vote
				Vote vote = new Vote();
				vote.setCandidatId(candidateId);
				vote.setMessage(msg);
				vote.setPublicKey(voter.getKeyPair().getPublic());
				vote.setSignature(signMessage);

				Candidat candidat = voteInterface.getCandidatById(candidateId);

				String result = voteInterface.vote(vote, candidat); // attribuer le vote au candidat
				System.out.println(result);

				if (!result.equals("Vous avez déja voté !!")) {
					candidat = voteInterface.getCandidatById(candidateId);
					System.out.println("count vote apres : " + String.valueOf(candidat.getCount()) + " sur "
							+ String.valueOf(candidat.getFullName()));
				}

				System.out.println("resultats !");
				candidats = voteInterface.getCandidats();
				candidats.forEach(c -> {
					System.out.println("candidate id :" + c.getId() + "  full name: " + c.getFullName() + " count :"
							+ c.getPublicKeysVoted().size());
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
