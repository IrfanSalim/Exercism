import java.util.HashMap;

class RnaTranscription {

    String transcribe(String dnaStrand) {
      HashMap<Character, Character> dnaToRna = new HashMap<>();
      dnaToRna.put('G', 'C');
      dnaToRna.put('C', 'G');
      dnaToRna.put('T', 'A');
      dnaToRna.put('A', 'U');

      StringBuilder rna = new StringBuilder();

      for (Character c: dnaStrand.toCharArray()) {
        if (dnaToRna.containsKey(c)) {
          rna.append(dnaToRna.get(c));
        } else rna.append(c);
      }

      return rna.toString();
    }

}
