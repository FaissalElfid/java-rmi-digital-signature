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
	// cette methode permet de générer le pair de cles (privée + public)
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
		// keyGen est un générateur des clés spectifique à l'algorithm "DSA"
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN"); 	// DSA : digital signature algorithm, SUN: provider
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN"); 		// SHA1PRING: algorithm de signature, SUN: provider
		keyGen.initialize(1024, random); 										// initialiser le générateur des clés sur la taille des clés avec l'algorithm de cryptage
		KeyPair pair = keyGen.generateKeyPair(); 								// générer la pair de clés
		return pair;
	}
	// cette methode permet de signer un message avec la private key (chez le client)
	public static byte[] signMessage(PrivateKey sk, String message)
			throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
		Signature sig = Signature.getInstance("SHA1withDSA", "SUN"); 			// SHA1PRING: algorithmde signature, SUN: provider
		sig.initSign(sk); 														// Cette méthode prend la clé privée comme paramètre pour générer la signature.
		sig.update(message.getBytes()); 										// mettre a jour le message
		return sig.sign(); 														// envoyer la signature byte[]
	}

}
