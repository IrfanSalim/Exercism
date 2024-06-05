class Acronym {
    String acr = "";

    Acronym(String phrase) {
      phrase = phrase.trim();
      String[] words = phrase.replaceAll("[^a-zA-Z0-9\s-]", "").split("[\\s-]+");
      for (String word : words) {
        this.acr += word.substring(0, 1).toUpperCase();
      }
    }

    String get() {
      return this.acr;
    }

}
