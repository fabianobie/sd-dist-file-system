package br.ufc.mdcc.sd.sda.cliente;


public enum Comando {
	CD("cd"),
	MKDIR("mkdir"),
	MKFILE("mkfile"),
	DEL("del"),
	LS("ls"),
	UP("up");
	
	private final String value;
	
	private Comando(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
