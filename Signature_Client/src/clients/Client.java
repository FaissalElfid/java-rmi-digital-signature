package clients;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import bean.Candidat;
import bean.Vote;
import bean.Voter;
import service.VoteInterface;
import utils.DigitalSignature;

public class Client {
	public static void main(String args[]) {
		// Recherche de LOD
		String url = "rmi://localhost/vote";
		try {
			VoteInterface voteInterface = (VoteInterface) Naming.lookup(url);

			// création d'un voter
			Voter voter = new Voter();
			voter.setKeyPair(DigitalSignature.generateKeyPair());
			voter.setFullName("Faissal EL FID");

			// afficher tous les candidats existants
			ArrayList<Candidat> candidats = voteInterface.getCandidats();
			System.out.println("Choisissez le candidat que vous voulez !!");
			candidats.forEach(c -> {
				System.out.println("candidate id :" + c.getId() + "  full name: " + c.getFullName() + " ");
			});

			// entrer les votes
			for (int i = 0; i < 2; i++) {

				Scanner sc = new Scanner(System.in);
				long candidateId = sc.nextLong();

				String msg = voter.getFullName() + String.valueOf(candidateId);

				byte[] signMessage = DigitalSignature.signMessage(voter.getKeyPair().getPrivate(), msg);

				Vote vote = new Vote();
				vote.setCandidatId(candidateId);
				vote.setMessage(msg);
				vote.setPublicKey(voter.getKeyPair().getPublic());
				vote.setSignature(signMessage);

				Candidat candidat = voteInterface.getCandidatById(candidateId);

				String result = voteInterface.vote(vote, candidat);
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
