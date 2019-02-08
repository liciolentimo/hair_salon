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

        get("stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-client-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/:stylistId/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylist_Id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String clientName = request.queryParams("clientName");
            int clientPhone = Integer.parseInt(request.queryParams("clientPhone"));
            String clientEmail = request.queryParams("clientEmail");
            Stylist stylist = Stylist.find(client.getStylistId());
            client.update(clientName, clientPhone, clientEmail);
            String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylistId/clients/:id/delete", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            Stylist stylist = Stylist.find(client.getStylistId());
            client.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));

            String clientName = request.queryParams("clientName");
            int clientPhone = Integer.parseInt(request.queryParams("clientPhone"));
            String clientEmail = request.queryParams("clientEmail");
            Client newClient = new Client(clientName, clientPhone, clientEmail, stylist.getId());

            newClient.save();

            model.put("stylist", stylist);
            model.put("template", "templates/stylist-client-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String stylistName = request.queryParams("stylistName");
            int stylistPhone = Integer.parseInt(request.queryParams("stylistPhone"));
            String stylistEmail = request.queryParams("stylistEmail");
            Stylist newStylist = new Stylist(stylistName, stylistPhone, stylistEmail);
            newStylist.save();
            model.put("template", "templates/stylist-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylistId/:id/delete", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            // Stylist stylist = Stylist.find(stylist.getId());
            stylist.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}