import java.util.HashSet;

public class PangramChecker {

    public boolean isPangram(String input) {
      input = input.toLowerCase().replaceAll("[^a-z]", "");
      HashSet<Character> letters = new HashSet<>();
      for (int i = 0; i < input.length(); i++) {
        letters.add(input.charAt(i));
      }
      return letters.size() == 26;
    }

}
