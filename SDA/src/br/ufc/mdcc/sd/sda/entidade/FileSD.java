package br.ufc.mdcc.sd.sda.entidade;

import java.io.Serializable;


public class FileSD implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2393599192450021093L;

	private Ufid ufid;
	
	private Descritor descritor;
	
	private byte[] dados;
	
	/**
	 * @param ufid
	 */
	public FileSD(Ufid ufid) {
		super();
		this.ufid = ufid;
	}

	public Ufid getUfid() {
		return ufid;
	}


	public Descritor getDescritor() {
		return descritor;
	}

	public void setDescritor(Descritor descritor) {
		this.descritor = descritor;
	}
	
	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	
	
		
}
