/**
 * 
 */
package br.ufc.mdcc.sd.sda.servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.PosicaoIvalidaException;

/**
 * @author Fabiano
 *
 */
public interface IServicoArquivo extends Remote {
	
	public byte[] read(Ufid ufid,int offset,int size) throws RemoteException ;
	
	public void write(Ufid ufid,int offset, byte[] dados) throws RemoteException;
	
	public Ufid create() throws RemoteException;
	
	public void truncate(Ufid ufid,int offset) throws RemoteException;
	
	public void delete(Ufid ufid) throws RemoteException;
	
	public Descritor getAttributes(Ufid ufid) throws RemoteException;
	
	public void setAttributes(Ufid ufid, Descritor descritor) throws RemoteException;
	
	public void getChavePublica() throws RemoteException;
}
