package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author William Rodrigues
 */
@Entity
@Table(name = "RelateDetailsTypes")
public class RelateDetailsTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "relate_id")
    private Integer relate_id;

    @OneToOne
    @JoinColumn(name = "place_id")
    private PlaceDetails place;

    @OneToOne
    @JoinColumn(name = "type_id")
    private PlaceTypes type;

    public RelateDetailsTypes() {
    }

    public Integer getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(Integer relate_id) {
        this.relate_id = relate_id;
    }

    public PlaceDetails getPlace() {
        return place;
    }

    public void setPlace(PlaceDetails place) {
        this.place = place;
    }

    public PlaceTypes getType() {
        return type;
    }

    public void setType(PlaceTypes type) {
        this.type = type;
    }

}
