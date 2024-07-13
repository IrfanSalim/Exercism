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


import java.util.stream.IntStream;

public class LuhnValidator {

    public boolean isValid(String str) {

        String s = str.trim().replaceAll(" ", "");
        String[] nums = s.split("");
        return s.matches("^\\d{2,}") && total(nums) % 10 == 0;
    }

    private int total(String[] nums) {
        int startDigit = (nums.length & 1) == 0 ? 0 : 1;
        return IntStream.range(0, nums.length)
                .map(i -> (i & 1) == startDigit ? doubleDigit(Integer.parseInt(nums[i])) : Integer.parseInt(nums[i]))
                .sum();
    }

    private int doubleDigit(int x) {
        if (x * 2 > 9) return x * 2 - 9;
        return x * 2;
    }
}
