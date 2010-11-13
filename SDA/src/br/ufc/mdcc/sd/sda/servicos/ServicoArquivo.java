package br.ufc.mdcc.sd.sda.servicos;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import br.ufc.mdcc.sd.sda.entidade.Arquivo;
import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.RSA;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.PosicaoIvalidaException;
import br.ufc.mdcc.sd.sda.util.FileUtil;

public class ServicoArquivo implements IServicoArquivo {

	private static URI URI_SD;
	/**
	 * 
	 */
	public ServicoArquivo() {
		super();
		
		try { URI_SD = new URI("127.0.0.1:1097"); } 
		catch (URISyntaxException e) { System.out.println("Erro de Sintaxe: Endereço errado !");}
	}

	private static final int COUNT_FILE = 0;
	private RSA chaves = new RSA();

	@Override
	public byte[] read(Ufid ufid, int offset, int size)
			throws PosicaoIvalidaException {

		FileSD arquivo = FileUtil.deserializarFile(ufid);

		size = (size <= arquivo.getDados().length) ? size
				: arquivo.getDados().length - offset;

		byte[] dados = new byte[size];

		for (int i = 0; i < dados.length; i++) {
			dados[i] = arquivo.getDados()[offset + i];
		}

		return dados;
	}

	@Override
	public void write(Ufid ufid, int offset, byte[] dados) {

		Arquivo arquivo = (Arquivo) FileUtil.deserializarFile(ufid);

		int size = arquivo.getDados().length;

		byte[] tmpDados = new byte[size + dados.length];

		for (int i = 0; i < tmpDados.length; i++) {
			if (i < size)
				tmpDados[i] = arquivo.getDados()[i];
			else
				tmpDados[i] = dados[i - size];
		}

		arquivo.setDados(tmpDados);
		FileUtil.serializarFile(arquivo);
	}

	@Override
	public Ufid create() {
		
		Ufid ufid = new Ufid();
		ufid.setData(new Date());
		ufid.setEncriptado(false);
		ufid.setEndereco(URI_SD);
		ufid.setNumArquivo(COUNT_FILE);
		
		FileSD novoArquivo = new FileSD(ufid);
		FileUtil.serializarFile(novoArquivo);
		return null;
	}

	@Override
	public void truncate(Ufid ufid, int offset) {

	}

	@Override
	public void delete(Ufid ufid) {
		
	}

	@Override
	public Descritor getAttributes(Ufid ufid) {
		Arquivo arquivo = (Arquivo) FileUtil.deserializarFile(ufid);
		return arquivo.getDescritor();
	}

	@Override
	public void setAttributes(Ufid ufid, Descritor descritor) {
		Arquivo arquivo = (Arquivo) FileUtil.deserializarFile(ufid);
		arquivo.setDescritor(descritor);
	}

	@Override
	public void getChavePublica() {
		this.chaves.getChavePublica();
	}

}
