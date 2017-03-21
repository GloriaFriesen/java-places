import org.junit.*;
import static org.junit.Assert.*;

public class PlaceTest{

  @Test
  public void Place_instantiatesCorrectly_true() {
    Place myPlace = new Place("Mexico");
    assertEquals(true, myPlace instanceof Place);
  }
  @Test
  public void Place_instantiatesCorrectly_placeName() {
    Place myPlace = new Place("Mexico");
    assertEquals("Mexico", myPlace.getPlaceName());
  }
}
