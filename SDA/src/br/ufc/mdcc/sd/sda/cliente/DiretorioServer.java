package br.ufc.mdcc.sd.sda.cliente;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.mdcc.sd.sda.servicos.IServicoArquivo;
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
	public static ServicoDiretorio servicoDiretorio;
	public static IServicoArquivo servicoArquivo;

	public static String nomeServicoArquivo = "sistemaArquivo";
	public static String nomeServicoDiretorio = "sistemaDiretorio";

	private static class ServidorDiretorio implements Runnable {
		public void run() {

			try {
				servicoDiretorio = new ServicoDiretorio();
				Registry registryServidor = LocateRegistry.createRegistry(1099);
				// Remote fileSkeleton =
				// UnicastRemoteObject.exportObject(servicoArquivo);

				registryServidor.bind(nomeServicoDiretorio, servicoDiretorio);

				System.out
						.println("Registrando objeto: 'sistemaDiretorio'\nBinding na porta 1099");
				System.out.println("SERVIDOR EXECUTANDO...");

			} catch (AlreadyBoundException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (true);
			
		}
	}

	private static class ClienteDiretorio implements Runnable {
		public void run() {
			Registry registry;
			while (true) {
				try {

					registry = LocateRegistry.getRegistry("127.0.0.1", 1098);
					servicoArquivo = (IServicoArquivo) registry
							.lookup(nomeServicoArquivo);

					System.out.println("Conectou com " + nomeServicoArquivo);

				} catch (RemoteException e) {
					System.out.println("Servidor Desligado !");
					
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new ServidorDiretorio());
		t1.start();

		//Thread t2 = new Thread(new ClienteDiretorio());
		//t2.start();

	}

}
