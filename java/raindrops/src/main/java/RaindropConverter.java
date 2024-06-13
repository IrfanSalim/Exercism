class RaindropConverter {

    String convert(int number) {
      String result = "";
      if (number % 3 == 0) result += "Pling";
      if (number % 5 == 0) result += "Plang";
      if (number % 7 == 0) result += "Plong";
      return result.equals("") ? String.valueOf(number) : result;
    }

}

import java.util.HashMap;
public class RaindropConverter {
  private static final HashMap<Integer, String> MAP = new HashMap<>();
  static {
    MAP.put(3, "Pling");
    MAP.put(5, "Plang");
    MAP.put(7, "Plong");
  }
  public String convert(int number) {
    return MAP.keySet()
      .stream()
      .filter(key -> number % key == 0)
      .map(key -> MAP.get(key))
      .reduce((s1, s2) -> s1 + s2)
      .orElse(Integer.toString(number));
  }
}
