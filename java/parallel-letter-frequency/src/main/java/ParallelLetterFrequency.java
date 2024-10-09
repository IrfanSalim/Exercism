import java.util.HashMap;
import java.util.Map;

class ParallelLetterFrequency {
    private String[] texts;

    // Constructor that initializes the texts array
    ParallelLetterFrequency(String[] texts) {
        this.texts = texts;
    }

    // Method to count letters in the provided texts
    Map<Character, Integer> countLetters() {
        Map<Character, Integer> letterFrequency = new HashMap<>();

        // Iterate through each text
        for (String text : texts) {
            // Normalize the text to lower case and iterate through each character
            for (char ch : text.toLowerCase().toCharArray()) {
                // Check if the character is a letter
                if (Character.isLetter(ch)) {
                    letterFrequency.put(ch, letterFrequency.getOrDefault(ch, 0) + 1);
                }
            }
        }

        return letterFrequency;
    }
}
