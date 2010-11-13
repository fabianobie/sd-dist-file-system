package br.ufc.mdcc.sd.sda.entidade;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {
	int tamPrimo;
	BigInteger n, q, p;
	BigInteger modulo;
	BigInteger chavePublica, chavePrivada;
	private SecureRandom random = new SecureRandom();

	
	public RSA(){
		this.tamPrimo = 10;
		geraPrimos(); 
		gerarChaves();
	}

	public BigInteger getModulo() {
		return modulo;
	}

	public BigInteger getChavePublica() {
		return chavePublica;
	}

	public BigInteger getChavePrivada() {
		return chavePrivada;
	}


	public void geraPrimos() {
		p = BigInteger.probablePrime(tamPrimo/2, random);
	    q = BigInteger.probablePrime(tamPrimo/2, random);
	}

	private void gerarChaves() {
		
		n = p.multiply(q);
		
		modulo = p.subtract(BigInteger.valueOf(1));
		modulo = modulo.multiply(q.subtract(BigInteger.valueOf(1)));
		
		do
			chavePublica = new BigInteger(2 * tamPrimo, new Random());
		while ((chavePublica.compareTo(modulo) != -1)
				|| (chavePublica.gcd(modulo).compareTo(BigInteger.valueOf(1)) != 0));
		
		chavePrivada = chavePublica.modInverse(modulo);
	}

	public BigInteger[] encripta(String mensagem) {
		int i;
		byte[] temp = new byte[1];
		byte[] digitos = mensagem.getBytes();
		BigInteger[] bigdigitos = new BigInteger[digitos.length];
		for (i = 0; i < bigdigitos.length; i++) {
			temp[0] = digitos[i];
			bigdigitos[i] = new BigInteger(temp);
		}
		BigInteger[] encriptado = new BigInteger[bigdigitos.length];
		for (i = 0; i < bigdigitos.length; i++)
			encriptado[i] = bigdigitos[i].modPow(chavePublica, n);
		return (encriptado);
	}

	public String desencripta(BigInteger[] encriptado) {
		BigInteger[] desencriptado = new BigInteger[encriptado.length];
		for (int i = 0; i < desencriptado.length; i++)
			desencriptado[i] = encriptado[i].modPow(chavePrivada, n);
		char[] charArray = new char[desencriptado.length];
		for (int i = 0; i < charArray.length; i++)
			charArray[i] = (char) (desencriptado[i].intValue());
		return (new String(charArray));
	}

	
	  public static void main(String[] args) {
	
	      RSA key = new RSA();
	 
	      String message = "12345wqr";

	      
	      BigInteger[] encrypt = key.encripta(message);
	      String decrypt = key.desencripta(encrypt);
	      System.out.println("message   = " + message);
	      System.out.print("encrpyted = " );
	      for (BigInteger bigInteger : encrypt) {
	    	  System.out.print(bigInteger);
	      }
	      
	      System.out.println("\ndecrypted = " + decrypt);
	   }

}
