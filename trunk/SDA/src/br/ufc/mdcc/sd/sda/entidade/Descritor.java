package br.ufc.mdcc.sd.sda.entidade;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Descritor extends File {
	
	private double tamanho;
	private Date criacao;
	private Date modificacao;
	private Date alteracao;
	private int contador;
	private int proprietario;
	private TipoArquivo tipo;
	private ArrayList<Permissao> listaAcesso;
	
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
	public ArrayList<Permissao> getListaAcesso() {
		return listaAcesso;
	}
	public void setListaAcesso(ArrayList<Permissao> listaAcesso) {
		this.listaAcesso = listaAcesso;
	}

	
	public Descritor(File parent, String child) {
		super(parent, child);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
