import java.util.*;

public class Anagram{

    private final String word;

    public Anagram(String input){
        this.word = input;
    }

    public List<String> match(List<String> possibleAnagramList){
        List<String> anagramList = new ArrayList<>();
            for(String possibleAnagram : possibleAnagramList){
                if(possibleAnagram.length() == word.length() && !possibleAnagram.equalsIgnoreCase(word)){
                    if(isAnagram(possibleAnagram, word))
                    anagramList.add(possibleAnagram);
                }else if(possibleAnagram.length() != word.length() || possibleAnagram.equalsIgnoreCase(word)){

                }else{
                    anagramList = Collections.emptyList();
                }

            }

        return anagramList;
    }

    private boolean isAnagram(String string1, String string2){
        char[] anagram1 = string1.toLowerCase().toCharArray();
        char[] anagram2 = string2.toLowerCase().toCharArray();
        Arrays.sort(anagram1);
        Arrays.sort(anagram2);

        return Arrays.equals(anagram1, anagram2);
    }

}


import java.util.Arrays;
import java.util.List;

class Anagram {

    private final String input;
    private final List<String> characterList;

    Anagram(final String input) {
        var upperCaseInput = prepareWord(input);
        this.input = upperCaseInput;
        this.characterList = getSortedCharacters(upperCaseInput);
    }

    private String prepareWord(String word) {
        return word.toUpperCase();
    }

    private List<String> getSortedCharacters(String input) {
        return Arrays.stream(input.split("")).sorted().toList();
    }

    List<String> match(final List<String> candidates) {
        return candidates.stream()
                .filter(candidate -> {
                    var upperCaseCandidate = prepareWord(candidate);
                    return !input.equals(upperCaseCandidate) && characterList.equals(getSortedCharacters(upperCaseCandidate));
                })
                .toList();
    }
}
