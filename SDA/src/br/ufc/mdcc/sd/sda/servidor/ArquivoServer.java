package br.ufc.mdcc.sd.sda.servidor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.HashMap;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.Permissao;
import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.servicos.IServicoDiretorio;
import br.ufc.mdcc.sd.sda.servicos.ServicoArquivo;
import br.ufc.mdcc.sd.sda.util.FileUtil;

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

	
		public static void servidorArquivo() {

			try {
				servicoArquivo = new ServicoArquivo();
				//servicoArquivo.setRoot(formatSistemaDeArquivo());
				
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	 
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
	
	public static IServicoDiretorio getServicoDiretorio() {
		return servicoDiretorio;
	}
	


	public static void main(String[] args) throws InterruptedException, URISyntaxException, IOException {
		
		servidorArquivo();
		
		clienteArquivo();
	
		
		while(true);
	}
	
	

}
