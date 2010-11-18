package br.ufc.mdcc.sd.sda.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.Permissao;
import br.ufc.mdcc.sd.sda.entidade.RSA;
import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
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
	private Ufid root;

	/**
	 * @throws RemoteException
	 * 
	 */
	public ServicoArquivo() throws RemoteException {
		super();
		try {
			URI_SD = new URI("rmi://localhost:1098");
		} catch (URISyntaxException e) {
			System.out.println("Erro de Sintaxe: Endereço errado !");
		}
	}

	@Override
	public byte[] read(Ufid ufid, int offset, int size) throws RemoteException, InexistenteException {
		
		FileSD arquivo = FileUtil.deserializarFile(ufid);
		byte[] dadoTotal = arquivo.getDados();
		
		/*
		int to = (size+offset <= arquivo.getDados().length) ? size+offset-1
				: arquivo.getDados().length-1;
		if(offset<to){
			byte[] dados = Arrays.copyOfRange(dadoTotal, offset, to);
			return dados;
		}else{
			return null;
		}*/
		
		return dadoTotal;
		
	}

	@Override
	public void write(Ufid ufid, int offset, byte[] dados)
			throws RemoteException, InexistenteException {

		FileSD arquivo =  FileUtil.deserializarFile(ufid);
		
		/*
		byte[] tmpDados = new byte[offset + dados.length];

		for (int i = 0; i < tmpDados.length; i++) {
			if (i < offset)
				tmpDados[i] = arquivo.getDados()[i];
			else
				tmpDados[i] = dados[i - offset];
		}
		*/
		arquivo.setDados(dados);
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
		File arquivo = new File(FileUtil.DIR_ROOT + File.separator + ufid.getName());
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
		System.out.println(descritor.getListaAcesso());
		arquivo.getUfid().setCodVerificacao(descritor.getListaAcesso().toString());
		arquivo.getUfid().setPermissao(descritor.getListaAcesso());
		arquivo.setDescritor(descritor);
		
		FileUtil.serializarFile(arquivo);
	}

	@Override
	public String getChavePublica() throws RemoteException {
		return this.chaves.getChavePublica();
	}
	
	@Override
	public  Ufid getRoot() {
		return root;
	}
	
	@Override
	public void setRoot(Ufid ufid,int userId) throws RemoteException {
		
			try {
				if(ufid == null)
					this.root = criarRaiz(userId);
				else
					this.root = ufid;
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	private static Ufid criarRaiz(int userId) throws URISyntaxException, IOException{
		Ufid idRaiz = new Ufid(new URI("rmi://localhost:1098"), new Date(0), 0);
		Permissao permissao = new Permissao(userId, true, true, true, true, true);
		idRaiz.setPermissao(permissao);
		FileSD fileRaiz = new FileSD(idRaiz);
		try {
			
			fileRaiz = FileUtil.deserializarFile(idRaiz);
			
		} catch (InexistenteException e) {
			
			Descritor descritor = new Descritor();
			descritor.setCriacao(new Date());
			descritor.setListaAcesso(permissao);
			descritor.setModificacao(new Date());
			descritor.setAlteracao(new Date());
			descritor.setProprietario(userId);
			descritor.setTamanho(0.0);
			descritor.setTipo(TipoArquivo.DIRECTORY);
			fileRaiz.getUfid().setPermissao(permissao);
			fileRaiz.setDescritor(descritor);
			HashMap<String,Ufid> diretorio = new HashMap<String, Ufid>();
			
			fileRaiz.getUfid().setCodVerificacao(permissao.toString());
			fileRaiz.getUfid().setPermissao(permissao);
			
			diretorio.put(".", fileRaiz.getUfid());
			fileRaiz.setDados(FileUtil.serializarObjeto(diretorio));
			FileUtil.serializarFile(fileRaiz);
		}
			
		
		return idRaiz;
	}
	
	
}
