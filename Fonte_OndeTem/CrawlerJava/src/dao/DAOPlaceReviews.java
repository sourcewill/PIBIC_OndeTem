package dao;

import java.util.List;
import modelo.PlaceReviews;

/**
 *
 * @author William Rodrigues
 */
public class DAOPlaceReviews extends DAOGenerico<PlaceReviews> {

    public List<PlaceReviews> buscarTodos() {
        return super.buscarTodos(PlaceReviews.class);
    }

    public PlaceReviews buscarPorId(Integer id) {
        return buscarPorId(PlaceReviews.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(PlaceReviews.class, id);
    }
}
