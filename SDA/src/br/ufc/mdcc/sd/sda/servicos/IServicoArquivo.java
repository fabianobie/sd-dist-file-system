/**
 * 
 */
package br.ufc.mdcc.sd.sda.servicos;

import java.rmi.Remote;

import br.ufc.mdcc.sd.sda.entidade.Descritor;
import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.PosicaoIvalidaException;

/**
 * @author Fabiano
 *
 */
public interface IServicoArquivo extends Remote {
	
	public byte[] read(Ufid ufid,int offset,int size) throws PosicaoIvalidaException;
	
	public void write(Ufid ufid,int offset, byte[] dados);
	
	public Ufid create();
	
	public void truncate(Ufid ufid,int offset);
	
	public void delete(Ufid ufid);
	
	public Descritor getAttributes(Ufid ufid);
	
	public void setAttributes(Ufid ufid, Descritor descritor);
	
	public void getChavePublica();
}
