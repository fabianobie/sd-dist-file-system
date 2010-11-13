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
import java.util.Map;

import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.Ufid;

public class FileUtil {

	public static final String DIR_ROOT = "C:\\hardDisk";

	public static void serializarFile(FileSD arq) {
		String nome = DIR_ROOT + File.separator + arq.getUfid();
		try {
			ObjectOutput out = new ObjectOutputStream(
					new FileOutputStream(nome));
			out.writeObject(arq);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static FileSD deserializarFile(Ufid ufid) {

		String nome = DIR_ROOT + File.separator + ufid;

		FileSD object = null;
		try {
			File file = new File(nome);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			object = (FileSD) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
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

	public static Object deserializarObjeto(byte[] dados) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bin = new ByteArrayInputStream(dados);
		ObjectInputStream in = new ObjectInputStream(bin);
		Object object = in.readObject();
		in.close();
		return object;
	}

}
