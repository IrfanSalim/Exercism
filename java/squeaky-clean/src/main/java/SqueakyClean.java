class SqueakyClean {
    static String clean(String identifier) {
      String clean = identifier.replaceAll("[^a-zA-Z0-9]", "_");
      if (clean.length() == 0) {
        return "a";
      }
      return clean;
    }
}
