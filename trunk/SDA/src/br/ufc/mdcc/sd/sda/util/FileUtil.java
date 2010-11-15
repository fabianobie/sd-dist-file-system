package br.ufc.mdcc.sd.sda.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.RSA;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.InexistenteException;

public class FileUtil {

	public static final String DIR_ROOT = "C:\\hardDisk";

	public static void serializarFile(FileSD arq) {
		String nome = DIR_ROOT + File.separator + arq.getName();
		try {
			ObjectOutput out = new ObjectOutputStream(
					new FileOutputStream(nome));
			out.writeObject(arq);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static FileSD deserializarFile(Ufid ufid) throws InexistenteException {

		String nome = DIR_ROOT + File.separator + ufid.getNumArquivo()+"_"+ufid.getEndereco()+"_"+ufid.getData();

		FileSD object = null;
		try {
			File file = new File(nome);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			object = (FileSD) in.readObject();
			in.close();
		} catch (Exception e) {
			
			throw new InexistenteException();
			
		}

		return object;
	}

	public static byte[] serializarObjeto(Object object) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bout);
		out.writeObject(object);
		out.close();
		return bout.toByteArray();
	}

	public static Object deserializarObjeto(byte[] dados) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream bin = new ByteArrayInputStream(dados);
		ObjectInputStream in = new ObjectInputStream(bin);
		Object object = in.readObject();
		in.close();
		return object;
	}

	public static byte[] addbytes(byte[] dados, byte[] dadosTmp) {
		int size = dados.length;

		byte[] dadosTotal = new byte[size + dadosTmp.length];

		for (int i = 0; i < dadosTotal.length; i++) {
			if (i < size)
				dadosTotal[i] = dados[i];
			else
				dadosTotal[i] = dadosTmp[i - size];
		}

		return dadosTotal;
	}

	public static boolean hasPermissao(String permissao, ModoAcesso modoacesso, int userId) {
		
			String[] codVerificao = permissao.split("_");
			String numRandom = codVerificao[0];
			String modos = codVerificao[1];
			
		return modos.contains(modoacesso.getValue());
	}

	public static ArrayList<String> buscaArquivos(HashMap arquivos, String regex) {
		ArrayList<String> results = new ArrayList<String>();

		Pattern p = Pattern.compile(regex);

		Set<String> keys = arquivos.keySet();
		Iterator<String> ite = keys.iterator();

		while (ite.hasNext()) {
			String candidate = ite.next();
			Matcher m = p.matcher(candidate);
			System.out.println("Attempting to match: " + candidate + " to "
					+ regex);
			if (m.matches()) {
				System.out.println("it matches");
				results.add(candidate);
			}
		}

		if (results.isEmpty()) {
			return null;
		} else {
			return results;
		}
	}
	
	public static String criptografa(String chavePublica, String  mensagem){
		BigInteger publicKey = new BigInteger(chavePublica);
		return RSA.encripta(mensagem, chavePublica);
	}

}
