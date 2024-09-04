import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Sieve {
    int maxPrime;

    Sieve(int maxPrime) {
      this.maxPrime = maxPrime;
    }

    List<Integer> getPrimes() {
      List<Integer> primes = new ArrayList<>();
      boolean[] sieve = new boolean[maxPrime + 1];
      Arrays.fill(sieve, true);
      for (int i = 2; i <= maxPrime; i++) {
        if (sieve[i]) {
          primes.add(i);
          for (int j = i + i; j <= maxPrime; j += i) {
            sieve[j] = false;
          }
        }
      }
      return primes;
    }
}

import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.lang.Math;
import java.util.List;
public class Sieve{
  
  final int limit;
  public Sieve(int limit){
    this.limit = limit;
  }
  public List<Integer> getPrimes(){
    return IntStream.rangeClosed(2, limit).filter(Sieve::isPrime).boxed().collect(Collectors.toList());
  }
  public static boolean isPrime(int number){
    return IntStream.rangeClosed(2, (int) Math.sqrt(number)).allMatch(n -> number % n != 0);
  }
}
