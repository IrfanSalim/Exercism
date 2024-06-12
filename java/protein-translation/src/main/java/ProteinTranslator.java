import java.util.*;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
      HashMap<String, String> codons = new HashMap<>();
      codons.put("AUG", "Methionine");
      codons.put("UUU", "Phenylalanine");
      codons.put("UUC", "Phenylalanine");
      codons.put("UUA", "Leucine");
      codons.put("UUG", "Leucine");
      codons.put("UCU", "Serine");
      codons.put("UCC", "Serine");
      codons.put("UCA", "Serine");
      codons.put("UCG", "Serine");
      codons.put("UAU", "Tyrosine");
      codons.put("UAC", "Tyrosine");
      codons.put("UGU", "Cysteine");
      codons.put("UGC", "Cysteine");
      codons.put("UGG", "Tryptophan");
      codons.put("UAA", "STOP");
      codons.put("UAG", "STOP");
      codons.put("UGA", "STOP");

      List<String> protein = new ArrayList<>();

      for (int i = 0; i < rnaSequence.length(); i += 3) {
        if (i + 3 > rnaSequence.length()) throw new IllegalArgumentException("Invalid codon");
        String codon = rnaSequence.substring(i, i + 3);
        if (codons.containsKey(codon) == false) {
          throw new IllegalArgumentException("Invalid codon");
        } else if (codons.get(codon) == "STOP") {
          return protein;
        } else {
          protein.add(codons.get(codon));
        }
      }

      return protein;
    }
}
