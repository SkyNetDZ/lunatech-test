package models;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;
import com.mongodb.MongoClient;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.internal.MorphiaUtils;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

public class CountryRepository {

    @Inject
    private Morphia morphia;


    public List<Country> countryList(){
        return morphia.createDatastore(new MongoClient(), "lunadb").createQuery(Country.class).asList();
    }

    public Country findById(int id) {
        Country country = morphia.createDatastore(new MongoClient(), "lunadb").createQuery(Country.class).
                field("id").equal(id).get();
        return country;
    }

    public Country findByName(String name) {
        Country country = morphia.createDatastore(new MongoClient(), "lunadb").createQuery(Country.class).
                field("name").equal(name).get();
        return country;
    }

    private Iterator<Country> groupAirports() {
        AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
        Query<Country> query = morphia.createDatastore(new MongoClient(), "lunadb").createQuery(Country.class);
        return morphia.createDatastore(new MongoClient(), "lunadb")
                .createAggregation(Country.class)
                //.match(query.filter("name =" , countryName))
                .lookup("airports" , "code", "iso_country", "airports")
                //.group(Group.id(Group.grouping("iso_country")), Group.grouping("airports", Group.push("name")))
                .out(Country.class, options);
    }

    public List<Country> listAirpots(){
        Iterator<Country> it = this.groupAirports();
        return morphia.createDatastore(new MongoClient(), "lunadb").createQuery(Country.class).asList();
    }

}