class IsogramChecker {

    boolean isIsogram(String phrase) {
      phrase = phrase.replaceAll("[^a-zA-Z]", "").toLowerCase();
      return phrase.toLowerCase().chars().distinct().count() == phrase.length();
    }

}


import java.util.HashSet;

final class IsogramChecker {

    boolean isIsogram(final String phrase) {
        return phrase.chars()
            .filter(Character::isLetter)
            .map(Character::toLowerCase)
            .allMatch(new HashSet < > ()::add);
    }
}


import java.util.stream.Collectors;

public class IsogramChecker {

    public boolean isIsogram(String input) {
        final var scrubbed = input.chars()
            .filter(Character::isLetter)
            .mapToObj(Character::toLowerCase)
            .collect(Collectors.toList());

        return scrubbed.size() == scrubbed.stream().distinct().count();
    }
}
