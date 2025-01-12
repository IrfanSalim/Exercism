import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        List<Signal> handshakes = new ArrayList<Signal>();
        if ((number & 1 << 0) != 0)
            handshakes.add(Signal.WINK);
        if ((number & 1 << 1) != 0)
            handshakes.add(Signal.DOUBLE_BLINK);
        if ((number & 1 << 2) != 0)
            handshakes.add(Signal.CLOSE_YOUR_EYES);
        if ((number & 1 << 3) != 0)
            handshakes.add(Signal.JUMP);
        if ((number & 1 << 4) != 0)
            Collections.reverse(handshakes);

        return handshakes;
    }

}
