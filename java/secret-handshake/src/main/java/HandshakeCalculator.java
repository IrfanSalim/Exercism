// import java.util.List;
// import java.util.ArrayList;
// import java.util.Collections;

// class HandshakeCalculator {

//     List<Signal> calculateHandshake(int number) {
//         List<Signal> handshakes = new ArrayList<Signal>();
//         if ((number & 1 << 0) != 0)
//             handshakes.add(Signal.WINK);
//         if ((number & 1 << 1) != 0)
//             handshakes.add(Signal.DOUBLE_BLINK);
//         if ((number & 1 << 2) != 0)
//             handshakes.add(Signal.CLOSE_YOUR_EYES);
//         if ((number & 1 << 3) != 0)
//             handshakes.add(Signal.JUMP);
//         if ((number & 1 << 4) != 0)
//             Collections.reverse(handshakes);

//         return handshakes;
//     }

// }

import java.util.Collections;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class HandshakeCalculator {
    public List<Signal> calculateHandshake(int n) {
        IntPredicate isBitOn = bitIndex -> ((1 << bitIndex) & n) > 0;
        List<Signal> signals = IntStream.range(0, Signal.values().length)
                .filter(isBitOn)
                .mapToObj(i -> Signal.values()[i])
                .collect(Collectors.toList());

        if (isBitOn.test(Signal.values().length)) {
            Collections.reverse(signals);
        }

        return signals;
    }
}

enum Signal {
    WINK, DOUBLE_BLINK, CLOSE_YOUR_EYES, JUMP
}