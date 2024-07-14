class Proverb {
    String[] words;
    
    Proverb(String[] words) {
      this.words = words;
    }

    String recite() {
      if (words.length == 0) return "";

      String proverb = "";
      for (int i = 0; i < words.length - 1; i++) {
       proverb += "For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n";
      }
      return proverb + "And all for the want of a " + words[0] + ".";
    }

}
