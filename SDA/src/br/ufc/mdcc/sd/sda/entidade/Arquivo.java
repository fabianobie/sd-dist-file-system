package br.ufc.mdcc.sd.sda.entidade;

public class Arquivo extends FileSD{
	
	private Byte[] dados;
	
	public Arquivo(Ufid ufid) {
		super(ufid);
	}

	public Byte[] getDados() {
		return dados;
	}

	public void setDados(Byte[] dados) {
		this.dados = dados;
	}
	
	
}
