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
