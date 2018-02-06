package controllers;

import io.ebean.Ebean;
import models.Country;
import play.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.List;

public class Application extends Controller {

    private List<Country> countries;
    Form<Country> countryForm ;


//    @Inject
//    public Application() {
//        this.countries = Ebean.find(Country.class).fetch("airports").where().eq("code","US").findList();
//    }



    public Result index() {
        return ok(index.render(countryForm));
    }

}
