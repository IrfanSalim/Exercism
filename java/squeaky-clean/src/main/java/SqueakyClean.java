import java.util.HashMap;

class SqueakyClean {
    static String clean(String identifier) {
      int maxLength = identifier.length();
      String regex = "[a-zA-Z0-9_ -]+";
      HashMap<Character, Character> hm = new HashMap<>();
      hm.put('3', 'e');
      hm.put('4', 'a');
      hm.put('1', 'l');
      hm.put('0', 'o');
      hm.put('7', 't');
      StringBuilder sb = new StringBuilder(maxLength);
      boolean camelCase = false;
      for (char c : identifier.toCharArray()) {
        if (String.valueOf(c).matches(regex)) {
          if (Character.isWhitespace(c)) {
            sb.append("_");
          } else if (c == '-') {
            camelCase = true;
          } else if (hm.containsKey(c)) {
            sb.append(hm.get(c));
          } else {
            if (camelCase) {
              sb.append(Character.toUpperCase(c));
              camelCase = false;
            }
            else sb.append(c);
          }
        }
      }
      
      return sb.toString();
    }
}


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class SqueakyClean {
    static String clean(String identifier) {
        return toCamelCase(identifier.split(""))
                        .stream()
                        .filter(c -> !c.equals(""))
                        .map(c -> c.equals(" ")?"_":c)
                        .map(c -> Character.isISOControl(c.charAt(0))?"CTRL":c)
                        .map(c -> ((c.charAt(0) >= 'α' && c.charAt(0) <= 'ω') || (!Character.isAlphabetic(c.charAt(0)) && !c.equals("_")))?"":c)
                        .collect(Collectors.joining());
    }
    static List<String> toCamelCase(String[] list){
        int[] indexes = IntStream.range(0, list.length).filter(i -> list[i].equals("-")).toArray();
        for (int i:indexes) list[i+1] = list[i+1].toUpperCase();
        return Arrays.stream(list).filter(c -> !c.equals("-")).collect(Collectors.toList());
    }
}


import java.util.HashMap;

public class SqueakyClean {
    private static final HashMap<Character, Character> CHARACTER_MAP = new HashMap<>();

    static {
        CHARACTER_MAP.put('3', 'e');
        CHARACTER_MAP.put('4', 'a');
        CHARACTER_MAP.put('1', 'l');
        CHARACTER_MAP.put('0', 'o');
        CHARACTER_MAP.put('7', 't');
    }

    public static String clean(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            return ""; // Handle null or empty input gracefully
        }

        StringBuilder cleanedIdentifier = new StringBuilder();
        boolean camelCase = false;

        for (char c : identifier.toCharArray()) {
            if (isAllowedCharacter(c)) {
                if (Character.isWhitespace(c)) {
                    cleanedIdentifier.append("_");
                } else if (c == '-') {
                    camelCase = true;
                } else {
                    cleanedIdentifier.append(handleCharacter(c, camelCase));
                    camelCase = false;
                }
            }
        }

        return cleanedIdentifier.toString();
    }

    private static boolean isAllowedCharacter(char c) {
        // Use a simpler regular expression to check if the character is allowed
        return Character.isLetterOrDigit(c) || c == '-' || Character.isWhitespace(c);
    }

    private static char handleCharacter(char c, boolean camelCase) {
        char cleanedChar = c;
        if (CHARACTER_MAP.containsKey(c)) {
            cleanedChar = CHARACTER_MAP.get(c);
        }
        if (camelCase) {
            cleanedChar = Character.toUpperCase(cleanedChar);
        }
        return cleanedChar;
    }
}

