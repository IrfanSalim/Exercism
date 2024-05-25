class ReverseString {

    String reverse(String inputString) {
      int n = inputString.length();
      StringBuilder reversedString = new StringBuilder();
      for (int i = n-1; i >= 0; i--) {
        reversedString.append(inputString.charAt(i));
      }
      return reversedString.toString();
    }
  
}

class ReverseString {

    String reverse(String inputString) {
        return new StringBuilder(inputString).reverse().toString();
    }
  
}
