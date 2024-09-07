import java.util.List;
import java.util.ArrayList;

class Anagram {
    String anagram;
    int[] charCount = new int[26];
    public Anagram(String word) {
      this.anagram = word;
      word = word.toLowerCase();
      for (int i = 0; i < word.length(); i++) {
        charCount[word.charAt(i) - 'a']++;
      }
    }

    public List<String> match(List<String> candidates) {
      List<String> result = new ArrayList<>();
      for (String candidate : candidates) {
        candidate = candidate.toLowerCase();
        if (anagram.length() == candidate.length()) {
          int[] candidateCharCount = new int[26];
          for (int i = 0; i < candidate.length(); i++) {
            candidateCharCount[candidate.charAt(i) - 'a']++;
          }
          boolean match = true;
          for (int i = 0; i < 26; i++) {
            if (charCount[i] != candidateCharCount[i]) {
              match = false;
              break;
            }
          }
          if (match) {
            result.add(candidate);
          }
        }
      }
      return result;
    }

}
