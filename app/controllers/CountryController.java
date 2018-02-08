package controllers;

import com.google.common.collect.Lists;
import models.Airport;
import models.Country;
import models.CountryRepository;
import play.libs.Json;
import play.mvc.*;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

public class CountryController extends Controller {

   private List<Country> countries;
   private List<Country> airports;
   private CountryRepository countryRepository;


    @Inject
    public CountryController(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;
        List<Country> it = this.countryRepository.listAirpots();

    }

    public Result list(){
        this.countries = this.countryRepository.countryList();
        return ok(Json.toJson(this.countries));
    }

    public Result listAirports(){
        List<Country> it = this.countryRepository.listAirpots();
        this.airports = Lists.newArrayList(it);
        return ok(Json.toJson(this.airports));
    }

}
