import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String stylistName = request.queryParams("stylistName");
            int stylistPhone = Integer.parseInt(request.queryParams("stylistPhone"));
            String stylistEmail = request.queryParams("stylistEmail");
            Stylist newStylist = new Stylist(stylistName, stylistPhone, stylistEmail);
            newStylist.save();
            response.redirect("/");
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}