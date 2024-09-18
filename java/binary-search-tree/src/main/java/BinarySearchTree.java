import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root = null;
    void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            
        }
    }

    List<T> getAsSortedList() {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    List<T> getAsLevelOrderList() {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    Node<T> getRoot() {
        throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
    }

    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        Node<T> getLeft() {
            throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        }

        Node<T> getRight() {
            throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        }

        T getData() {
            throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        }

    }
}
