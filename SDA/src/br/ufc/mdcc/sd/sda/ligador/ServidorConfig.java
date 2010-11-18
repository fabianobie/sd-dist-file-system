package br.ufc.mdcc.sd.sda.ligador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorConfig  extends UnicastRemoteObject implements IServidorConfig {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6234976737691756613L;
	private String servico;
	private String nome;
	
	protected ServidorConfig() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setServicos(String nome, String obj) throws RemoteException {
		this.nome = nome;
		this.servico = obj;
	}

	@Override
	public String getNomeServico() throws RemoteException {
		return nome;
	}

	@Override
	public String getServicoArquivo() throws RemoteException {
		return "localhost:1098";
	}

	@Override
	public String getServicoDiretorio() throws RemoteException {
		// TODO Auto-generated method stub
		return "localhost:1099";
	}
	

}
