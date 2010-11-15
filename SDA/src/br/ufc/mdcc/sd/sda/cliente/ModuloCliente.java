package br.ufc.mdcc.sd.sda.cliente;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.InexistenteException;
import br.ufc.mdcc.sd.sda.exceptions.PermissaoException;
import br.ufc.mdcc.sd.sda.servicos.IServicoArquivo;
import br.ufc.mdcc.sd.sda.servicos.IServicoDiretorio;
import br.ufc.mdcc.sd.sda.util.FileUtil;

public class ModuloCliente {

	private static Ufid root;
	private static int userId;

	public String[] listarArquivos(String dir) {

		return null;
	}

	public boolean criarArquivo(String nome, TipoArquivo tipo)
			throws RemoteException {
		IServicoDiretorio servDiretorio = ClienteSDA.getServicoDiretorio();
		IServicoArquivo servArquivo = ClienteSDA.getServicoArquivo();

		Ufid ufid = null;

		try {

			ufid = servDiretorio.Lookup(root, nome, ModoAcesso.WRITE, userId);
			System.out.println("Diretorio já existente com esse nome");
			return false;

		} catch (InexistenteException e) {

			ufid = servArquivo.create();

			try {

				servDiretorio.AddName(root, nome, ufid, userId);

				Descritor descritor = new Descritor();
				descritor.setCriacao(new Date());
				descritor.setListaAcesso(root.getPermissao());
				descritor.setModificacao(new Date());
				descritor.setAlteracao(new Date());
				descritor.setProprietario(userId);
				descritor.setTamanho(0.0);
				descritor.setTipo(tipo);

				servArquivo.setAttributes(ufid, descritor);

				if (tipo.equals(TipoArquivo.DIRECTORY)) {
					
					HashMap<String, Ufid> diretorio = new HashMap<String, Ufid>();
					servArquivo.write(ufid, 0,FileUtil.serializarObjeto(diretorio));
					
				} else if (tipo.equals(TipoArquivo.FILE)) {
					
					StringBuffer arquivo = new StringBuffer("Arquivo Vazio");
					servArquivo.write(ufid, 0,FileUtil.serializarObjeto(arquivo));
					
				}

			} catch (InexistenteException e1) {
				System.out.println("Pasta recém criada raiz não existe");
			} catch (PermissaoException e2) {
				System.out.println("Permissão negada para alterar diretorio");
			} catch (IOException e3) {
				e.printStackTrace();
			}

			return true;

		} catch (PermissaoException e) {

			System.out.println("Permissão negada ao usuário " + userId);
			return false;

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// String[] nome = nomeArquivo.split("/");

		return false;
	}

}
