// import java.util.Map;
// import java.util.HashMap;
// import java.util.TreeSet;
// import java.util.ArrayList;

// class BuildTree {
//     Map<Integer, TreeSet<Integer>> map = new HashMap<>();

//     TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
//         if (records.isEmpty())
//             return null;

//         var set = new TreeSet<Integer>();

//         for (var record : records) {
//             int id = record.getRecordId();

//             if (!set.add(id))
//                 throw new InvalidRecordsException("Invalid Records");

//             int pid = record.getParentId();

//             if (id == 0) {
//                 if (pid != 0)
//                     throw new InvalidRecordsException("Invalid Records");
//                 continue;
//             }

//             if (pid >= id)
//                 throw new InvalidRecordsException("Invalid Records");

//             map.computeIfAbsent(pid, k -> new TreeSet<>()).add(id);
//         }

//         if (set.first() != 0 || set.last() != records.size() - 1)
//             throw new InvalidRecordsException("Invalid Records");

//         return buildNode(0);
//     }

//     private TreeNode buildNode(int n) {
//         TreeNode res = new TreeNode(n);

//         if (map.containsKey(n)) {
//             for (int c : map.get(n))
//                 res.getChildren().add(buildNode(c));
//         }

//         return res;
//     }
// }

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        int size = records.size();
        if (size == 0) {
            return null;
        }

        records.sort(Comparator.comparing(Record::getRecordId));
        if (records.get(0).getRecordId() != 0 || records.get(0).getParentId() != 0
                || records.get(size - 1).getRecordId() != size - 1
                || IntStream.range(1, size)
                        .anyMatch(i -> records.get(i).getRecordId() <= records.get(i).getParentId())) {
            throw new InvalidRecordsException("Invalid Records");
        }

        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(new TreeNode(0));
        for (int i = 1; i < size; i++) {
            Record record = records.get(i);
            TreeNode node = new TreeNode(record.getRecordId());
            treeNodes.add(node);
            treeNodes.get(record.getParentId()).getChildren().add(node);
        }

        return treeNodes.get(0);
    }

}