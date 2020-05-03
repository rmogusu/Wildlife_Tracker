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
        //post: process new sighting form
        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String species = req.queryParams("species");
            String  location = req.queryParams("location");
            Sighting newSighting = new Sighting(rangerName,species, location,1);
            newSighting.save();
//            try {
//                Sighting sighting = new Sighting(rangerName,species,location,rangerId) ;
//                sighting.save();
//            } catch (IllegalArgumentException exception) {
//                System.out.println("Please enter an animal name.");
//            }
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
