package br.ufc.mdcc.sd.sda.entidade;

import java.math.BigInteger;

public class Permissao {
	/**
	 * Id do Usuario ou grupo que possui permissoes
	 */
	private int idEntidade;
	
	/**
	 * Permissões
	 */
	private boolean read;
	private boolean write;
	private boolean delete;
	private boolean getAtt;
	private boolean setAtt;
	
	/**
	 * @param idEntidade
	 * @param read
	 * @param write
	 * @param delete
	 * @param getAtt
	 * @param setAtt
	 */
	public Permissao(int idEntidade, boolean read, boolean write,
			boolean delete, boolean getAtt, boolean setAtt) {
		super();
		
		this.idEntidade = idEntidade;
		this.read = read;
		this.write = write;
		this.delete = delete;
		this.getAtt = getAtt;
		this.setAtt = setAtt;
	}

	public int getIdEntidade() {
		return idEntidade;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isGetAtt() {
		return getAtt;
	}

	public void setGetAtt(boolean getAtt) {
		this.getAtt = getAtt;
	}

	public boolean isSetAtt() {
		return setAtt;
	}

	public void setSetAtt(boolean setAtt) {
		this.setAtt = setAtt;
	}
	
	@Override
	public String toString(){
		String permissao="";
		
		if(read) permissao+="r"; else permissao+="-";
		if(write) permissao+="w"; else permissao+="-";
		if(delete) permissao+="d"; else permissao+="-";
		if(getAtt) permissao+="g"; else permissao+="-";
		if(setAtt) permissao+="s"; else permissao+="-";
		
		return permissao;
	}

}