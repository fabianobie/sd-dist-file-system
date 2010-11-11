package br.ufc.mdcc.sd.sda.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.ufc.mdcc.sd.sda.servicos.ServicoArquivo;


/**
 * 
 * @author Fabiano
 *
 */
public class ArquivoServer {

	/**
	 * 
	 */
	public static ServicoArquivo servicoArquivo = new ServicoArquivo();
	public static String nomeServico = "sistemaArquivo";
	
	
	public static void main(String[] args) throws NotBoundException, RemoteException {
		
		try {
			
			Registry registryServidor = LocateRegistry.createRegistry(1099);
			Remote fileSkeleton = UnicastRemoteObject.exportObject(servicoArquivo);

			registryServidor.bind(nomeServico, fileSkeleton);
			
			System.out.println("Registrando objeto: 'sistemaArquivo'\nBinding na porta 1099");
			System.out.println("SERVIDOR EXECUTANDO...");

		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

		

		while(true);
		
}


}
