package br.ufc.mdcc.sd.sda.cliente;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.mdcc.sd.sda.servicos.IServicoDiretorio;
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
	
	public static ServicoArquivo servicoArquivo;
	public static IServicoDiretorio servicoDiretorio;
	
	public static String nomeServicoArquivo = "sistemaArquivo";
	public static String nomeServicoDiretorio = "sistemaDiretorio";

	private static class ServidorArquivo implements Runnable {
		public void run() {

			try {
				servicoArquivo = new ServicoArquivo();
				Registry registryServidor = LocateRegistry.createRegistry(1098);
				//Remote fileSkeleton = UnicastRemoteObject.exportObject(servicoArquivo);

				registryServidor.bind(nomeServicoArquivo, servicoArquivo);

				System.out
						.println("Registrando objeto: 'sistemaArquivo'\nBinding na porta 1099");
				System.out.println("SERVIDOR EXECUTANDO...");

			} catch (AlreadyBoundException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true);
		}
	}
	
	private static class ClienteArquivo implements Runnable {
		public void run() {
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
				servicoDiretorio = (IServicoDiretorio) registry.lookup(nomeServicoDiretorio);
				
				System.out.println("Conectou com "+nomeServicoDiretorio);
				
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
		}
	}


	public static void main(String[] args) throws InterruptedException {
		
		//Thread t1 = new Thread(new ServidorArquivo());
		//t1.start();
		
		Thread t2 = new Thread(new ClienteArquivo());
		t2.start();

	}

}
