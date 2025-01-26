import java.util.List;
import java.util.LinkedList;

public class Satellite {

    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        if (preorder.size() != inorder.size())
            throw new IllegalArgumentException("traversals must have the same length");

        if (preorder.size() != preorder.stream().distinct().count())
            throw new IllegalArgumentException("traversals must contain unique items");

        if (!preorder.containsAll(inorder) || !inorder.containsAll(preorder))
            throw new IllegalArgumentException("traversals must have the same elements");

        List<Character> pre = new LinkedList<>();
        for (int i = 0; i < preorder.size(); i++) {
            pre.add(preorder.get(i));
        }

        return new Tree(from(pre, inorder, -1));
    }

    private Node from(List<Character> preorder, List<Character> inorder, int parentIn) {
        if (preorder.isEmpty())
            return null;

        char value = preorder.get(0);
        Node left = null, right = null;

        int inorderIndex = inorder.indexOf(value);
        preorder.remove(0);

        if (inorderIndex - parentIn > 1)
            left = from(preorder, inorder, inorderIndex);

        if (parentIn - inorderIndex < -1)
            right = from(preorder, inorder, inorderIndex);

        return new Node(value, left, right);
    }

}