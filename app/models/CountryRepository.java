package models;

import com.mongodb.AggregationOptions;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Group;

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

    public Iterator<Airport> getAirports() {
        AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
        return morphia.createDatastore(new MongoClient(), "lunadb")
                .createAggregation(Airport.class)
                .group("iso_country", Group.grouping("airports", Group.push("name")))
                .out(Airport.class, options);
    }

}