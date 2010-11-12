package br.ufc.mdcc.sd.sda.entidade;

import java.io.File;

public class Arquivo {
	
	private Ufid ufid;
	
	private File dados;
	
	private Descritor descritor;

	public Ufid getUfid() {
		return ufid;
	}

	public void setUfid(Ufid ufid) {
		this.ufid = ufid;
	}

	public File getDados() {
		return dados;
	}

	public void setDados(File dados) {
		this.dados = dados;
	}

	public Descritor getDescritor() {
		return descritor;
	}

	public void setDescritor(Descritor descritor) {
		this.descritor = descritor;
	}
		
}
