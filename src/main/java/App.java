import org.sql2o.Sql2o;
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
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "rose", "wambua");

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
            model.put("endangered", unEndangered);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //show new Sighting form
        get("/sighting/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new sighting form
        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            String rangerName = req.queryParams("rangerName");
            String species = req.queryParams("species");
            String location = req.queryParams("location");
            Sighting newSighting = new Sighting(rangerName, species, location, 1);
            newSighting.save();
            return new ModelAndView(model, "sighting-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //show new Endangered form
        get("/endangered/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Endangered> endangereds = Endangered.all();
            model.put("endangereds", endangereds);
            List<UnEndangered> unEndangereds = UnEndangered.all();
            model.put("endangereds", unEndangereds);
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new Endangered form
        post("/endangered", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Endangered> endangereds = Endangered.all();
            model.put("endangereds", endangereds);
            List<UnEndangered> unEndangereds = UnEndangered.all();
            model.put("unEndangereds", unEndangereds);
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Endangered newEndangered = new Endangered(name, health, age, 1);
            newEndangered.save();
            return new ModelAndView(model, "endangered-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //show new Rangers form
        get("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new Endangered form
        post("/rangers", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            String name = req.queryParams("name");
            int badgeNo = Integer.parseInt(req.queryParams("badgeNo"));
            int contact = Integer.parseInt(req.queryParams("contact"));
            Ranger newRanger = new Ranger(name, badgeNo, contact);
            newRanger.save();
            return new ModelAndView(model, "rangers-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //Delete all rangers
        get("/rangers/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            String name = req.queryParams("name");
            String num = req.queryParams("badgeNo");
            int badgeNo = 0;
            if(num!=null){
                try{
                    badgeNo = Integer.parseInt(num);
                }catch(Exception e){
                }
            }
            String con = req.queryParams("contact");
            int contact = 0;
            if(con!=null){
                try{
                    contact= Integer.parseInt(con);
                }catch(Exception e){
                }
            }
            Ranger newRanger = new Ranger(name, badgeNo, contact);
            newRanger.save();
            newRanger.delete();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //delete all sightings
           get("/sightings/delete", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            String rangerName = req.queryParams("rangerName");
            String species = req.queryParams("species");
            String location = req.queryParams("location");
            Sighting newSighting = new Sighting(rangerName, species, location, 1);
            newSighting.save();
            newSighting.delete();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        get("/endangered/delete", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Endangered> endangereds = Endangered.all();
            model.put("endangereds", endangereds);
            List<UnEndangered> unEndangereds = UnEndangered.all();
            model.put("unEndangereds", unEndangereds);
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Endangered newEndangered = new Endangered(name, health, age, 1);
            newEndangered.save();
            newEndangered.delete();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
       //get: show an individual ranger and sightings it contains
        get("/rangers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRangerToFind = Integer.parseInt(req.params("id")); //new
            Ranger foundRanger = Ranger.find(idOfRangerToFind);
            model.put("ranger", foundRanger);
            List<Sighting> allSightingByRanger = Ranger.getSightings(idOfRangerToFind);
            model.put("sightings", allSightingByRanger);
            model.put("rangers", Ranger.all());
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sighting-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
