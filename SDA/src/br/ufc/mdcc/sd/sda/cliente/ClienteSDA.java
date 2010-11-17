package br.ufc.mdcc.sd.sda.cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
import br.ufc.mdcc.sd.sda.servicos.IServicoArquivo;
import br.ufc.mdcc.sd.sda.servicos.IServicoDiretorio;

public class ClienteSDA {
	

	/**
	 * 
	 */
	
	public static IServicoArquivo servicoArquivo;
	public static IServicoDiretorio servicoDiretorio;
	
	public static String nomeServicoArquivo = "sistemaArquivo";
	public static String nomeServicoDiretorio = "sistemaDiretorio";


	
		public static void clienteArquivo() {
			boolean ok = false;
			Registry registry;
			while (!ok) {
			try {
				registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
				servicoDiretorio = (IServicoDiretorio) registry.lookup(nomeServicoDiretorio);
				
				System.out.println("Conectou com "+nomeServicoDiretorio);
				
				ok=true;
				
			} catch (RemoteException e) {
				System.out.println("Servidor de Arquivo Desligado !");
				ok = false;
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
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
					System.out.println("Servidor de Diretorio Desligado !");
					ok = false;
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}

		}
	
	public static IServicoDiretorio getServicoDiretorio() {
		return servicoDiretorio;
	}

	public static IServicoArquivo getServicoArquivo() {
		return servicoArquivo;
	}
	
	public static void iniciarCliente(){
		clienteDiretorio();
		clienteArquivo();
		
	}

	
	
	
}
