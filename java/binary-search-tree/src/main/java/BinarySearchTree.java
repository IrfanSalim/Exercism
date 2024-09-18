import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root = null;
    void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root = put(root, value);
        }
    }

    private Node<T> put(Node<T> pos, T value) {
        if (pos == null) return new Node<>(value);
        int cmp = value.compareTo((T) pos.getData());
        if (cmp > 0) pos.right = put(pos.right, value);
        else if (cmp <= 0) pos.left = put(pos.left, value);
        return pos;
    }

    List<T> getAsSortedList() {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    List<T> getAsLevelOrderList() {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        T data;
        Node<T> left = null;
        Node<T> right = null;

        public Node(T data) {
            this.data = data;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }
}
