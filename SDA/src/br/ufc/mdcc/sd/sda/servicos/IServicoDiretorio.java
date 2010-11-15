package br.ufc.mdcc.sd.sda.servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.InexistenteException;
import br.ufc.mdcc.sd.sda.exceptions.PermissaoException;

public interface IServicoDiretorio extends Remote{
	
	public Ufid Lookup(Ufid ufidDir,String nome,ModoAcesso modoacesso,int userId)throws RemoteException, InexistenteException, PermissaoException;
	
	public void AddName(Ufid ufidDir,String nome,Ufid ufid,int userId)throws RemoteException, InexistenteException, PermissaoException;
	
	public void UnName(Ufid ufid,String nome)throws RemoteException, InexistenteException, PermissaoException;
	
	public void ReName(Ufid ufid,String nomeVelho,String nomeNovo)throws RemoteException, InexistenteException, PermissaoException;
	
	public String[] GetNames (Ufid ufid, String expressao)throws RemoteException, InexistenteException, PermissaoException;

	String getChavePublica() throws RemoteException;
}
