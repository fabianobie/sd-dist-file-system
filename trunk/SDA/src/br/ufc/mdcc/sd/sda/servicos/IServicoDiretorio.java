package br.ufc.mdcc.sd.sda.servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

import br.ufc.mdcc.sd.sda.entidade.ModoAcesso;
import br.ufc.mdcc.sd.sda.entidade.Ufid;

public interface IServicoDiretorio extends Remote{
	
	public Ufid Lookup(Ufid ufidDir,String nome,ModoAcesso modoacesso,int userId)throws RemoteException;
	
	public void AddName(Ufid ufidDir,String nome,Ufid ufid,int userId)throws RemoteException;
	
	public void UnName(Ufid ufid,String nome)throws RemoteException;
	
	public void ReName(Ufid ufid,String nomeVelho,String nomeNovo)throws RemoteException;
	
	public String[] GetNames (Ufid ufid, String expressao)throws RemoteException;
}
