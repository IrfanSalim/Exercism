// import java.util.List;
// import java.util.LinkedList;

// public class Satellite {

//     public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
//         if (preorder.size() != inorder.size())
//             throw new IllegalArgumentException("traversals must have the same length");

//         if (preorder.size() != preorder.stream().distinct().count())
//             throw new IllegalArgumentException("traversals must contain unique items");

//         if (!preorder.containsAll(inorder) || !inorder.containsAll(preorder))
//             throw new IllegalArgumentException("traversals must have the same elements");

//         List<Character> pre = new LinkedList<>();
//         for (int i = 0; i < preorder.size(); i++) {
//             pre.add(preorder.get(i));
//         }

//         return new Tree(from(pre, inorder, -1));
//     }

//     private Node from(List<Character> preorder, List<Character> inorder, int parentIn) {
//         if (preorder.isEmpty())
//             return null;

//         char value = preorder.get(0);
//         Node left = null, right = null;

//         int inorderIndex = inorder.indexOf(value);
//         preorder.remove(0);

//         if (inorderIndex - parentIn > 1)
//             left = from(preorder, inorder, inorderIndex);

//         if (parentIn - inorderIndex < -1)
//             right = from(preorder, inorder, inorderIndex);

//         return new Node(value, left, right);
//     }

// }

import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Satellite {
    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        if (preorder.size() != inorder.size())
            throw new IllegalArgumentException("traversals must have the same length");
        if (!preorder.stream().collect(toSet()).equals(inorder.stream().collect(toSet())))
            throw new IllegalArgumentException("traversals must have the same elements");
        if (preorder.stream().collect(toSet()).size() != preorder.size())
            throw new IllegalArgumentException("traversals must contain unique items");
        return new Tree(build(preorder, inorder));
    }

    private Node build(List<Character> preorder, List<Character> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }
        char head = preorder.get(0);
        var leftNodes = inorder.stream().takeWhile(c -> c != head).collect(toList());
        var rightNodes = inorder.stream().dropWhile(c -> c != head).skip(1).collect(toList());
        var preorderLeft = preorder.stream().filter(leftNodes::contains).collect(toList());
        var preorderRight = preorder.stream().filter(rightNodes::contains).collect(toList());
        var inorderLeft = inorder.stream().filter(leftNodes::contains).collect(toList());
        var inorderRight = inorder.stream().filter(rightNodes::contains).collect(toList());
        return new Node(
                head,
                build(preorderLeft, inorderLeft),
                build(preorderRight, inorderRight));
    }
}