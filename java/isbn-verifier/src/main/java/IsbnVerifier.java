class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        // Remove hyphens
        String stringToVerifyWithoutHyphens = stringToVerify.replaceAll("-", "");
        
        // ISBN must be 10 characters long
        if (stringToVerifyWithoutHyphens.length() != 10) {
            return false;
        }
        
        int sum = 0;
        int multiplier = 10;
        
        // Validate each character
        for (int i = 0; i < stringToVerifyWithoutHyphens.length(); i++) {
            char c = stringToVerifyWithoutHyphens.charAt(i);
            
            if (Character.isDigit(c)) {
                sum += (c - '0') * multiplier;
            } else if (c == 'X' && i == 9) { // 'X' is allowed only in the last position
                sum += 10 * multiplier;
            } else {
                return false; // Invalid character
            }
            multiplier--;
        }
        
        // Check if the sum is divisible by 11
        return sum % 11 == 0;
    }
}



import java.util.stream.IntStream;

class IsbnVerifier {

    public boolean isValid(String s) {
        String scrubbed = s.replace("-", "");

        return scrubbed.matches("^([0-9]{10}|[0-9]{9}X)$") &&
            IntStream.range(0, scrubbed.length())
            .map(pos -> {
                var chr = scrubbed.charAt(pos);
                return (chr != 'X' ? chr - '0' : 10) * (10 - pos);
            })
            .sum() % 11 == 0;
    }
}



class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        var acc = new IsbnAcc();
        stringToVerify.chars().forEach(acc::calc);
        return acc.isIsbn();
    }

}

class IsbnAcc {
    int sum = 0;
    int pos = 0;
    boolean allValidChars = true;

    boolean isIsbn() {
        return allValidChars && pos == 10 && sum % 11 == 0;
    }

    void calc(int codePoint) {
        if (Character.isDigit(codePoint)) {
            sum += (codePoint - '0') * (10 - pos);
            pos++;
            return;
        }
        if (codePoint == '-')
            return;
        if (codePoint == 'X' && pos == 9) {
            sum += 10;
            pos++;
            return;
        }
        allValidChars = false;
    }
}
