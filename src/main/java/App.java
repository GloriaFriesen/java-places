import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    ArrayList<Place> places = new ArrayList<Place>();
    request.session().attribute("places", places);
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/placeList", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    ArrayList<Place> places = new ArrayList<Place>();
    request.session().attribute("places", places);

    String placeName = request.queryParams("placeName");
    Place newPlace = new Place(placeName);
    places.add(newPlace);
    request.session().attribute("place", newPlace);
    model.put("template", "templates/placeList.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/placeList", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();

    // ArrayList<Place> places = request.session().attribute("places");
    // if (places == null) {
    //   places = new ArrayList<Place>();
    //   request.session().attribute("places", places);
    // }
    // request.session().attribute("place");
    ArrayList<Place> places = request.session().attribute("places");
    String placeName = request.queryParams("placeName");
    Place newPlace = new Place(placeName);
    places.add(newPlace);

    model.put("place", request.session().attribute("place"));
    model.put("places", request.session().attribute("places"));
    model.put("template", "templates/placeList.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  }
}
