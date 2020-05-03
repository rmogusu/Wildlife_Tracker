import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        //get: show all sightings in all rangers  and show all animals in all sightings
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            List<Endangered> endangered = Endangered.all();
            model.put("endangered", endangered);
            List<UnEndangered> unEndangered = UnEndangered.all();
            model.put("unEndangered", unEndangered);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //show new Sighting form
        get("/sighting/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
