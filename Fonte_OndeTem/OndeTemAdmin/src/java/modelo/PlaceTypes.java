package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author William Rodrigues
 */
@Entity
@Table(name = "PlaceTypes")
public class PlaceTypes {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "type_id")
    private Integer type_id;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "traducao")
    private String traducao;

    public PlaceTypes() {
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }
    
    
    
    @Override
    public String toString(){
        return("type: " + this.type 
                + " | category: " + this.category);
    }
}
