package models;

//import io.ebean.Finder;
//import io.ebean.Model;
//import play.data.validation.Constraints;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "Country")
//public class Country {
//    @Id
//    public Long id;
//    @Column
//    @Constraints.Required
//    public String name;
//    public String code;
//    public String continent;
//    public String wikipedia_link;
//
//
//    @OneToMany(mappedBy = "country")
//    public List<Airport> airports;
//
//    public List<Airport> getAirports() {
//        return airports;
//    }
//
//    public void setAirports(List<Airport> airports) {
//        this.airports = airports;
//    }
//
//    public String getWikipedia_link() {
//        return wikipedia_link;
//    }
//
//    public void setWikipedia_link(String wikipedia_link) {
//        this.wikipedia_link = wikipedia_link;
//    }
//
//    public String keywords;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getContinent() {
//        return continent;
//    }
//
//    public void setContinent(String continent) {
//        this.continent = continent;
//    }
//
//
//    public String getKeywords() {
//        return keywords;
//    }
//
//    public void setKeywords(String keywords) {
//        this.keywords = keywords;
//    }
//
//    public static Finder<Long, Country> find = new Finder<Long, Country>(Country.class);
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "countries")
public class Country {

    @Id
    private ObjectId _id;
    public String id;
    public String name;
    public String code;
    public String continent;
    public String wikipedia_link;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getWikipedia_link() {
        return wikipedia_link;
    }

    public void setWikipedia_link(String wikipedia_link) {
        this.wikipedia_link = wikipedia_link;
    }
}