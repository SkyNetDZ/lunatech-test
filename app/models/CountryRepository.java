package models;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.query.QueryImpl;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

public class CountryRepository {

    @Inject
    private Morphia morphia;

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
        return morphia.createDatastore(new MongoClient(), "lunadb").createAggregation(Country.class)
                .lookup("countries", "iso_country", "code", "airport_docs")
                .out(Airport.class);

    }

    public void save(Country u) {
        morphia.createDatastore(new MongoClient(), "lunadb").save(u);
    }
}