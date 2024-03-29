package br.ufc.mdcc.sd.sda.entidade;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

import br.ufc.mdcc.sd.sda.util.FileUtil;

public class Ufid implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4735297245960262863L;
	private URI endereco;
	private Date data = new Date();
	private int numArquivo;
	private String codVerificacao;
	private Permissao permissao;
	private boolean encriptado;
	
	
	/**
	 * @param endereco
	 * @param data
	 * @param numArquivo
	 * @param permissao
	 * @param encriptado
	 */
	public Ufid(URI endereco, Date data, int numArquivo) {
		super();
		this.endereco = endereco;
		this.data = data;
		this.numArquivo = numArquivo;
		//this.codVerificacao = Math.round(Math.random()*100)+"_"+permissao;
	}

	
	public URI getEndereco() {
		return endereco;
	}
	public String getData() {
		 long l = this.data.getTime();
		 String str = Long.toString(l);
		return str;
	}
	public int getNumArquivo() {
		return numArquivo;
	}
	public void setEncriptado(boolean encriptado) {
		this.encriptado = encriptado;
	}
	
	public boolean isEncriptado() {
		return encriptado;
	}
	public String getCodVerificacao() {
		return codVerificacao;
	}
	public void setCodVerificacao(String codVerificacao) {
		this.codVerificacao = codVerificacao;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setEndereco(URI endereco) {
		this.endereco = endereco;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setNumArquivo(int numArquivo) {
		this.numArquivo = numArquivo;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString(){
		return endereco.getHost()+"_"+endereco.getPort()+"_"+data.getTime()+"_"+codVerificacao+"_"+permissao;
	}
	
	public String getName() {
		return this.getNumArquivo()+"_"+this.getEndereco().getHost()+"_"+this.getEndereco().getPort()+"_"+this.getData();
	}
	
}
