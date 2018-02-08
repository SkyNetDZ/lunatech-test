package controllers;

import com.google.common.collect.Lists;
import models.Country;
import models.CountryRepository;
import play.libs.Json;
import play.mvc.*;
import javax.inject.Inject;
import java.util.List;

public class CountryController extends Controller {

   private List<Country> countries;
   private List<Country> airports;
   private CountryRepository countryRepository;


    @Inject
    public CountryController(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;
        //this.countryRepository.listAirports();

    }

    public Result list(){
        this.countries = this.countryRepository.countryList();
        return ok(Json.toJson(this.countries));
    }

    public Result listAirports(){
        List<Country> it = this.countryRepository.listAirports();
        this.airports = Lists.newArrayList(it);
        return ok(Json.toJson(this.airports));
    }


    public Result sortCountriesByAirports(String direction){
        if(direction.endsWith("asc")){
            return ok(Json.toJson(this.countryRepository.listLowestNbrAirports()));
        }else {
            return ok(Json.toJson(this.countryRepository.listHighestNbrAirports()));
        }
    }

}
