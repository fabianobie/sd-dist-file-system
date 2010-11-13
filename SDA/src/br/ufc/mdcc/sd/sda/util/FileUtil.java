package br.ufc.mdcc.sd.sda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class FileUtil {

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
