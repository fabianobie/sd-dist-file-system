package br.ufc.mdcc.sd.sda.servidor;

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

	public static void setServicoArquivo(IServicoArquivo servicoArquivo) {
		DiretorioServer.servicoArquivo = servicoArquivo;
	}

	public static String nomeServicoArquivo = "sistemaArquivo";
	public static String nomeServicoDiretorio = "sistemaDiretorio";

	 
		public static void servidorDiretorio() {

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

			
			
		}
	

	 
		public static void clienteDiretorio() {
			boolean ok = false;
			Registry registry;
			while (!ok) {
				try {

					registry = LocateRegistry.getRegistry("127.0.0.1", 1098);
					servicoArquivo = (IServicoArquivo) registry
							.lookup(nomeServicoArquivo);
					
					System.out.println("Conectou com " + nomeServicoArquivo);
					ok=true;
					
				} catch (RemoteException e) {
					System.out.println("Servidor Desligado !");
					ok = false;
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}

		}

	
	public static IServicoArquivo getServicoArquivo() {
		return servicoArquivo;
	}

	public static void main(String[] args) throws InterruptedException {

		servidorDiretorio();
		
		clienteDiretorio();
	
		while (true);
	}
	


}
