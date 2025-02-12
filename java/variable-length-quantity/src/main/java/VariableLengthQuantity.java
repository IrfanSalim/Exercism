import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> res = new ArrayList<>();
        for (long n : numbers) {
            List<String> tmp = new ArrayList<>();
            tmp.add(String.format("0x%x", n & 0x7f));
            n >>= 7;
            while (n != 0) {
                tmp.add(String.format("0x%x", n & 0x7f | 0x80));
                n >>= 7;
            }
            Collections.reverse(tmp);
            res.addAll(tmp);
        }
        return res;
    }

    List<String> decode(List<Long> bytes) {
        if ((bytes.get(bytes.size() - 1) & 0x80) != 0)
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        List<String> res = new ArrayList<>();
        long nbr = 0L;
        for (Long n : bytes) {
            nbr <<= 7;
            nbr += n & 0x7f;
            if ((n & 0x80) == 0) {
                res.add(String.format("0x%x", nbr));
                nbr = 0L;
            }
        }
        return res;
    }
}