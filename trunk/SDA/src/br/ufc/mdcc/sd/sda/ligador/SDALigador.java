package br.ufc.mdcc.sd.sda.ligador;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author Fabiano
 *
 */
public class SDALigador {
	
	//Atributo que armazena
	public static ServidorConfig servConfig;

	public static void main(String[] args) throws NotBoundException,
			RemoteException, AlreadyBoundException {
		
		servConfig = new ServidorConfig();
		
		Registry registryServidor = LocateRegistry.createRegistry(1097);
		//Remote servSkeleton = UnicastRemoteObject.exportObject(servConfig);
		
		System.out.println("Registrando objeto: 'servicos'\nBinding na porta 1097");
		registryServidor.bind("servicos", servConfig);

		System.out.println("LIGADOR EXECUTANDO...");

		while (true);

	}
}
