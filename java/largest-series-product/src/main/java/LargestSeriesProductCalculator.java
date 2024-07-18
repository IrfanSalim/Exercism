class LargestSeriesProductCalculator {

    String inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {
        if (inputNumber == null) {
            throw new IllegalArgumentException("Input number cannot be null.");
        }
        if (inputNumber.matches(".*\\D.*")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits > inputNumber.length()) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (numberOfDigits == 0) {
            return 1;
        }

        long largestProduct = 0;

        for (int i = 0; i <= inputNumber.length() - numberOfDigits; i++) {
            long product = 1;
            for (int j = 0; j < numberOfDigits; j++) {
                product *= inputNumber.charAt(i + j) - '0';
            }
            if (product > largestProduct) {
                largestProduct = product;
            }
        }

        return largestProduct;
    }
}

