import java.util.ArrayList;
import java.util.List;

class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1)
            throw new IllegalArgumentException();
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        primeList.add(3);

        for (int i = 5; primeList.size() < nth; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0)
                    isPrime = false;
            }
            if (isPrime)
                primeList.add(i);
        }
        return primeList.get(nth - 1);
    }

}