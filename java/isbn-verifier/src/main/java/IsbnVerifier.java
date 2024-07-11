class IsbnVerifier {

    boolean isValid(String stringToVerify) {
      int sum = 0;
      int multiplier = 10;
      for (int i = 0; i < stringToVerify.length(); i++) {
        if (Character.isDigit(stringToVerify.charAt(i))) {
          sum += (Integer.parseInt(String.valueOf(stringToVerify.charAt(i))) * multiplier);
        } else if (stringToVerify.charAt(i) == 'X') {
          sum += 10 * multiplier;
        }
        multiplier--;
    }
    
    return sum % 11 == 0;
  }
}
