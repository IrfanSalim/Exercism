import java.util.Objects;

class Zipper {
    private int val;
    Zipper up;
    Zipper left;
    Zipper right;

    Zipper(int val) {
        this.val = val;
        this.up = null;
        this.left = null;
        this.right = null;
    }

    BinaryTree toTree() {
        Zipper root = this;
        while (root.up != null)
            root = root.up;
        return new BinaryTree(root);
    }

    int getValue() {
        return this.val;
    }

    Zipper setLeft(Zipper leftChild) {
        if (leftChild != null) {
            this.left = leftChild;
            leftChild.up = this;
            return this;
        }

        this.left = null;
        return this;
    }

    Zipper setRight(Zipper rightChild) {
        if (rightChild != null) {
            this.right = rightChild;
            rightChild.up = this;
            return this;
        }
        this.right = null;
        return this;
    }

    void setValue(int val) {
        this.val = val;
    }

    public String printTree() {
        String result = "value: " + this.val + ", ";
        if (this.left != null)
            result += "left: { " + this.left.printTree() + " }, ";
        else
            result += "left: null, ";
        if (this.right != null)
            result += "right: { " + this.right.printTree() + " }";
        else
            result += "right: null";
        return result;
    }
}

class BinaryTree {

    public Zipper root;

    BinaryTree(int value) {
        this.root = new Zipper(value);
    }

    BinaryTree(Zipper root) {
        this.root = root;
    }

    Zipper getRoot() {
        return this.root;
    }

    String printTree() {
        return root.printTree();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BinaryTree that = (BinaryTree) obj;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}