// import java.util.HashMap;
// import java.util.Map;

// class ParallelLetterFrequency {
//     private String[] texts;

//     // Constructor that initializes the texts array
//     ParallelLetterFrequency(String[] texts) {
//         this.texts = texts;
//     }

//     // Method to count letters in the provided texts
//     Map<Character, Integer> countLetters() {
//         Map<Character, Integer> letterFrequency = new HashMap<>();

//         // Iterate through each text
//         for (String text : texts) {
//             // Normalize the text to lower case and iterate through each character
//             for (char ch : text.toLowerCase().toCharArray()) {
//                 // Check if the character is a letter
//                 if (Character.isLetter(ch)) {
//                     letterFrequency.put(ch, letterFrequency.getOrDefault(ch, 0) + 1);
//                 }
//             }
//         }

//         return letterFrequency;
//     }
// }

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParallelLetterFrequency {
    private final String[] texts;

    // Constructor that initializes the texts array
    ParallelLetterFrequency(String[] texts) {
        this.texts = texts;
    }

    // Method to count letters in the provided texts using parallel streams
    Map<Character, Integer> countLetters() {
        // Using parallel stream to process each text and collect letter frequencies
        return Stream.of(texts)
                .parallel() // Enable parallel processing
                .flatMap(text -> text.toLowerCase().chars() // Convert each text to a stream of characters
                        .filter(Character::isLetter) // Keep only letters
                        .mapToObj(ch -> (char) ch)) // Convert int stream to Character stream
                .collect(Collectors.toMap(
                        ch -> ch,
                        ch -> 1,
                        Integer::sum)); // Sum frequencies for each letter
    }
}
