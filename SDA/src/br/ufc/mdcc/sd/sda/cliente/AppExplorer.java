package br.ufc.mdcc.sd.sda.cliente;

import java.rmi.RemoteException;

import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;

public class AppExplorer {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		ClienteSDA.iniciarCliente();
		
		ModuloCliente modCliente = new ModuloCliente();
		
		modCliente.criarArquivo("teste.txt", TipoArquivo.FILE);
		
		
		
		

	}

}
