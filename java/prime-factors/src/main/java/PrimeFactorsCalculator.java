import java.util.*;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> result = new ArrayList<Long>();
        if (number <= 1) return result; // Early return for numbers <= 1
        List<Long> primes = getPrimeNumbers((long) Math.sqrt(number) + 1);
        for (Long prime : primes) {
            while (number % prime == 0) {
                result.add(prime);
                number /= prime;
            }
        }
        if (number > 1) result.add(number); // If number is prime itself
        return result;
    }

    private List<Long> getPrimeNumbers(Long max) {
        List<Long> result = new ArrayList<Long>();
        Boolean[] primes = new Boolean[max.intValue() + 1];
        Arrays.fill(primes, true);

        for (int i = 2; i <= max; i++) {
            if (primes[i]) {
                for (int j = i * 2; j <= max; j += i) { // Start from i*2 and increment by i
                    primes[j] = false;
                }
                result.add((long) i);
            }
        }

        return result;
    }

}