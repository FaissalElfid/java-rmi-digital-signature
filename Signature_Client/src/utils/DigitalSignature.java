package utils;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSignature {
	// cette methode permet de g�n�rer le pair de cles (priv�e + public)
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
		// keyGen est un g�n�rateur des cl�s spectifique � l'algorithm "DSA"
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN"); // DSA : digital signature algorithm, SUN:
																				// provider
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN"); // SHA1PRING: algorithm de cryptage, SUN:
																			// provider
		keyGen.initialize(1024, random); // initialiser le g�n�rateur des cl�s sur la taille des cl�s avec l'algorithm
											// de cryptage
		KeyPair pair = keyGen.generateKeyPair(); // g�n�rer la pair de cl�s
		return pair;
	}

	// cette methode permet de signer un message avec la private key (chez le
	// client)
	public static byte[] signMessage(PrivateKey sk, String message)
			throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
		Signature sig = Signature.getInstance("SHA1withDSA", "SUN"); // SHA1PRING: algorithmde signature, SUN: provider
		sig.initSign(sk); // Cette m�thode prend la cl� priv�e comme param�tre pour g�n�rer la signature.
		sig.update(message.getBytes()); // mettre a jour le message
		return sig.sign(); // envoyer la signature byte[]
	}

}
