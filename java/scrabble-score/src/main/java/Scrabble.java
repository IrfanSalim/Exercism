// import java.util.HashMap;
// import java.util.Map;

// class Scrabble {
//     private String word;
//     private static final Map<Integer, Integer> lScore = new HashMap<>(26);
//     static {
//         "AEIOULNRST".chars().forEach(c -> lScore.put(c, 1));
//         "DG".chars().forEach(c -> lScore.put(c, 2));
//         "BCMP".chars().forEach(c -> lScore.put(c, 3));
//         "FHVWY".chars().forEach(c -> lScore.put(c, 4));
//         "K".chars().forEach(c -> lScore.put(c, 5));
//         "JX".chars().forEach(c -> lScore.put(c, 8));
//         "QZ".chars().forEach(c -> lScore.put(c, 10));
//     }

//     Scrabble(String word) {
//         this.word = word.toUpperCase();
//     }

//     int getScore() {
//         return word.chars().reduce(0, (a, b) -> a + lScore.get(b));
//     }

// }

import java.util.TreeMap;
import java.util.function.IntPredicate;

class Scrabble {
    private final String word;
    private static final TreeMap<String, Integer> scrableMap = new TreeMap<>();
    private int counter;

    Scrabble(String word) {

        this.word = word.toUpperCase();
    }

    int getScore() {
        counter = 0;

        word.chars()
                .mapToObj((c1) -> scrableMap.keySet().stream().filter(
                        (s) -> s.contains(Character.toString((char) c1))))
                .forEach(
                        (str1) -> str1.forEach(
                                (s2) -> counter = counter + scrableMap.getOrDefault(s2, 0)));

        return counter;
    }

    static {
        scrableMap.put("AEIOULNRST", 1);
        scrableMap.put("DG", 2);
        scrableMap.put("BCMP", 3);
        scrableMap.put("FHVWY", 4);
        scrableMap.put("K", 5);
        scrableMap.put("JX", 8);
        scrableMap.put("QZ", 10);
    }

}