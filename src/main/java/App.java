import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        //get: show all Heroes in all squads and show all squads
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//        List<Squad> squads = squadDao.getAll();
//        model.put("squads", squads);
//        List<Hero> heroes = heroDao.getAll();
//        model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
