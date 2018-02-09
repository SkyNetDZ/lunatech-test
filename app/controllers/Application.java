package controllers;

import models.Country;
import models.CountryRepository;
import play.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.List;

public class Application extends Controller {


    @Inject
    public Application() {

    }

    public Result index() {
        return ok(index.apply());
    }



}
