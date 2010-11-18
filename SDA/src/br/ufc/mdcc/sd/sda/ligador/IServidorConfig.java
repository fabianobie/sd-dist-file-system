package br.ufc.mdcc.sd.sda.ligador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface IServidorConfig extends Remote {
	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String getServicoArquivo() throws RemoteException;
	
	String getServicoDiretorio() throws RemoteException;
	
	String getNomeServico() throws RemoteException;

	void setServicos(String nome, String obj) throws RemoteException;
	 
	 

}
