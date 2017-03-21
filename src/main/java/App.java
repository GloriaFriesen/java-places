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
    ArrayList<Place> placesArray = new ArrayList<Place>();

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/placeList", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    request.session().attribute("placesArrayKey", placesArray);
    String placeName = request.queryParams("placeName");
    Place newPlace = new Place(placeName);
    placesArray.add(newPlace);

    model.put("printPlaces", request.session().attribute("placesArrayKey"));
    model.put("template", "templates/placeList.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  }
}
