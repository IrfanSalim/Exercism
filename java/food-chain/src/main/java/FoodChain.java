class FoodChain {

    private final static int NUMBER_MAX = 8;
    private final static String[] animals = { "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse" };
    private final static String[] phrase = { "I don't know why she swallowed the fly. Perhaps she'll die.",
            "It wriggled and jiggled and tickled inside her.", "How absurd to swallow a bird!",
            "Imagine that, to swallow a cat!", "What a hog, to swallow a dog!",
            "Just opened her throat and swallowed a goat!", "I don't know how she swallowed a cow!",
            "She's dead, of course!" };

    String verse(int verse) {
        StringBuilder couplet = new StringBuilder("");
        if (verse > 0) {
            couplet.append("I know an old lady who swallowed a " + animals[verse - 1] + ".");
            couplet.append("\n" + phrase[verse - 1]);
            if (verse > 1 && verse < NUMBER_MAX) {
                for (int i = 1; i < verse; i++) {
                    if (verse - i - 1 == 1) {
                        couplet.append("\n" + "She swallowed the " + animals[verse - i] +
                                " to catch the " + animals[verse - i - 1] +
                                " that wriggled and jiggled and tickled inside her.");
                    } else {
                        couplet.append("\n" + "She swallowed the " + animals[verse - i] +
                                " to catch the " + animals[verse - i - 1] + ".");
                    }

                }
                couplet.append("\n" + phrase[0]);
            }
        }
        return couplet.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder song = new StringBuilder("");
        for (int i = startVerse; i <= endVerse; i++) {
            if (i != endVerse) {
                song.append(verse(i) + "\n\n");
            } else
                song.append(verse(i));
        }
        return song.toString();
    }
}