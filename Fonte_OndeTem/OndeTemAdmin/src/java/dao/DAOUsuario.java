package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author William Rodrigues
 */
public class DAOUsuario extends DAOGenerico<Usuario> {

    public List<Usuario> buscarTodos() {
        return super.buscarTodos(Usuario.class);
    }

    public Usuario buscarPorId(Integer id) {
        return buscarPorId(Usuario.class, id);
    }
    
    public Usuario buscaPorLogin(String login){
        for(Usuario u : buscarTodos()){
            if(u.getLogin().equals(login)){
                return u;
            }
        }
        return null;
    }

    public boolean remover(Integer id) {
        return super.remover(Usuario.class, id);
    }
}
