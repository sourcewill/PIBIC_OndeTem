package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PlaceReviews.class)
public abstract class PlaceReviews_ {

	public static volatile SingularAttribute<PlaceReviews, String> author_name;
	public static volatile SingularAttribute<PlaceReviews, Integer> review_id;
	public static volatile SingularAttribute<PlaceReviews, Integer> appeal_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, Integer> decor_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, String> language;
	public static volatile SingularAttribute<PlaceReviews, Integer> food_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, Integer> overall_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, Integer> facilities_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, String> author_url;
	public static volatile SingularAttribute<PlaceReviews, Integer> quality_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, Integer> service_aspect_rating;
	public static volatile SingularAttribute<PlaceReviews, PlaceDetails> place;
	public static volatile SingularAttribute<PlaceReviews, String> text;
	public static volatile SingularAttribute<PlaceReviews, Integer> time;
	public static volatile SingularAttribute<PlaceReviews, Float> overall_rating;

}

