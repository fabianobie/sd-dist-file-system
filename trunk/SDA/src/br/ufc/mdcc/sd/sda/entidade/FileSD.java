package br.ufc.mdcc.sd.sda.entidade;


public abstract class FileSD {
	
	private Ufid ufid;
	
	private Descritor descritor;

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
		
}
