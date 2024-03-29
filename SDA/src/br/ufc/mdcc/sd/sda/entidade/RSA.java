package br.ufc.mdcc.sd.sda.entidade;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {
	int tamPrimo;
	BigInteger nprimo, q, p;
	BigInteger modulo;
	BigInteger chavePublica, chavePrivada;
	private SecureRandom random = new SecureRandom();

	
	public RSA(){
		this.tamPrimo = 10;
		geraPrimos(); 
		gerarChaves();
	}

	public String getChavePublica() {
		return chavePublica.toString()+"."+nprimo.toString();
	}


	public void geraPrimos() {
		p = BigInteger.probablePrime(tamPrimo/2, random);
	    q = BigInteger.probablePrime(tamPrimo/2, random);
	}

	private void gerarChaves() {
		
		nprimo = p.multiply(q);
		
		modulo = p.subtract(BigInteger.valueOf(1));
		modulo = modulo.multiply(q.subtract(BigInteger.valueOf(1)));
		
		do
			chavePublica = new BigInteger(2 * tamPrimo, new Random());
		while ((chavePublica.compareTo(modulo) != -1)
				|| (chavePublica.gcd(modulo).compareTo(BigInteger.valueOf(1)) != 0));
		System.out.println(getChavePublica());
		chavePrivada = chavePublica.modInverse(modulo);
	}

	public static String encripta(String mensagem, String chavePublica) {
		String[] chaves = chavePublica.split("\\.");
		byte[] temp = new byte[1];
		byte[] digitos = mensagem.getBytes();
		String num = chavePublica.substring(chavePublica.length()/2);
		BigInteger[] bigdigitos = new BigInteger[digitos.length];
		for (int i = 0; i < bigdigitos.length; i++) {
			temp[0] = digitos[i];
			bigdigitos[i] = new BigInteger(temp);
		}
		BigInteger[] encriptado = new BigInteger[bigdigitos.length];
		for (int i = 0; i < bigdigitos.length; i++)
			encriptado[i] = bigdigitos[i].modPow(new BigInteger(chaves[0]), new BigInteger(chaves[1]));
		
		String result ="";
		for (int i=0; i<encriptado.length;i++) {
	    	  result+=encriptado[i];
	    	  if(i+1<encriptado.length)
	    		  result+=".";
	     }
		
		return result;
	}

	public String desencripta(String msgmCrip) {
		String[] encrBigInt = msgmCrip.split("\\.");
		BigInteger[] encriptado = new BigInteger[encrBigInt.length];
		for (int i=0; i<encriptado.length;i++) {
	    	  encriptado[i] = new BigInteger(encrBigInt[i]);
	     }
		
		BigInteger[] desencriptado = new BigInteger[encriptado.length];
		for (int i = 0; i < desencriptado.length; i++)
			desencriptado[i] = encriptado[i].modPow(chavePrivada, nprimo);
		char[] charArray = new char[desencriptado.length];
		for (int i = 0; i < charArray.length; i++)
			charArray[i] = (char) (desencriptado[i].intValue());
		return (new String(charArray));
	}

	
	  public static void main(String[] args) {
	
	      RSA key = new RSA();
	 
	      String message = "Fabiano Tavares da Silva";

	      
	      String encrypt = RSA.encripta(message, key.getChavePublica());
	      String decrypt = key.desencripta(encrypt);
	      
	      System.out.println("message   = " + message);
	      System.out.print("encrpyted = " );
	      System.out.println(encrypt);
	      System.out.println("\ndecrypted = " + decrypt);
	   }

}
