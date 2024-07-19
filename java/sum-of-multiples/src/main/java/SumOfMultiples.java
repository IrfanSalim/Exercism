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
