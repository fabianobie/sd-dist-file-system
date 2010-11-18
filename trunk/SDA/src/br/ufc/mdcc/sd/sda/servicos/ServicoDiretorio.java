package br.ufc.mdcc.sd.sda.servicos;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.Diretorio;
import br.ufc.mdcc.sd.sda.entidade.FileSD;
import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.Permissao;
import br.ufc.mdcc.sd.sda.entidade.RSA;
import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.InexistenteException;
import br.ufc.mdcc.sd.sda.exceptions.PermissaoException;
import br.ufc.mdcc.sd.sda.servidor.ArquivoServer;
import br.ufc.mdcc.sd.sda.servidor.DiretorioServer;
import br.ufc.mdcc.sd.sda.util.FileUtil;

public class ServicoDiretorio extends UnicastRemoteObject implements
		IServicoDiretorio {
	
	private RSA chaves = new RSA();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8396159772556173325L;

	public ServicoDiretorio() throws RemoteException {
		super();

	}

	@Override
	public Ufid Lookup(Ufid ufidDir, String nome, ModoAcesso modoacesso,int userId)
		throws RemoteException, PermissaoException, InexistenteException 
		{
		Ufid ufidResult = null;
		boolean temPermissao = false;
		
		HashMap<String, Ufid> diretorio = getDiretorio(ufidDir);
		
		ufidResult = diretorio.get(nome);
		//Descritor descritor = getAttribute(ufidResult);
		
		if(ufidResult!=null){
			
			/*String permissao = ufidResult.getCodVerificacao();
			
			ufidResult.setCodVerificacao(FileUtil.criptografa(
													DiretorioServer.
													getServicoArquivo()
													.getChavePublica(),
													permissao)
											);
			*/
			//temPermissao = FileUtil.hasPermissao(permissao, modoacesso, userId);

			//if (!temPermissao) {
		//		System.out.println("Usuario " + userId + " não tem permissão de "+ modoacesso);
			//	throw new PermissaoException();
			//}
		}else{
			
			System.out.println("Arquivo inexistente ");
			throw new InexistenteException();
		}
		
		return ufidResult;
	}

	@Override
	public void AddName(Ufid ufidDir, String nome, Ufid ufid, int userId)
			throws RemoteException, InexistenteException, PermissaoException  {
		
		FileSD arquivo = (FileSD) FileUtil.deserializarFile(ufid);
		HashMap<String, Ufid> diretorio = getDiretorio(ufidDir);
		diretorio.put(nome, arquivo.getUfid());
		
		try {
			
			DiretorioServer.getServicoArquivo().write(ufidDir,0,FileUtil.serializarObjeto(diretorio));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void UnName(Ufid ufid, String nome) throws RemoteException  , InexistenteException, PermissaoException  {
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
			throws RemoteException, InexistenteException, PermissaoException  {
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
			throws RemoteException, InexistenteException {
		HashMap<String, Ufid> diretorio = getDiretorio(ufid);
		
		return FileUtil.buscaArquivos(diretorio, expressao);
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
	 * @throws InexistenteException 
	 */
	
	private HashMap<String, Ufid> getDiretorio(Ufid ufidDir)
			throws RemoteException, InexistenteException {
		IServicoArquivo servFile = DiretorioServer.getServicoArquivo();
		if (servFile != null) {

			byte[] dadosTmp = null;
			byte[] dados = null;
			int i = 0;
			/*do {
				dadosTmp = servFile.read(ufidDir, i, 10);
				dados = FileUtil.addbytes(dados, dadosTmp);
				i += 10;
			} while (dadosTmp != null);*/
			dados = servFile.read(ufidDir, i, 10);

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
			throws RemoteException, InexistenteException {
		
		IServicoArquivo servFile = DiretorioServer.getServicoArquivo();
		return servFile.getAttributes(ufid);
	}
	
	@Override
	public String getChavePublica() throws RemoteException {
		return this.chaves.getChavePublica();
	}
}
