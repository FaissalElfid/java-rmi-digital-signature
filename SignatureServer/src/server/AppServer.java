package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import bean.Candidat;
import serviceImpl.VoteImpl;
import storage.Storage;

public class AppServer {
	public static void main(String args[]) {
		try {
			// création des candidats
			Candidat candidat1 = new Candidat();
			candidat1.setFullName("EL FID Faissal");
			candidat1.setId(1);

			Candidat candidat2 = new Candidat();
			candidat2.setFullName("ABIDAR Yassine");
			candidat2.setId(2);

			Candidat candidat3 = new Candidat();
			candidat3.setFullName("MEKMASSI Mohamed Ali");
			candidat3.setId(3);

			Storage.candidats.add(candidat1);
			Storage.candidats.add(candidat2);
			Storage.candidats.add(candidat3);

			VoteImpl VoteImpl = new VoteImpl();
			LocateRegistry.createRegistry(1099);

			// Enregistrement de l'objet distant dans RMI
			Naming.rebind("vote", VoteImpl);

			System.out.println("Serveur Pret ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
