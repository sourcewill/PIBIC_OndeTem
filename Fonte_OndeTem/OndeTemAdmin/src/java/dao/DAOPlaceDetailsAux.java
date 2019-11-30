package dao;

import java.util.List;
import modelo.PlaceDetailsAux;

/**
 *
 * @author William Rodrigues
 */
public class DAOPlaceDetailsAux extends DAOGenerico<PlaceDetailsAux> {

    public List<PlaceDetailsAux> buscarTodos() {
        return super.buscarTodos(PlaceDetailsAux.class);
    }

    public PlaceDetailsAux buscarPorId(Integer id) {
        for(PlaceDetailsAux placeDetails: buscarTodos()){
            if(placeDetails.getPlace_id().equals(id)){
                return placeDetails;
            }
        }
        return null;
    }

    public boolean remover(Integer id) {
        return super.remover(PlaceDetailsAux.class, id);
    }

}
