package br.ufc.mdcc.sd.sda.ligador;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class LigadorSDA {

	
	public static ServicoLigador servConfig = new ServicoLigador();

	public static void main(String[] args) throws NotBoundException,
			RemoteException, AlreadyBoundException {

		Registry registryServidor = LocateRegistry.createRegistry(1098);
		Remote servSkeleton = UnicastRemoteObject.exportObject(servConfig);
		
		System.out.println("Registrando objeto: 'servicos'\nBinding na porta 1098");
		registryServidor.bind("servicos", servSkeleton);

		System.out.println("LIGADOR EXECUTANDO...");

		while (true);

	}
}
