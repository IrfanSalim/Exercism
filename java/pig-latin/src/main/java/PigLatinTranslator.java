// public class PigLatinTranslator {
//     static final String PATTERN;
//     static {
//         // ?! is zero width negative lookahead
//         String no_yt_or_xr_at_start = "(?!yt|xr)";
//         String qu_or_consonant_at_start = "(qu|[^aeiou ])";
//         String then_qu_or_consonant_except_y = "(qu|[^aeiouy ])*";
//         String consonants = no_yt_or_xr_at_start
//                 + qu_or_consonant_at_start
//                 + then_qu_or_consonant_except_y;
//         PATTERN = String.format("(?<consonants>%s)?(?<rest>\\w+)", consonants);
//     }

//     public static String translate(String input) {
//         return input.replaceAll(PATTERN, "${rest}${consonants}ay");
//     }
// }

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PigLatinTranslator {
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern RULES = Pattern.compile("((?<vowel>[aeiou]|xr|yt)|" +
            "(?<consonant>(?!xr|yt)y?(?:[^aeiouy]*qu|[^aeiouy]*)))" +
            "(?<body>.*)");
    private static final String TEMPLATE = "${vowel}${body}${consonant}ay";

    String translate(String phrase) {
        Stream<String> wordsStream = WHITESPACE.splitAsStream(phrase);
        return wordsStream.map(this::translateWord)
                .collect(Collectors.joining(" "));
    }

    private String translateWord(String word) {
        return RULES.matcher(word).replaceFirst(TEMPLATE);
    }
}