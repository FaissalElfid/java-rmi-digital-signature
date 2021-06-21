package utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSignature {

	public static boolean verify(PublicKey pk, String message, byte[] signatrue)
			throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
		Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
		sig.initVerify(pk); 			//Initialisation, avec une clé publique, qui initialise la vérification de la signature 
		sig.update(message.getBytes()); // mettre a jour le message
		return sig.verify(signatrue); 	// verifier la signature
	}
}
