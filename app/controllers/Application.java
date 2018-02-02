package controllers;

import io.ebean.Ebean;
import models.Country;
import play.*;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.List;

public class Application extends Controller {

    private List<Country> countries;

    @Inject
    public Application() {
        this.countries = Ebean.find(Country.class).fetch("airports").where().eq("code","US").findList();
    }

    public Result index() {
        return ok(index.render(this.countries));
    }

}
