package models;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;
import com.mongodb.MongoClient;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.internal.MorphiaUtils;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

public class CountryRepository {

    private Datastore datastore;

    @Inject
    public CountryRepository(Morphia morphia) {
        this.datastore = morphia.createDatastore(new MongoClient(), "lunadb");
    }

    public List<Country> countryList(){
        return datastore.createQuery(Country.class).asList();
    }


    private Iterator<Country> groupAirports() {
        AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
        Query<Country> query = this.datastore.createQuery(Country.class);
        Accumulator count = new Accumulator("sum" ,"airports");
        return datastore
                .createAggregation(Country.class)
                //.match(query.filter("name =" , countryName))
                .lookup("airports" , "code", "iso_country", "airports")
                .out(Country.class, options);
    }

    private Iterator<Airport> groupRunways() {
        AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
        return this.datastore
                .createAggregation(Airport.class)
                .lookup("runways" , "id", "airport_ref", "runways")
                .out(Airport.class, options);
    }

    public List<Country> listAirports(){
         if(this.datastore.find(Country.class).limit(1).get().airports == null){
             this.groupAirports();
         }
         return this.datastore.createQuery(Country.class).asList();
    }

    public List<Country> listHighestNbrAirports(){
        return this.datastore
                .createQuery(Country.class)
                .order("$airports.length")
                .limit(10)
                .asList();
    }

    public List<Country> listLowestNbrAirports(){
        return this.datastore
                .createQuery(Country.class)
                .order("$airports.length")
                .limit(10)
                .asList();
    }
}