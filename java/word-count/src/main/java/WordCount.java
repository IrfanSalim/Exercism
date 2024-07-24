import java.util.Map;
import java.util.HashMap;

class WordCount {
    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> map = new HashMap<>();
        if (input == null) return map;

        // Update the regex to handle apostrophes and numbers correctly
        String[] words = input.toLowerCase().split("[^a-zA-Z0-9']+");
        for (String word : words) {
            // Handle leading and trailing apostrophes
            word = word.replaceAll("^'+|'+$", "");
            if (word.length() > 0) {
                map.compute(word, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return map;
    }
}


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class WordCount {
    public Map<String,Integer> phrase(String word) {
        return Arrays.stream(word
                .toLowerCase()
                .replaceAll("[^a-z0-9' ]", " ")
                .trim()
                .split("\\s+"))
            .map(it -> it.replaceAll("^'|'$", ""))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().intValue()));
    }
}

