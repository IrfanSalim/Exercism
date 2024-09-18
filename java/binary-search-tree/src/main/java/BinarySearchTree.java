import java.util.*;


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
        List<T> result = new ArrayList<>();
        get(root, result);
        return result;
    }

    private void get(Node<T> node, List<T> result) {
        if (node == null) return;

        get(node.getLeft(), result);
        result.add(node.getData());
        get(node.getRight(), result);
    }

    List<T> getAsLevelOrderList() {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        List<T> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node<T> pos = queue.poll();
            res.add((T) pos.getData());
            if (pos.getLeft() != null) queue.offer(pos.getLeft());
            if (pos.getRight() != null) queue.offer(pos.getRight());
        }
        return res;
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
