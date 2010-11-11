package br.ufc.mdcc.sd.sda.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.ufc.mdcc.sd.sda.servicos.ServicoArquivo;
import br.ufc.mdcc.sd.sda.servicos.ServicoDiretorio;


/**
 * 
 * @author Fabiano
 *
 */
public class DiretorioServer {

	/**
	 * 
	 */
	public static ServicoDiretorio servicoDiretorio = new ServicoDiretorio();
	public static String nomeServico = "sistemaDiretorio";
	
	
	public static void main(String[] args) throws NotBoundException, RemoteException {
		
		try {
			
			Registry registryServidor = LocateRegistry.createRegistry(1098);
			Remote dirSkeleton = UnicastRemoteObject.exportObject(servicoDiretorio);

			registryServidor.bind(nomeServico, dirSkeleton);
			
			System.out.println("Registrando objeto: 'sistemaDiretorio'\nBinding na porta 1099");
			System.out.println("SERVIDOR EXECUTANDO...");

		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

		

		while(true);
		
}


}
