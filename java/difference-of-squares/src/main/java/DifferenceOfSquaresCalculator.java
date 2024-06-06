class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
      int sum = 0;
      for (int i = 1; i <= input; i++) {
        sum += i;
      }
      return sum * sum;
    }

    int computeSumOfSquaresTo(int input) {
      int sum = 0;
      for (int i = 1; i <= input; i++) {
        sum += i * i;
      }
      return sum;
    }

    int computeDifferenceOfSquares(int input) {
      return this.computeSquareOfSumTo(input) - this.computeSumOfSquaresTo(input);
    }

}


import java.util.stream.IntStream;

class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = IntStream.rangeClosed(1, input).sum();
        return sum * sum;
    }
    
    int computeSumOfSquaresTo(int input) {
        return IntStream.rangeClosed(1, input)
            .map(num -> num * num)
            .sum();
    }
    
    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }
}


class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = (input * (input + 1)) / 2;
        return sum * sum;
    }

    int computeSumOfSquaresTo(int input) {
        return (input * (input + 1) * ((input * 2) + 1)) / 6;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }

}
