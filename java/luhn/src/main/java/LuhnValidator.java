class LuhnValidator {

    boolean isValid(String candidate) {
      // Remove all non-digit characters
      candidate = candidate.replaceAll("\\s", "");
      
      // Check if the candidate contains only digits
      if (candidate.length() <= 1 || !candidate.matches("\\d+")) {
        return false;
      }

      // Reverse the cleaned string
      String reversed = new StringBuilder(candidate).reverse().toString();

      int sum = 0;

      for (int i = 0; i < reversed.length(); i++) {
        char c = reversed.charAt(i);
        int value = Character.digit(c, 10);
        if (i % 2 == 1) {
          // Double every second digit
          value *= 2;
          // If the result is greater than 9, subtract 9
          if (value > 9) {
            value -= 9;
          }
        }
        // Add the value to the sum
        sum += value;
      }

      // Return true if the sum is a multiple of 10
      return sum % 10 == 0;
    }

}

