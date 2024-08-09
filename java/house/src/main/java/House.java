import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {
	static final String[] PARTS = { "the house that Jack built.", "the malt that lay in ", "the rat that ate ",
			"the cat that killed ", "the dog that worried ", "the cow with the crumpled horn that tossed ",
			"the maiden all forlorn that milked ", "the man all tattered and torn that kissed ",
			"the priest all shaven and shorn that married ", "the rooster that crowed in the morn that woke ",
			"the farmer sowing his corn that kept ", "the horse and the hound and the horn that belonged to " };

	String verse(int verse) {
		StringBuilder result = new StringBuilder("This is ");
		for (int i = verse - 1; i >= 0; i--) {
			result.append(PARTS[i]);
		}
		return result.toString();
	}

	String verses(int startVerse, int endVerse) {
		return String.join("\n",
				IntStream.rangeClosed(startVerse, endVerse).mapToObj(this::verse).collect(Collectors.toList()));
	}

	String sing() {
		return verses(1, 12);
	}
}

import java.util.stream.IntStream;
import java.util.stream.Collectors;

class House {
  private static final String[] NOUNS = {
    "house that Jack built.",
    "malt",
    "rat",
    "cat",
    "dog",
    "cow with the crumpled horn",
    "maiden all forlorn",
    "man all tattered and torn",
    "priest all shaven and shorn",
    "rooster that crowed in the morn",
    "farmer sowing his corn",
    "horse and the hound and the horn"
  };
  private static final String[] VERBS = {
    "lay in",
    "ate",
    "killed",
    "worried",
    "tossed",
    "milked",
    "kissed",
    "married",
    "woke",
    "kept",
    "belonged to"
  };

  String verse(int n) {
    StringBuilder verse = new StringBuilder(String.format("This is the %s", NOUNS[--n]));
    while (n > 0) verse.append(String.format(" that %s the %s", VERBS[--n], NOUNS[n]));
    return verse.toString();
  }

  String verses(int start, int end) {
    return IntStream.rangeClosed(start, end).mapToObj(i -> verse(i))
      .collect(Collectors.joining("\n"));
  }

  String sing() {
    return verses(1, 12);
  }
}
