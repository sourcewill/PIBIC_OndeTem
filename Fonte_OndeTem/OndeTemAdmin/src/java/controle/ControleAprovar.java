package controle;

import dao.DAOPlaceDetails;
import dao.DAOPlaceDetailsAux;
import dao.DAOPlaceTypes;
import dao.DAORelateDetailsTypes;
import java.util.List;
import modelo.PlaceDetails;
import modelo.PlaceDetailsAux;
import modelo.PlaceTypes;
import modelo.RelateDetailsTypes;

/**
 *
 * @author William Rodrigues
 */
public class ControleAprovar {
    
    /**
     * Recebe um objeto PlaceDetailsAux, cria a partir de suas informações um
     * novo objeto PlaceDetails, inserindo o novo objeto no banco de dados e 
     * removendo o antigo objeto PlaceDetailsAux do banco de dados.
     *
     * @param placeAux objeto PlaceDetailsAux
     */
    public void aprovar(PlaceDetailsAux placeAux){
        
        DAOPlaceDetails daoPlaceDetails = new DAOPlaceDetails();
        DAOPlaceDetailsAux daoPlaceDetailsAux = new DAOPlaceDetailsAux();
        DAOPlaceTypes daoPlaceTypes = new DAOPlaceTypes();
        DAORelateDetailsTypes daoRelateDetailsTypes = new DAORelateDetailsTypes();
        
        //Cria um novo PlaceDetails
        PlaceDetails place = new PlaceDetails();
        
        //Preenche as informações do PlaceDetails com as informações do PlaceDetailsAux
        place.setPlace_id(placeAux.getPlace_id().toString());
        place.setName(placeAux.getName());
        place.setFormatted_address(placeAux.getFormatted_address());
        place.setFormatted_phone_number(placeAux.getFormatted_phone_number());
        place.setLatitude(placeAux.getLatitude());
        place.setLongitude(placeAux.getLongitude());
        place.setWebsite(placeAux.getWebsite());
        //Insere o local no banco de dados
        daoPlaceDetails.inserir(place);
        
        //Cria um novo RelateDetailsTypes
        RelateDetailsTypes relateDetailsTypes = new RelateDetailsTypes();
        PlaceTypes type = daoPlaceTypes.buscarPorId(placeAux.getTypeId());
        relateDetailsTypes.setPlace(place);
        relateDetailsTypes.setType(type);
        //Insere o objeto no banco de dados
        daoRelateDetailsTypes.inserir(relateDetailsTypes);
        
        //Remove o objeto PlaceDetailsAux
        daoPlaceDetailsAux.remover(placeAux);
    }
    
    /**
     *
     * @return lista de tipos cadastrados no banco de dados
     */
    public List<PlaceTypes> formaListaTipos(){        
        
        DAOPlaceTypes dao = new DAOPlaceTypes();        
        
        return dao.buscarTodos();
    }
    
    /**
     * Edita um objeto PlaceDetailsAux a partir de seu id, colocando as novas 
     * informações recebidas por parametro.
     *
     */
    public void editarLocal(Integer place_id, String name, String formatted_address, String formatted_phone_number, String website, Integer typeId, String latitude, String longitude) {
        
        DAOPlaceDetailsAux daoDetails = new DAOPlaceDetailsAux();
        PlaceDetailsAux place = daoDetails.buscarPorId(place_id);        
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
        
        daoDetails.alterar(place);
    }
    
}
