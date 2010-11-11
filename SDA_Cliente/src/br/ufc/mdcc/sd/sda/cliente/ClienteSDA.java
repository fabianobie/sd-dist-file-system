package br.ufc.mdcc.sd.sda.cliente;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.mdcc.sd.sda.ligador.IServicoLigador;

public class ClienteSDA {

	
	
	private static IServicoLigador stubServidorConfig;

	public static void main(String[] args) throws NotBoundException,
			IOException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		Registry registry = LocateRegistry.getRegistry("localhost", 1098);
		stubServidorConfig = (IServicoLigador) registry.lookup("servicos");

		/**
		 * TODO Aqui progamaremos o servico do cliente		
		 */
	}

}
