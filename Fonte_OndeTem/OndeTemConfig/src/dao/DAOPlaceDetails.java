package dao;

import java.util.List;
import modelo.PlaceDetails;

/**
 *
 * @author William Rodrigues
 */
public class DAOPlaceDetails extends DAOGenerico<PlaceDetails> {

    public List<PlaceDetails> buscarTodos() {
        return super.buscarTodos(PlaceDetails.class);
    }

    public PlaceDetails buscarPorId(String id) {
        for(PlaceDetails placeDetails: buscarTodos()){
            if(placeDetails.getPlace_id().equals(id)){
                return placeDetails;
            }
        }
        return null;
    }

    public boolean remover(Integer id) {
        return super.remover(PlaceDetails.class, id);
    }

}
