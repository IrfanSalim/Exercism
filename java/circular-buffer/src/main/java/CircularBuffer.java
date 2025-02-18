// import java.util.ArrayDeque;
// import java.util.Deque;
// import java.util.Optional;

// public class CircularBuffer<E> {
//     private final Deque<E> deque = new ArrayDeque<>();
//     private final int size;

//     public CircularBuffer(int size) {
//         this.size = size;
//     }

//     public E read() throws BufferIOException {
//         E first = deque.pollFirst();
//         return Optional.ofNullable(first)
//                 .orElseThrow(() -> new BufferIOException("Tried to read from empty buffer"));
//     }

//     public void write(E element) throws BufferIOException {
//         if (deque.size() == size)
//             throw new BufferIOException("Tried to write to full buffer");

//         deque.addLast(element);
//     }

//     public void clear() {
//         deque.clear();
//     }

//     public void overwrite(E element) {
//         if (deque.size() == size)
//             deque.removeFirst();

//         deque.addLast(element);
//     }
// }

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

class CircularBuffer<T> {

    private final Queue<T> buffer;

    CircularBuffer(final int size) {
        buffer = new ArrayBlockingQueue<>(size);
    }

    T read() throws BufferIOException {
        if (buffer.isEmpty()) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        return buffer.poll();
    }

    void write(T data) throws BufferIOException {
        if (!buffer.offer(data)) {
            throw new BufferIOException("Tried to write to full buffer");
        }
    }

    void overwrite(T data) {
        while (!buffer.offer(data)) {
            buffer.poll();
        }
    }

    void clear() {
        buffer.clear();
    }

}