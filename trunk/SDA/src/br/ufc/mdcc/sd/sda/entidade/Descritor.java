package br.ufc.mdcc.sd.sda.entidade;

import java.io.Serializable;
import java.util.Date;

public class Descritor implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4679656268851738030L;
	private double tamanho;
	private Date criacao;
	private Date modificacao;
	private Date alteracao;
	private int contador;
	private int proprietario;
	private TipoArquivo tipo;
	private Permissao listaAcesso;
	
	public double getTamanho() {
		return tamanho;
	}
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}
	public Date getCriacao() {
		return criacao;
	}
	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}
	public Date getModificacao() {
		return modificacao;
	}
	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}
	public Date getAlteracao() {
		return alteracao;
	}
	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public int getProprietario() {
		return proprietario;
	}
	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}
	public TipoArquivo getTipo() {
		return tipo;
	}
	public void setTipo(TipoArquivo tipo) {
		this.tipo = tipo;
	}
	public Permissao getListaAcesso() {
		return listaAcesso;
	}
	public void setListaAcesso(Permissao listaAcesso) {
		this.listaAcesso = listaAcesso;
	}

	public Descritor() {
		
	}


}
