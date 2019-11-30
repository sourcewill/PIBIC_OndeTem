/*
 * Sistema para pré-configuração do banco de dados das aplicações OndeTem:
 * CrawlerJava, OndeTemWeb, OndeTemAdmin.
 *
 */
package principal;

import conexaoBanco.ConexaoBanco;
import controle.Controle;
import dao.DAOUsuario;
import modelo.Usuario;

/**
 *
 * @author William Rodrigues
 */
public class Principal {

    public static void main(String[] args) {
        
        Controle controle = new Controle();      
        
        try {

            ConexaoBanco.conectar();

            //Insere um usuário admin padrão
            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario usuarioPadrao = new Usuario();

            usuarioPadrao.setLogin("admin");
            usuarioPadrao.setSenha("admin");
            usuarioPadrao.setPermissao("Administrador");

            daoUsuario.inserir(usuarioPadrao);
            
            //Preenche as tabelas de tipos e categorias usando os arquivos de texto
            controle.preencheTabelaCategorias("type_category_google.txt");
            controle.preencheTabelaCategorias("type_category_kekanto.txt");

            ConexaoBanco.desconectar();
            
            System.out.println("\nBanco de dados iniciado e configurado com sucesso!");
            System.out.println("Copie o aquivo 'config-banco.properties'"
                    + "\ne o substitua em todos os diretórios dos demais sistemas"
                    + "\npara concluir a configuração.");
            System.out.println("\nPara mais informações consulte o arquivo de documentação deste sistema.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

}
