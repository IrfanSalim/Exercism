def find(search_list, value):
    left = 0
    right = len(search_list) - 1
    while left <= right:
        middle = (left + right) // 2
        if search_list[middle] < value:
            left = middle + 1
        elif search_list[middle] > value:
            right = middle - 1
        else:
            return middle
    raise ValueError("value not in array")