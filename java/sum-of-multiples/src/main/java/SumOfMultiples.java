import java.util.HashSet;
import java.util.Set;

public class SumOfMultiples {
    private final int limit;
    private final int[] factors;

    public SumOfMultiples(int limit, int[] factors) {
        this.limit = limit;
        this.factors = factors;
    }

    public int getSum() {
        Set<Integer> multiples = new HashSet<>();
        for (int factor : factors) {
            if (factor != 0) {
                for (int i = factor; i < limit; i += factor) {
                    multiples.add(i);
                }
            }
        }
        return multiples.stream().mapToInt(Integer::intValue).sum();
    }
}


import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

class SumOfMultiples {

    private final int topNumber;
    private final int[] factors;

    SumOfMultiples(int topNumber, int[] factors) {

        this.topNumber = topNumber;
        this.factors = factors;
    }

    int getSum() {
        return range(0, topNumber)
                .filter(number -> stream(factors).anyMatch(factor -> factor > 0 && number % factor == 0))
                .sum();
    }
}
