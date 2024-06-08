public class Hamming {

    int hammingDistance = 0;

    public Hamming(String leftStrand, String rightStrand) {
      if (leftStrand.length() != rightStrand.length()) 
        throw new IllegalArgumentException("strands must be of equal length");
      
      for (int i = 0; i < leftStrand.length(); i++) {
        if (leftStrand.charAt(i) != rightStrand.charAt(i)) 
          this.hammingDistance++;
      }
    }

    public int getHammingDistance() {
      return this.hammingDistance;
    }
}


import java.util.stream.IntStream;

public class Hamming {

    final private long difference;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        difference = IntStream
            .range(0, leftStrand.length())
            .filter(i -> leftStrand.charAt(i) != rightStrand.charAt(i))
            .count();
    }

    public long getHammingDistance() {
        return difference;
    }
}


import java.util.stream.IntStream;

public class Hamming {

    final private int difference;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        difference = IntStream
            .range(0, leftStrand.length())
            .map(i -> leftStrand.charAt(i) != rightStrand.charAt(i) ? 1 : 0)
            .sum();
    }

    public int getHammingDistance() {
        return difference;
    }
}


import java.util.stream.IntStream;

public class Hamming {

    final private int difference;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        difference = IntStream.range(0, leftStrand.length()).reduce(0,
            (hamcount, index) -> hamcount + (leftStrand.charAt(index) != rightStrand.charAt(index) ? 1 : 0));
    }

    public int getHammingDistance() {
        return difference;
    }
}
