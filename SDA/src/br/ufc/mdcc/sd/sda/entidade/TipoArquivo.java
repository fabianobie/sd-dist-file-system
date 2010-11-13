package br.ufc.mdcc.sd.sda.entidade;

public enum TipoArquivo {
	FILE("f",Arquivo.class),
	DIRECTORY("d",Diretorio.class);
	
	private final String value;
	private final Class classe;
	
	private TipoArquivo(String value, Class classe){
		this.value = value;
		this.classe = classe;
	}
	public String getValue() {
		return value;
	}
	public Class getClasse() {
		return classe;
	}
	
}
