package controle;

import dao.DAOUsuario;
import modelo.Usuario;

/**
 *
 * @author William Rodrigues
 */
public class ControleUsuario {

    DAOUsuario dao = new DAOUsuario();

    public Usuario validar(String login, String senha) {

        DAOUsuario dao = new DAOUsuario();

        for (Usuario usuario : dao.buscarTodos()) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }

        return null;
    }

    public boolean cadastrarUsuario(String login, String senha, String permissao) {

        Usuario u = dao.buscaPorLogin(login);

        if (u == null) {
            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setPermissao(permissao);
            dao.inserir(usuario);
            return true;
        }else return false;
    }
    
    public void editarUsuario(Integer id, String login, String senha, String permissao){
        
        Usuario u = dao.buscarPorId(id);
        u.setLogin(login);
        u.setSenha(senha);
        u.setPermissao(permissao);
        dao.alterar(u);
    }

}
