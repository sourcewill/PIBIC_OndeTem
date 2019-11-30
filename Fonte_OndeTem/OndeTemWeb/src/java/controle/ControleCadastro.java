package controle;

import dao.DAOPlaceDetailsAux;
import dao.DAOPlaceTypes;
import java.util.List;
import modelo.PlaceDetailsAux;
import modelo.PlaceTypes;

/**
 *
 * @author William Rodrigues
 */
public class ControleCadastro {
    
    /**
     *
     * @param name
     * @param formatted_address
     * @param formatted_phone_number
     * @param website
     * @param typeId
     * @param latitude
     * @param longitude
     * 
     * Recebe os campos digitados na página de cadastro
     * forma o objeto local e o persiste no banco de dados
     */
    public void cadastrarLocal(String name, String formatted_address, String formatted_phone_number, String website, Integer typeId, String latitude, String longitude) {
        
        PlaceDetailsAux place = new PlaceDetailsAux();
        DAOPlaceDetailsAux daoDetails = new DAOPlaceDetailsAux();
        PlaceTypes placeType;
        DAOPlaceTypes daoType = new DAOPlaceTypes();
        
        place.setName(name);
        place.setFormatted_address(formatted_address);
        place.setFormatted_phone_number(formatted_phone_number);
        place.setWebsite(website);
        place.setLatitude(latitude);
        place.setLongitude(longitude);
        
        placeType = daoType.buscarPorId(typeId);
        place.setTypeId(typeId);
        place.setType(placeType.getType());
        place.setCategory(placeType.getCategory());
        place.setTraducao(placeType.getTraducao());
        
        daoDetails.inserir(place);
    }
    
    /**
     *
     * @return lista de tipos cadastrados no banco de dados
     */
    public List<PlaceTypes> formaListaTipos(){        
        
        DAOPlaceTypes dao = new DAOPlaceTypes();        
        return dao.buscarTodos();
    }

    
}
