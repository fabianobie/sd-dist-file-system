package br.ufc.mdcc.sd.sda.entidade;

import java.util.HashMap;

public class Diretorio extends Arquivo{
	
	private HashMap<String, Ufid> mapeamento;
	
	/**
	 * 
	 */
	public Diretorio() {
		
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
