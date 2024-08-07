import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    String sequence;
    NucleotideCounter(String sequence) {
        this.sequence = sequence.toUpperCase();
        if(!this.sequence.matches("[ACGT]*"))
        {
            throw new IllegalArgumentException();
        }    
    }

    Map<Character, Integer> nucleotideCounts() {
        Map<Character, Integer> nucleotideCounts = new HashMap<>();
        nucleotideCounts.put('A', 0);
        nucleotideCounts.put('T', 0);
        nucleotideCounts.put('C', 0);
        nucleotideCounts.put('G', 0);

        for (char nucleotide : sequence.toCharArray()) {
            nucleotideCounts.put(nucleotide, nucleotideCounts.get(nucleotide) + 1);
        }
        return nucleotideCounts;
    }

}

