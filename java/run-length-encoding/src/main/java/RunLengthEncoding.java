// import java.util.regex.*;

// public class RunLengthEncoding {
//     public String encode(String text) {
//         StringBuilder builder = new StringBuilder();

//         if (text.length() == 0) {
//             return "";
//         }

//         char current = text.charAt(0);
//         int group = 1;

//         for (int i = 1; i < text.length(); i++) {
//             if (text.charAt(i) != current) {
//                 if (group > 1) {
//                     builder.append(group);
//                 }
//                 builder.append(current);
//                 current = text.charAt(i);
//                 group = 1;
//             } else {
//                 group++;
//             }

//         }
//         if (group > 1) {
//             builder.append(group);
//         }
//         builder.append(current);

//         return builder.toString();
//     }

//     public String decode(String text) {
//         StringBuilder builder = new StringBuilder();
//         Matcher m = Pattern.compile("(\\d*)(\\D)").matcher(text);
//         while (m.find()) {
//             if (!m.group(1).isEmpty()) {
//                 int size = Integer.parseInt(m.group(1));
//                 char c = m.group(2).charAt(0);
//                 for (int i = 0; i < size; i++) {
//                     builder.append(c);
//                 }
//             } else {
//                 builder.append(m.group());
//             }
//         }

//         return builder.toString();
//     }
// }

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

record LetterAndAmount(char letter, int amount) {
}

public class RunLengthEncoding {
    public String encode(String input) {
        if (input.equals(""))
            return "";

        ArrayList<LetterAndAmount> letterAndAmounts = new ArrayList<>();
        {
            char prevLetter = input.charAt(0);
            int letterCount = 1;

            for (char letter : input.substring(1).toCharArray()) {
                if (letter == prevLetter) {
                    letterCount++;
                } else {
                    letterAndAmounts.add(new LetterAndAmount(prevLetter, letterCount));
                    prevLetter = letter;
                    letterCount = 1;
                }
            }

            letterAndAmounts.add(new LetterAndAmount(prevLetter, letterCount));
        }

        return letterAndAmounts
                .stream()
                .map(letterAndAmount -> (letterAndAmount.amount() > 1 ? Integer.toString(letterAndAmount.amount()) : "")
                        + letterAndAmount.letter())
                .collect(Collectors.joining(""));
    }

    public String decode(String input) {
        return Arrays.stream(input.split("(?<=[ a-zA-Z])"))
                .map(stringLetterAndAmount -> stringLetterAndAmount.split("(?=[ a-zA-Z])"))
                .map(stringArrayLetterAndAmount -> stringArrayLetterAndAmount.length == 1
                        ? stringArrayLetterAndAmount[0]
                        : stringArrayLetterAndAmount[1].repeat(Integer.parseInt(stringArrayLetterAndAmount[0])))
                .collect(Collectors.joining());
    }
}