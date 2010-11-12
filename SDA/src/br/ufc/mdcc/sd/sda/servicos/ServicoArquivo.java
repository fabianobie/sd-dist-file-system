package br.ufc.mdcc.sd.sda.servicos;

import java.io.File;

import javax.management.Descriptor;

import br.ufc.mdcc.sd.sda.entidade.Ufid;

public class ServicoArquivo implements IServicoArquivo {

	@Override
	public File read(Ufid ufid, int i, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(Ufid ufid, int i, File dados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ufid create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void truncate(Ufid ufid, int l) {
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
