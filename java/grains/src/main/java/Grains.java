import java.math.BigInteger;

class Grains {
    BigInteger[] squares = new BigInteger[64];
    BigInteger sum = BigInteger.ZERO;

    public Grains() {
        BigInteger count = BigInteger.ONE;
        for (int i = 0; i < 64; i++) {
            squares[i] = count;
            sum = sum.add(count);
            count = count.multiply(BigInteger.TWO);
        }
    }

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64) {
          throw new IllegalArgumentException("square must be between 1 and 64");
        }
        return squares[square - 1];
    }

    BigInteger grainsOnBoard() {
        return sum;
    }
}

import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        if ((square <= 0) || (square >= 65)) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
        return BigInteger.valueOf(2).pow(square - 1);
    }

    BigInteger grainsOnBoard() {
        return BigInteger.valueOf(2).pow(64).subtract(BigInteger.ONE);
    }
}
