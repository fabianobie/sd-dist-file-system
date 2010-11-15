package br.ufc.mdcc.sd.sda.entidade;

public enum ModoAcesso {
	WRITE("w"),
	READ("r"),
	DELETE("d"),
	GETATT("g"),
	SETATT("s");
	
	private final String value;
	private ModoAcesso(String val) {
		this.value = val;
	}
	public String getValue(){
		return this.value;
	}
}
