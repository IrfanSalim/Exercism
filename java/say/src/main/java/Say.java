class Say {

    static String[] under_twenty = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", 
                                    "eighteen", "nineteen"};

    static String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};


    String say(long number)  {

        if (number < 0) {
            throw new IllegalArgumentException("must be positive number");
        }

        if (number < 20) {
            return under_twenty[(int)number];
        } 

        if (number % 10 == 0 && number < 100)  {
            int n = ((int)number - 20) / 10;
            return tens[n];
        }

        if (number >= 21 && number < 100) {
            return say(number/10 * 10) + "-" + say(number % 10);
        }

        if (number < 1_000) {
            return sufix(number, 100, "hundred");
        }

        if (number < 1_000_000) {
            return sufix(number, 1_000, "thousand");
        }

        if (number < 1_000_000_000) {
            return sufix(number, 1_000_000, "million");
        }

        if (number < 1_000_000_000_000L) {
            return sufix(number, 1_000_000_000, "billion");
        }

        throw new IllegalArgumentException("not supported");
    }

    private String sufix(long number, long modulo, String suffix) {
        var a = number / modulo;
        var b = number % modulo;
        if (b == 0) {
            return say(a) + " " + suffix;
        } else {
            return say(a) + " " + suffix + " " + say(b);
        }
    }
}



import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

public class Say {

	private static final long MAX_NUMBER = 999_999_999_999L;
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String HYPHEN = "-";

	private static final NavigableMap<Long, String> NUMBERS = new TreeMap<>(ofEntries(
		entry(0L, "zero"),
		entry(1L, "one"),
		entry(2L, "two"),
		entry(3L, "three"),
		entry(4L, "four"),
		entry(5L, "five"),
		entry(6L, "six"),
		entry(7L, "seven"),
		entry(8L, "eight"),
		entry(9L, "nine"),
		entry(10L, "ten"),
		entry(11L, "eleven"),
		entry(12L, "twelve"),
		entry(13L, "thirteen"),
		entry(14L, "fourteen"),
		entry(15L, "fifteen"),
		entry(16L, "sixteen"),
		entry(17L, "seventeen"),
		entry(18L, "eighteen"),
		entry(19L, "nineteen"),
		entry(20L, "twenty"),
		entry(30L, "thirty"),
		entry(40L, "forty"),
		entry(50L, "fifty"),
		entry(60L, "sixty"),
		entry(70L, "seventy"),
		entry(80L, "eighty"),
		entry(90L, "ninety"),
		entry(100L, "hundred"),
		entry(1000L, "thousand"),
		entry(1000_000L, "million"),
		entry(1000_000_000L, "billion"))
	);

	public String say(final long number) {

		if (number < 0 || number > MAX_NUMBER) {
			throw new IllegalArgumentException();
		}

		if (number < 100) {
			return Optional.ofNullable(NUMBERS.get(number))
				.orElseGet(() -> NUMBERS.floorEntry(number).getValue() + HYPHEN + this.say(number % 10));
		}

		return this.say(number / NUMBERS.floorKey(number)) + SPACE
			+ Optional.ofNullable(NUMBERS.get(number)).orElseGet(() -> NUMBERS.floorEntry(number).getValue()
			+ (number % NUMBERS.floorKey(number) > 0 ? SPACE + this.say(number % NUMBERS.floorKey(number)) : EMPTY));
	}
}
