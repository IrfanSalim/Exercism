class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;

    public DoublyLinkedList() {
        this.head = new Element<>(null, null, tail);
        this.tail = new Element<>(null, head, null);
    }

    void push(T value) {
        Element<T> node = new Element<>(value, this.tail.prev, this.tail);
        this.tail.prev.next = node;
        this.tail.prev = node;
    }

    T pop() {
        Element<T> node = this.tail.prev;
        this.tail.prev = node.prev;
        this.tail.prev.next = this.tail;
        return (T) node.value;
    }

    void unshift(T value) {
        throw new UnsupportedOperationException("Please implement the DoublyLinkedList.unshift() method.");
    }

    T shift() {
        throw new UnsupportedOperationException("Please implement the DoublyLinkedList.shift() method.");
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
