package dao;

import java.util.List;
import modelo.PlaceTypes;

public class DAOPlaceTypes extends DAOGenerico<PlaceTypes> {

    public List<PlaceTypes> buscarTodos() {
        return super.buscarTodos(PlaceTypes.class);
    }

    public PlaceTypes buscarPorId(Integer id) {
        return buscarPorId(PlaceTypes.class, id);
    }
    
    public PlaceTypes buscaPorType(String type){
        for(PlaceTypes placeTypes: buscarTodos()){
            if(placeTypes.getType().equalsIgnoreCase(type)){
                return placeTypes;
            }
        }
        return null;
    }
    
    public PlaceTypes buscaPorTraducao(String traducao){
        for(PlaceTypes placeTypes: buscarTodos()){
            if(placeTypes.getTraducao().equalsIgnoreCase(traducao)){
                return placeTypes;
            }
        }
        return null;
    }

    public boolean remover(Integer id) {
        return super.remover(PlaceTypes.class, id);
    }
}
