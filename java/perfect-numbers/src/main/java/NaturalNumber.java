import java.util.stream.IntStream;

class NaturalNumber {

    int number;

    NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }

        this.number = number;
    }

    Classification getClassification() {
        int aliquotSum = IntStream.rangeClosed(1, this.number / 2).parallel()
                .filter(i -> this.number % i == 0)
                .sum();

        if (aliquotSum < number) {
            return Classification.DEFICIENT;
        } else if (aliquotSum > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.PERFECT;
        }
    }

}