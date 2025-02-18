import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class CircularBuffer<E> {
    private final Deque<E> deque = new ArrayDeque<>();
    private final int size;

    public CircularBuffer(int size) {
        this.size = size;
    }

    public E read() throws BufferIOException {
        E first = deque.pollFirst();
        return Optional.ofNullable(first)
                .orElseThrow(() -> new BufferIOException("Tried to read from empty buffer"));
    }

    public void write(E element) throws BufferIOException {
        if (deque.size() == size)
            throw new BufferIOException("Tried to write to full buffer");

        deque.addLast(element);
    }

    public void clear() {
        deque.clear();
    }

    public void overwrite(E element) {
        if (deque.size() == size)
            deque.removeFirst();

        deque.addLast(element);
    }
}