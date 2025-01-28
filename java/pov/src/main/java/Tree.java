import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Tree {
    private final String label;
    private final List<Tree> children;

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = new ArrayList<Tree>(children);
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && children.containsAll(tree.children)
                && tree.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label +
                ", " + children +
                "}";
    }

    public Tree fromPov(String fromNode) {
        List<Tree> path = findPath(fromNode);
        if (path == null)
            throw new UnsupportedOperationException("Tree could not be reoriented");
        for (int i = 1; i < path.size(); i++) {
            path.get(i).children.remove(path.get(i - 1));
            path.get(i - 1).children.add(path.get(i));
        }
        return path.get(0);
    }

    public List<String> pathTo(String fromNode, String toNode) {
        List<Tree> path1 = findPath(fromNode);
        List<Tree> path2 = findPath(toNode);
        if (path1 == null || path2 == null)
            throw new UnsupportedOperationException("No path found");
        Tree parent = null;
        while (path1.size() > 0 && path2.size() > 0) {
            String label1 = path1.get(path1.size() - 1).label;
            String label2 = path2.get(path2.size() - 1).label;
            if (!label1.equals(label2))
                break;
            parent = path1.remove(path1.size() - 1);
            path2.remove(path2.size() - 1);
        }
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < path1.size(); i++)
            result.add(path1.get(i).label);
        result.add(parent.label);
        for (int i = path2.size() - 1; i >= 0; i--)
            result.add(path2.get(i).label);
        return result;
    }

    List<Tree> findPath(String node) {
        if (node.equals(label))
            return new ArrayList<Tree>(List.of(this));
        for (Tree child : children) {
            List<Tree> path = child.findPath(node);
            if (path != null) {
                path.add(this);
                return path;
            }
        }
        return null;
    }
}