package br.ufc.mdcc.sd.sda.entidade;


public class FileSD {
	
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
