// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// class VariableLengthQuantity {

//     List<String> encode(List<Long> numbers) {
//         List<String> res = new ArrayList<>();
//         for (long n : numbers) {
//             List<String> tmp = new ArrayList<>();
//             tmp.add(String.format("0x%x", n & 0x7f));
//             n >>= 7;
//             while (n != 0) {
//                 tmp.add(String.format("0x%x", n & 0x7f | 0x80));
//                 n >>= 7;
//             }
//             Collections.reverse(tmp);
//             res.addAll(tmp);
//         }
//         return res;
//     }

//     List<String> decode(List<Long> bytes) {
//         if ((bytes.get(bytes.size() - 1) & 0x80) != 0)
//             throw new IllegalArgumentException("Invalid variable-length quantity encoding");
//         List<String> res = new ArrayList<>();
//         long nbr = 0L;
//         for (Long n : bytes) {
//             nbr <<= 7;
//             nbr += n & 0x7f;
//             if ((n & 0x80) == 0) {
//                 res.add(String.format("0x%x", nbr));
//                 nbr = 0L;
//             }
//         }
//         return res;
//     }
// }

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class VariableLengthQuantity {
    List<String> encode(List<Long> numbers) {
        return numbers.stream()
                .flatMap(number -> encodeOneNumber(number).stream())
                .collect(Collectors.toList());
    }

    List<String> encodeOneNumber(long number) {
        List<String> result = new ArrayList<>();
        String s = Long.toBinaryString(number);
        for (int i = 0; i < s.length(); i += 7) {
            result.add(0, "0x" + Integer.toHexString(
                    Integer.parseInt(s.substring(Math.max(0, s.length() - i - 7), s.length() - i), 2)
                            + ((i == 0) ? 0 : (1 << 7))));
        }
        return result;
    }

    List<String> decode(List<Long> bytes) {
        if (bytes.get(bytes.size() - 1) >= 1 << 7) {
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }

        List<String> result = new ArrayList<>();
        int beginIndex = 0;
        for (int i = 0; i < bytes.size(); i++) {
            if (bytes.get(i) < 1 << 7) {
                result.add(decodeOneNumber(bytes.subList(beginIndex, i + 1)));
                beginIndex = i + 1;
            }
        }
        return result;
    }

    String decodeOneNumber(List<Long> bytes) {
        return "0x" + Long.toHexString(Long.parseLong(String.join("", bytes.stream()
                .map(b -> String.format("%7s", Long.toBinaryString(b % (1 << 7)))
                        .replace(' ', '0'))
                .collect(Collectors.toList())), 2));
    }
}