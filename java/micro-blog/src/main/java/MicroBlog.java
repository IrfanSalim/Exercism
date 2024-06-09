class MicroBlog {
    public String truncate(String input) {
      int usableLength = Math.min(input.codePointCount(0, input.length()), 5);
      return input.substring(0, input.offsetByCodePoints(0, usableLength));
    }
}
