class Proverb {
    String[] words;
    
    Proverb(String[] words) {
      this.words = words;
    }

    String recite() {
      if (words.length == 0) return "";

      String proverb = "";
      for (int i = 0; i < words.length - 1; i++) {
       proverb += "For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n";
      }
      return proverb + "And all for the want of a " + words[0] + ".";
    }

}


import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {

    private final String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        return IntStream.range(0, words.length)
                .mapToObj(this::format)
                .collect(Collectors.joining("\n"));
    }

    private String format(int s) {
        return s != words.length - 1 ?
                "For want of a " + words[s] + " the " + words[s + 1] + " was lost."
                : "And all for the want of a " + words[0] + ".";
    }
}



