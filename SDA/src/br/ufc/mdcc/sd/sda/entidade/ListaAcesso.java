package br.ufc.mdcc.sd.sda.entidade;

public class ListaAcesso {
	private int usuario;
	private int grupo;
	private int outros;

	public static final String[] permissao = { "---", "--x", "-w-", "-wx",
												"r--", "r-x", "rw-", "rwx" };

	/**
	 * @param usuario
	 * @param grupo
	 * @param outros
	 */
	
	public ListaAcesso() {
		super();
		//Configuração Default
		setUsuario(7);
		setGrupo(6);
		setOutros(4);

	}
	public ListaAcesso(int usuario, int grupo, int outros) {
		super();
		setUsuario(usuario);
		setGrupo(grupo);
		setOutros(outros);

	}

	public void setUsuario(int usuario) {
		if (usuario >= 0 && usuario <= 7)
			this.usuario = usuario;
	}

	public void setGrupo(int grupo) {
		if (grupo >= 0 && grupo <= 7)
			this.grupo = grupo;
	}

	public void setOutros(int outros) {
		if (outros >= 0 && outros <= 7)
			this.outros = outros;
	}

	public void setSUsuario(String usuario) {

		this.usuario = getPermissao(usuario);
	}

	public void setSGrupo(String grupo) {

		this.grupo = getPermissao(grupo);
	}

	public void setSOutros(String outros) {

		this.outros = getPermissao(outros);
	}

	private int getPermissao(String code) {
		if (code.length() == 3)
			for (int i = 0; i < permissao.length; i++) {
				if (permissao[i].equals(code))
					return i;
			}
		else
			System.out.println("Código Inválido");
		return 4;
	}

	public int getUsuario() {
		return usuario;
	}

	public int getGrupo() {
		return grupo;
	}

	public int getOutros() {
		return outros;
	}

	public String getSOutros() {
		return permissao[outros];
	}

	public String getSUsuario() {
		return permissao[usuario];
	}

	public String getSGrupo() {
		return permissao[grupo];
	}

	@Override
	public String toString() {
		return permissao[usuario] + permissao[grupo] + permissao[outros];
	}

}