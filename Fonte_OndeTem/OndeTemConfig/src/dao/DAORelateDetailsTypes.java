/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.RelateDetailsTypes;

/**
 *
 * @author William Rodrigues
 */
public class DAORelateDetailsTypes extends DAOGenerico<RelateDetailsTypes>{
    
    public List<RelateDetailsTypes> buscarTodos() {
        return super.buscarTodos(RelateDetailsTypes.class);
    }

    public RelateDetailsTypes buscarPorId(Integer id) {
        return buscarPorId(RelateDetailsTypes.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(RelateDetailsTypes.class, id);
    }
}
