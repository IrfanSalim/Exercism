import java.util.Map;

public class PhoneNumber {
    private static Map<Character, String> codes = Map.of('0', "zero", '1', "one");
    private String number;

    public PhoneNumber(String num) {
        this.number = num.replaceAll("[()\\-.+\\s]", "");
        if (number.matches("^\\d*\\p{IsLatin}+\\d*$"))
            throw new IllegalArgumentException("letters not permitted");
        if (number.matches("^\\d*\\p{Punct}+\\d*$"))
            throw new IllegalArgumentException("punctuations not permitted");
        if (number.length() == 11) {
            if (!number.startsWith("1"))
                throw new IllegalArgumentException("11 digits must start with 1");
            number = number.substring(1);
        }
        if (number.length() > 11)
            throw new IllegalArgumentException("must not be greater than 11 digits");
        if (number.length() < 10)
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        if (!number.matches("\\d{3}[2-9]\\d+"))
            throw new IllegalArgumentException("exchange code cannot start with %s".formatted(codes.get(number.charAt(3))));
        if (!number.matches("[2-9]\\d+"))
            throw new IllegalArgumentException("area code cannot start with %s".formatted(codes.get(number.charAt(0))));
    }

    public String getNumber() {
        return number;
    }
}


class PhoneNumber {
	private String number;

	public PhoneNumber(String rawNumber) {
		number = rawNumber.replaceAll("[ ()+-.]", "");

		if (number.matches(".*[a-zA-Z]+.*")) throw new IllegalArgumentException("letters not permitted");
		if (number.matches(".*\\D+.*")) throw new IllegalArgumentException("punctuations not permitted");

		if (number.length() > 11) throw new IllegalArgumentException("more than 11 digits");
		if (number.length() == 11) {
			if (!number.startsWith("1")) throw new IllegalArgumentException("11 digits must start with 1");
			number = number.substring(1);
		}
		if (number.length() < 10) throw new IllegalArgumentException("incorrect number of digits");

		if (number.startsWith("0")) throw new IllegalArgumentException("area code cannot start with zero");
		if (number.startsWith("1")) throw new IllegalArgumentException("area code cannot start with one");
		if (number.charAt(3) == '0') throw new IllegalArgumentException("exchange code cannot start with zero");
		if (number.charAt(3) == '1') throw new IllegalArgumentException("exchange code cannot start with one");
	}

	public String getNumber() {
		return number;
	}
}



