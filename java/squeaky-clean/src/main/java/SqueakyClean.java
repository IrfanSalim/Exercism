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
