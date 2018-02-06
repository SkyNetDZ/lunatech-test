package controllers;

import models.Country;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class CountryController extends Controller {

    private FormFactory formFactory;
    Form<Country> countryForm ;



    @Inject
    public CountryController(final FormFactory formFactory) {
        this.formFactory = formFactory;
    }



    public Result postForm() {
        this.countryForm = formFactory.form(Country.class).bindFromRequest();
        if (countryForm.hasErrors()) {
            return badRequest(views.html.country.render(countryForm.get()));
        } else {
            Country country = countryForm.get();
            flash("success", "Product saved!");
            return ok(views.html.country.render(country));
        }
    }
}
