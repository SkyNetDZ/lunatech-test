package controllers;

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
    private FormFactory formFactory;


    @Inject
    public Application(final FormFactory formFactory) {

        this.formFactory = formFactory;
    }

    public Result index() {
        return ok(index.apply());
    }

//    public Result postForm() {
//        this.countryForm = formFactory.form(Country.class).bindFromRequest();
//        if (countryForm.hasErrors()) {
//            return badRequest(views.html.country.render(countryForm.get()));
//        } else {
//            Country country = countryForm.get();
//            flash("success", "Country saved!");
//            return ok(views.html.country.render(country));
//        }
//    }

}
