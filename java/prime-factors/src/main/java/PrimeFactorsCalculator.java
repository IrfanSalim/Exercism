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

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class PrimeFactorsCalculator {

	List<Long> calculatePrimeFactorsOf(final long number) {
		return number < 2
			? Collections.emptyList()
			: this.concatFactor(this.smallestFactor(number), number).collect(toList());
	}

	private Stream<Long> concatFactor(final long factor, final long number) {
		return Stream.concat(Stream.of(factor), this.calculatePrimeFactorsOf(number / factor).stream());
	}

	private Long smallestFactor(final Long number) {
		return LongStream.range(2, number).filter(n -> number % n == 0).findFirst().orElse(number);

	}
}

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorsCalculator {
    
    public List<Long> calculatePrimeFactorsOf(long input) {
        List<Long> list = new ArrayList<>();
        long i = 2;
        while (i <= input) {
            if (input % i == 0) {
                list.add(i);
                input = input / i;
            } else {
                i++;
            }
        }
        return list;
    }
    
}