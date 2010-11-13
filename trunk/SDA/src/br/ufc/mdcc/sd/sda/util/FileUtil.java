package br.ufc.mdcc.sd.sda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

public class FileUtil {
	
	   private final static BigInteger one      = new BigInteger("1");
	   private final static SecureRandom random = new SecureRandom();

	   private BigInteger privateKey;
	   private BigInteger publicKey;
	   private BigInteger modulus;

//	   // generate an N-bit (roughly) public and private key
//	   class RSA{
//		   RSA(int N) {
//			      BigInteger p = BigInteger.probablePrime(N/2, random);
//			      BigInteger q = BigInteger.probablePrime(N/2, random);
//			      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
//
//			      modulus    = p.multiply(q);                                  
//			      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
//			      privateKey = publicKey.modInverse(phi);
//			   }
//
//
//			   BigInteger encrypt(BigInteger message) {
//			      return message.modPow(publicKey, modulus);
//			   }
//
//			   BigInteger decrypt(BigInteger encrypted) {
//			      return encrypted.modPow(privateKey, modulus);
//			   }
//
//			   public String toString() {
//			      String s = "";
//			      s += "public  = " + publicKey  + "\n";
//			      s += "private = " + privateKey + "\n";
//			      s += "modulus = " + modulus;
//			      return s;
//			   }
//
//	   }
//	 
//	   public static void main(String[] args) {
//	      int N = Integer.parseInt(args[0]);
//	      RSA key = new RSA(N);
//	      System.out.println(key);
//	 
//	      // create random message, encrypt and decrypt
//	      BigInteger message = new BigInteger(N-1, random);
//
//	      //// create message by converting string to integer
//	      // String s = "test";
//	      // byte[] bytes = s.getBytes();
//	      // BigInteger message = new BigInteger(s);
//
//	      BigInteger encrypt = key.encrypt(message);
//	      BigInteger decrypt = key.decrypt(encrypt);
//	      System.out.println("message   = " + message);
//	      System.out.println("encrpyted = " + encrypt);
//	      System.out.println("decrypted = " + decrypt);
//	   }
//	
//	
	public static void serializarObjeto(Object object, String nome) {

		try {
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream(nome));
			out.writeObject(object);
			out.close();

		} catch (IOException e) {
		}

	}

	public static Object deserializarObjeto(String nome) {
		Object object = null;
		try {
			File file = new File(nome);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			object = in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		return object;
	}
	
	

}
