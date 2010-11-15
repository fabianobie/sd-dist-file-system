package br.ufc.mdcc.sd.sda.servicos;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.Diretorio;
import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.servidor.DiretorioServer;
import br.ufc.mdcc.sd.sda.util.FileUtil;

public class ServicoDiretorio extends UnicastRemoteObject implements
		IServicoDiretorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8396159772556173325L;

	public ServicoDiretorio() throws RemoteException {
		super();

	}

	@Override
	public Ufid Lookup(Ufid ufidDir, String nome, ModoAcesso modoacesso,
			int userId) throws RemoteException {
		Ufid ufidResult = null;
		boolean temPermissao = false;
		HashMap<String, Ufid> diretorio = getDiretorio(ufidDir);

		ufidResult = diretorio.get(nome);
		Descritor descritor = getAttribute(ufidResult);
		temPermissao = FileUtil.hasPermissao(descritor, modoacesso, userId);

		if (temPermissao) {
			return ufidResult;
		} else {
			System.out.println("Usuario " + userId + " não tem permissão de "
					+ modoacesso);
			return null;
		}
	}

	@Override
	public void AddName(Ufid ufidDir, String nome, Ufid ufid, int userId)
			throws RemoteException {
		// TODO Auto-generated method stub
		ModoAcesso modoacesso = ModoAcesso.WRITE;
		Ufid uf = Lookup(ufidDir, nome, modoacesso, userId);
		if (uf == null) {
			Diretorio dir = new Diretorio(ufidDir); // deserializar da ufidDir
			dir.getMapeamento().put(nome, ufid);
		} else
			;// chamar excessão

	}

	@Override
	public void UnName(Ufid ufid, String nome) throws RemoteException {
		ModoAcesso modoacesso = ModoAcesso.DELETE;
		int userId = 0;
		Ufid uf = Lookup(ufid, nome, modoacesso, userId);
		if (uf == null) {
			Diretorio dir = new Diretorio(ufid); // deserializar da ufidDir
			if (dir.getMapeamento().isEmpty())
				dir.getMapeamento().remove(ufid);
		} else
			;// chamar excessão

	}

	@Override
	public void ReName(Ufid ufid, String nomeVelho, String nomeNovo)
			throws RemoteException {
		ModoAcesso modoacesso = ModoAcesso.WRITE;
		int userId = 0;
		Ufid uf = Lookup(ufid, nomeVelho, modoacesso, userId);
		if (uf == null) {
			UnName(ufid, nomeVelho);
			AddName(ufid, nomeNovo, uf, userId);
		} else
			;// chamar excessão
	}

	@Override
	public String[] GetNames(Ufid ufid, String expressao)
			throws RemoteException {
		HashMap<String, Ufid> diretorio = getDiretorio(ufid);
		
		FileUtil.buscaArquivos(diretorio, expressao);
		
		
		
		return null;
	}
	
//////////////////////////////////////////////////////////////
///	
///     	Funções Auxiliares
///	
/////////////////////////////////////////////////////////////	
	
	/**
	 * 
	 * 
	 * @param ufidDir
	 * @return
	 * @throws RemoteException
	 */
	
	private HashMap<String, Ufid> getDiretorio(Ufid ufidDir)
			throws RemoteException {
		IServicoArquivo servFile = DiretorioServer.getServicoArquivo();
		if (servFile != null) {

			byte[] dadosTmp = null;
			byte[] dados = null;
			int i = 0;
			do {
				dadosTmp = servFile.read(ufidDir, i, 10);
				dados = FileUtil.addbytes(dados, dadosTmp);
				i += 10;
			} while (dadosTmp != null);

			HashMap<String, Ufid> diretorio = null;

			try {
				diretorio = (HashMap<String, Ufid>) FileUtil.deserializarObjeto(dados);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			return diretorio;
		} else {
			System.out.println("Serviço de Arquivo Desligado");
			return null;
		}

	}

	private Descritor getAttribute(Ufid ufid)
			throws RemoteException {
		
		IServicoArquivo servFile = DiretorioServer.getServicoArquivo();
		return servFile.getAttributes(ufid);
	}
}
