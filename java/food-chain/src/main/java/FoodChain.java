// class FoodChain {

//     private final static int NUMBER_MAX = 8;
//     private final static String[] animals = { "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse" };
//     private final static String[] phrase = { "I don't know why she swallowed the fly. Perhaps she'll die.",
//             "It wriggled and jiggled and tickled inside her.", "How absurd to swallow a bird!",
//             "Imagine that, to swallow a cat!", "What a hog, to swallow a dog!",
//             "Just opened her throat and swallowed a goat!", "I don't know how she swallowed a cow!",
//             "She's dead, of course!" };

//     String verse(int verse) {
//         StringBuilder couplet = new StringBuilder("");
//         if (verse > 0) {
//             couplet.append("I know an old lady who swallowed a " + animals[verse - 1] + ".");
//             couplet.append("\n" + phrase[verse - 1]);
//             if (verse > 1 && verse < NUMBER_MAX) {
//                 for (int i = 1; i < verse; i++) {
//                     if (verse - i - 1 == 1) {
//                         couplet.append("\n" + "She swallowed the " + animals[verse - i] +
//                                 " to catch the " + animals[verse - i - 1] +
//                                 " that wriggled and jiggled and tickled inside her.");
//                     } else {
//                         couplet.append("\n" + "She swallowed the " + animals[verse - i] +
//                                 " to catch the " + animals[verse - i - 1] + ".");
//                     }

//                 }
//                 couplet.append("\n" + phrase[0]);
//             }
//         }
//         return couplet.toString();
//     }

//     String verses(int startVerse, int endVerse) {
//         StringBuilder song = new StringBuilder("");
//         for (int i = startVerse; i <= endVerse; i++) {
//             if (i != endVerse) {
//                 song.append(verse(i) + "\n\n");
//             } else
//                 song.append(verse(i));
//         }
//         return song.toString();
//     }
// }

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class FoodChain {

    private static final String START = "I know an old lady who swallowed a %s.\n";

    private final class Description {

        private String creature;
        private String reason;
        private String connection;

        public Description(String creature, String reason, String connection) {
            this.creature = creature;
            this.reason = reason;
            this.connection = connection;
        }

        public String getCreature() {
            return creature;
        }

        public String getReason() {
            return reason;
        }

        public String getConnection() {
            return connection;
        }
    }

    private static Map<Integer, Description> creatures;

    {
        creatures = new HashMap<>();
        creatures.put(1, new Description("fly", "I don't know why she swallowed the fly. Perhaps she'll die.", ""));
        creatures.put(2,
                new Description("spider",
                        "She swallowed the spider to catch the fly.",
                        "It wriggled and jiggled and tickled inside her."));
        creatures.put(3,
                new Description("bird",
                        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
                        "How absurd to swallow a bird!"));
        creatures.put(4,
                new Description("cat",
                        "She swallowed the cat to catch the bird.",
                        "Imagine that, to swallow a cat!"));
        creatures.put(5,
                new Description("dog",
                        "She swallowed the dog to catch the cat.",
                        "What a hog, to swallow a dog!"));
        creatures.put(6,
                new Description("goat",
                        "She swallowed the goat to catch the dog.",
                        "Just opened her throat and swallowed a goat!"));
        creatures.put(7,
                new Description("cow",
                        "She swallowed the cow to catch the goat.",
                        "I don't know how she swallowed a cow!"));
        creatures.put(8, new Description("horse", "She's dead, of course!", ""));
    }

    public String verse(int start) {
        Description firstDescription = creatures.get(start);
        String intro = format(START, firstDescription.getCreature());

        if (start == creatures.keySet()
                .stream()
                .findFirst()
                .get() || start == creatures.size()) {
            return intro + firstDescription.getReason();
        }

        return intro + range(0, start).map(i -> start - i)
                .mapToObj(i -> {
                    Description currentDescription = creatures.get(i);
                    String reason = currentDescription.getReason();
                    return i == start ? currentDescription.getConnection()
                            .concat("\n")
                            .concat(reason) : reason;
                })
                .collect(joining("\n"));

    }

    public String verses(int start, int end) {
        return range(start, end + 1).mapToObj(i -> verse(i))
                .collect(joining("\n\n"));
    }
}