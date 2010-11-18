package br.ufc.mdcc.sd.sda.cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
import br.ufc.mdcc.sd.sda.ligador.IServidorConfig;
import br.ufc.mdcc.sd.sda.servicos.IServicoArquivo;
import br.ufc.mdcc.sd.sda.servicos.IServicoDiretorio;

public class ClienteSDA {
	

	/**
	 * 
	 */
	
	public static IServicoArquivo servicoArquivo;
	public static IServicoDiretorio servicoDiretorio;
	public static IServidorConfig servConfig;
	
	public static String nomeServicoArquivo = "sistemaArquivo";
	public static String nomeServicoDiretorio = "sistemaDiretorio";
	public static String nomeServicoServidor = "servicos";

		
		public static String clienteLigador() {
			boolean ok = false;
			Registry registry;
			while (!ok) {
			try {
				registry = LocateRegistry.getRegistry("127.0.0.1", 1097);
				servConfig = (IServidorConfig) registry.lookup(nomeServicoServidor);
				
				System.out.println("Conectou com "+nomeServicoServidor);
				
				ok=true;
				
				
			} catch (RemoteException e) {
				System.out.println("Servidor de Servidor de Informações Desligado !");
				ok = false;
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			}
			return null;
		}
	
	
		public static void clienteDiretorio(String endereco, String porta) {
			boolean ok = false;
			Registry registry;
			while (!ok) {
			try {
				registry = LocateRegistry.getRegistry(endereco, Integer.parseInt(porta));
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
	
	
		public static void clienteArquivo(String endereco, String porta) {
			boolean ok = false;
			Registry registry;
			while (!ok) {
				try {

					registry = LocateRegistry.getRegistry(endereco, Integer.parseInt(porta));
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
	
	public static void iniciarCliente() throws RemoteException{
		clienteLigador();
		String endereco = servConfig.getServicoArquivo().split(":")[0];
		String porta = servConfig.getServicoArquivo().split(":")[1];
		clienteArquivo(endereco,porta);
		
		endereco = servConfig.getServicoDiretorio().split(":")[0];
		porta = servConfig.getServicoDiretorio().split(":")[1];
		clienteDiretorio(endereco,porta);
		
		
		
	}

	
	
	
}
