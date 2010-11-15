package br.ufc.mdcc.sd.sda.servicos;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.RSA;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.InexistenteException;
import br.ufc.mdcc.sd.sda.util.FileUtil;

public class ServicoArquivo extends UnicastRemoteObject implements
		IServicoArquivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8598798164416572562L;

	private static URI URI_SD;
	private static int COUNT_FILE = 1;
	private RSA chaves = new RSA();
	private static Ufid root;

	/**
	 * @throws RemoteException
	 * 
	 */
	public ServicoArquivo() throws RemoteException {
		super();
		try {
			URI_SD = new URI("localhost:1098");
		} catch (URISyntaxException e) {
			System.out.println("Erro de Sintaxe: Endereço errado !");
		}
	}

	@Override
	public byte[] read(Ufid ufid, int offset, int size) throws RemoteException, InexistenteException {
		
		
		FileSD arquivo = FileUtil.deserializarFile(ufid);

		size = (size <= arquivo.getDados().length) ? size
				: arquivo.getDados().length - offset;
		byte[] dados = null;
		
		if (size > 0) {
			dados = new byte[size];

			for (int i = 0; i < dados.length; i++) {
				dados[i] = arquivo.getDados()[offset + i];
			}
		}

		return dados;
	}

	@Override
	public void write(Ufid ufid, int offset, byte[] dados)
			throws RemoteException, InexistenteException {

		FileSD arquivo =  FileUtil.deserializarFile(ufid);
		
		byte[] tmpDados = new byte[offset + dados.length];

		for (int i = 0; i < tmpDados.length; i++) {
			if (i < offset)
				tmpDados[i] = arquivo.getDados()[i];
			else
				tmpDados[i] = dados[i - offset];
		}

		arquivo.setDados(tmpDados);
		FileUtil.serializarFile(arquivo);
	}

	@Override
	public Ufid create() throws RemoteException {

		Ufid ufid = new Ufid(URI_SD, new Date(), COUNT_FILE++);
		FileSD novoArquivo = new FileSD(ufid);
		FileUtil.serializarFile(novoArquivo);
		
		return ufid;
	}

	@Override
	public void truncate(Ufid ufid, int offset) throws RemoteException, InexistenteException {
		FileSD arquivo = (FileSD) FileUtil.deserializarFile(ufid);

		byte[] tmpDados = new byte[offset];

		for (int i = 0; i < offset; i++) {
			tmpDados[i] = arquivo.getDados()[i];
		}

		arquivo.setDados(tmpDados);
		FileUtil.serializarFile(arquivo);
	}

	@Override
	public void delete(Ufid ufid) throws RemoteException {
		File arquivo = new File(FileUtil.DIR_ROOT + File.separator + ufid);
		if (arquivo.exists())
			arquivo.delete();
	}

	@Override
	public Descritor getAttributes(Ufid ufid) throws RemoteException, InexistenteException {
		FileSD arquivo = (FileSD) FileUtil.deserializarFile(ufid);
		return arquivo.getDescritor();
	}

	@Override
	public void setAttributes(Ufid ufid, Descritor descritor)
			throws RemoteException, InexistenteException {
		FileSD arquivo = (FileSD) FileUtil.deserializarFile(ufid);
		
		arquivo.getUfid().setCodVerificacao(descritor.getListaAcesso().toString());
		arquivo.getUfid().setPermissao(descritor.getListaAcesso());
		arquivo.setDescritor(descritor);
		
		FileUtil.serializarFile(arquivo);
	}

	@Override
	public String getChavePublica() throws RemoteException {
		return this.chaves.getChavePublica();
	}

	public static Ufid getRoot() {
		return root;
	}

	public static void setRoot(Ufid root) {
		ServicoArquivo.root = root;
	}
	
	
}
