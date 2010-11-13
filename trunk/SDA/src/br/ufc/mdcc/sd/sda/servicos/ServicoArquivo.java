package br.ufc.mdcc.sd.sda.servicos;

import java.io.File;

import javax.management.Descriptor;

import br.ufc.mdcc.sd.sda.entidade.Ufid;
import br.ufc.mdcc.sd.sda.exceptions.PosicaoIvalidaException;

public class ServicoArquivo implements IServicoArquivo {

	@Override
	public byte[] read(Ufid ufid, int offset, int size)
			throws PosicaoIvalidaException {
		File arquivoLocal =  new File(ufid.toString());
		
		
		return null;
	}

	@Override
	public void write(Ufid ufid, int offset, byte[] dados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ufid create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void truncate(Ufid ufid, int offset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ufid ufid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Descriptor getAttributes(Ufid ufid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttributes(Ufid ufid, Descriptor descritor) {
		// TODO Auto-generated method stub
		
	}
	
}
