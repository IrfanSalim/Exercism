import java.util.List;

class BinarySearch {
    List<Integer> items;
    BinarySearch(List<Integer> items) {
      this.items = items;
    }

    int indexOf(int item) throws ValueNotFoundException {
      int left = 0;
      int right = items.size() - 1;
      while (left <= right) {
        int mid = (left + right) / 2;
        if (item == items.get(mid)) {
          return mid;
        } else if (item < items.get(mid)) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
      throw new ValueNotFoundException("Value not in array");
    }
}
