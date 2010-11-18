package br.ufc.mdcc.sd.sda.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.ufc.mdcc.sd.sda.entidade.TipoArquivo;

public class AppExplorer {

	private static ModuloCliente modCliente;
	private static int userId = 1;
	
	private static String[] arquivos = {};

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Comando comando = null;
		
		ClienteSDA.iniciarCliente();
		
		modCliente = new ModuloCliente();
		modCliente.iniciarSistemaArquivo(userId);
		
		
		System.out.println("---------------------------------------------");
		System.out.println("|\tSistema de Arquivo\t|\tAlternativo\t|");
		System.out.println("---------------------------------------------");
		
		
		while (true) {
			System.out.println("Digite um comando de Sistema de Arquivo:");
			

			BufferedReader entradaDoUsuario = new BufferedReader(
					new InputStreamReader(System.in));

			comando = comandoExist(entradaDoUsuario.readLine());
			
			if(comando!=null)
				executaComando(comando);
		}
		
		
		//modCliente.lerDiretorio("pasta7", userId);
		//File file = new File("C:\\dev\\saudade2.txt");
		//modCliente.inserirArquivo(file.getName(), FileUtil.getBytesFromFile(file), userId);
		// modCliente.criarArquivo("pasta7", TipoArquivo.DIRECTORY,userId);
		// modCliente.criarArquivo("teste.pdf", TipoArquivo.FILE,userId);

	}

	private static void executaComando(Comando comando) throws IOException {
		String auxiliar = "";
		BufferedReader inUser;
		
			switch (comando) {
			case CD:
				System.out.println("Digite o nome do diretorio:");
				inUser = new BufferedReader(new InputStreamReader(System.in));
				auxiliar = inUser.readLine();
				arquivos = modCliente.lerDiretorio(auxiliar, userId);
				System.out.println(modCliente.getRoot().getName());
				break;
			case UP:
				
				break;
			case LS:
				arquivos = modCliente.lerDiretorio(".", userId);
				for (String nome : arquivos) {
					System.out.println(nome);
				}
				break;
			case DEL:
				
				break;
			case MKDIR:
				System.out.println("Digite um nome para o diretorio:");
				inUser = new BufferedReader(new InputStreamReader(System.in));
				auxiliar = inUser.readLine();
				modCliente.criarArquivo(auxiliar, TipoArquivo.DIRECTORY,userId);
				break;
			case MKFILE:
				System.out.println("Digite um nome para o arquivo:");
				inUser = new BufferedReader(new InputStreamReader(System.in));
				auxiliar = inUser.readLine();
				modCliente.criarArquivo(auxiliar, TipoArquivo.FILE,userId);
				break;
			default:
				System.out.println("nenhum comando correspondente");
				break;
			}
	}

	private static Comando comandoExist(String cmd) {
			Comando[] cmds = Comando.values();
			for (Comando comando : cmds) {
				if(comando.getValue().equals(cmd))
					return comando;
			}
			return null;
	}

	

}
