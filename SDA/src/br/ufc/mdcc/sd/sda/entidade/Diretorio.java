package br.ufc.mdcc.sd.sda.entidade;

import java.util.HashMap;

public class Diretorio extends FileSD{
	
	private HashMap<String, Ufid> mapeamento;
	
	/**
	 * 
	 */
	public Diretorio(Ufid ufid) {
		super(ufid);
		mapeamento = new HashMap<String, Ufid>();
		getDescritor().setTipo(TipoArquivo.DIRECTORY);
	}
	
	public HashMap<String, Ufid> getMapeamento() {
		return mapeamento;
	}
	public void setMapeamento(HashMap<String, Ufid> mapeamento) {
		this.mapeamento = mapeamento;
	}

}
