/**
 * 
 */
package br.ufc.mdcc.sd.sda.servicos;

import java.io.File;
import java.rmi.Remote;

import javax.management.Descriptor;

import br.ufc.mdcc.sd.sda.entidade.Ufid;

/**
 * @author Fabiano
 *
 */
public interface IServicoArquivo extends Remote {
	
	public  File read(Ufid ufid,int i,int n);
	
	public void write(Ufid ufid,int i,File dados);
	
	public Ufid create();
	
	public void truncate(Ufid ufid,int l);
	
	public void delete(Ufid ufid);
	
	public Descriptor getAttributes(Ufid ufid);
	
	public void setAttributes(Ufid ufid,Descriptor descritor);

}
