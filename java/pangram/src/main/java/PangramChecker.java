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

public class PangramChecker {
    private final static int LETTERS_IN_ALPHABET = 26;

    public boolean isPangram(String input) {
        return input.toLowerCase().chars()
            .filter(Character::isLetter)
            .distinct()
            .count() == LETTERS_IN_ALPHABET;
    }
}

import java.util.Arrays;

public class PangramChecker {

    public boolean isPangram(String input) {
        return Arrays.asList(input.toLowerCase().split(""))
            .containsAll(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
    }
}
