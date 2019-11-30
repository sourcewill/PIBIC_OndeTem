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
@Table(name = "PlaceReviews")
public class PlaceReviews {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer review_id;
    
    @OneToOne
    @JoinColumn(name = "place_id")
    private PlaceDetails place;
    
    @Column(name = "author_name")
    private String author_name;
    
    @Column(name = "author_url")
    private String author_url;
    
    @Column(name = "text", length = 1000)
    private String text;
    
    @Column(name = "time")
    private Integer time;
    
    @Column(name = "language")
    private String language;
    
    @Column(name = "overall_rating")
    private Float overall_rating;
    
    @Column(name = "appeal_aspect_rating")
    private Integer appeal_aspect_rating;
    
    @Column(name = "decor_aspect_rating")
    private Integer decor_aspect_rating;
    
    @Column(name = "facilities_aspect_rating")
    private Integer facilities_aspect_rating;
    
    @Column(name = "food_aspect_rating")
    private Integer food_aspect_rating;
    
    @Column(name = "overall_aspect_rating")
    private Integer overall_aspect_rating;
    
    @Column(name = "quality_aspect_rating")
    private Integer quality_aspect_rating;
    
    @Column(name = "service_aspect_rating")
    private Integer service_aspect_rating;

    public PlaceReviews() {
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public PlaceDetails getPlace() {
        return place;
    }

    public void setPlace(PlaceDetails place) {
        this.place = place;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Float getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating(Float overall_rating) {
        this.overall_rating = overall_rating;
    }

    public Integer getAppeal_aspect_rating() {
        return appeal_aspect_rating;
    }

    public void setAppeal_aspect_rating(Integer appeal_aspect_rating) {
        this.appeal_aspect_rating = appeal_aspect_rating;
    }

    public Integer getDecor_aspect_rating() {
        return decor_aspect_rating;
    }

    public void setDecor_aspect_rating(Integer decor_aspect_rating) {
        this.decor_aspect_rating = decor_aspect_rating;
    }

    public Integer getFacilities_aspect_rating() {
        return facilities_aspect_rating;
    }

    public void setFacilities_aspect_rating(Integer facilities_aspect_rating) {
        this.facilities_aspect_rating = facilities_aspect_rating;
    }

    public Integer getFood_aspect_rating() {
        return food_aspect_rating;
    }

    public void setFood_aspect_rating(Integer food_aspect_rating) {
        this.food_aspect_rating = food_aspect_rating;
    }

    public Integer getOverall_aspect_rating() {
        return overall_aspect_rating;
    }

    public void setOverall_aspect_rating(Integer overall_aspect_rating) {
        this.overall_aspect_rating = overall_aspect_rating;
    }

    public Integer getQuality_aspect_rating() {
        return quality_aspect_rating;
    }

    public void setQuality_aspect_rating(Integer quality_aspect_rating) {
        this.quality_aspect_rating = quality_aspect_rating;
    }

    public Integer getService_aspect_rating() {
        return service_aspect_rating;
    }

    public void setService_aspect_rating(Integer service_aspect_rating) {
        this.service_aspect_rating = service_aspect_rating;
    }

    @Override
    public String toString(){
        return "Review author: " + this.author_name;
    }
}
