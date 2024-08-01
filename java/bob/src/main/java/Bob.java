class Bob {

    String hey(String input) {
        if (input == null || input.trim().length() == 0) {
            return "Fine. Be that way!";
        } else if (isYell(input)) {
            return "Whoa, chill out!";
        } else if (isYellQuestion(input)) {
            return "Calm down, I know what I'm doing!";
        } else if (isQuestion(input)) {
            return "Sure.";
        } else {
            return "Whatever.";
        }
    }

    boolean isQuestion(String input) {
        return input.trim().endsWith("?");
    }

    boolean isYell(String input) {
        boolean hasLetters = input.chars().anyMatch(Character::isLetter);
        return hasLetters && input.equals(input.toUpperCase()) && !isQuestion(input);
    }

    boolean isYellQuestion(String input) {
        boolean hasLetters = input.chars().anyMatch(Character::isLetter);
        return hasLetters && input.equals(input.toUpperCase()) && isQuestion(input);
    }
}


import java.util.function.Predicate;
import java.util.regex.Pattern;

class Bob {

    final private static Pattern isAlpha = Pattern.compile("[a-zA-Z]");
    final private static Predicate < String > isShout = msg -> isAlpha.matcher(msg).find() && msg == msg.toUpperCase();

    public String hey(String message) {
        var speech = message.trim();
        if (speech.isEmpty()) {
            return "Fine. Be that way!";
        }
        var questioning = speech.endsWith("?");
        var shouting = isShout.test(speech);
        if (questioning) {
            if (shouting) {
                return "Calm down, I know what I'm doing!";
            }
            return "Sure.";
        }
        if (shouting) {
            return "Whoa, chill out!";
        }
        return "Whatever.";
    }
}

import java.util.function.Predicate;
import java.util.regex.Pattern;

class Bob {
    final private static String[] answers = {
        "Whatever.",
        "Sure.",
        "Whoa, chill out!",
        "Calm down, I know what I'm doing!"
    };
    final private static Pattern isAlpha = Pattern.compile("[a-zA-Z]");
    final private static Predicate < String > isShout = msg -> isAlpha.matcher(msg).find() && msg == msg.toUpperCase();

    public String hey(String message) {
        var speech = message.trim();
        if (speech.isEmpty()) {
            return "Fine. Be that way!";
        }
        var questioning = speech.endsWith("?") ? 1 : 0;
        var shouting = isShout.test(speech) ? 2 : 0;
        return answers[questioning + shouting];
    }
}
