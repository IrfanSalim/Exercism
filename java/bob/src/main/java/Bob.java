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
      return input.toUpperCase().equals(input) && !isQuestion(input);
    }

    boolean isYellQuestion(String input) {
      return input.toUpperCase().equals(input) && isQuestion(input);
    }

}
