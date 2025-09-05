def append(list1, list2):
    return list1 + list2

def concat(lists):
    return [element for sublist in lists if sublist for element in sublist]

def filter(function, list):
    return [item for item in list if function(item)]

def length(list):
    return sum(1 for item in list)

def map(function, list):
    return [function(item) for item in list]

def foldl(function, list, initial):
    return initial if not list else foldl(function, list[1:], function(initial, list[0]))

def foldr(function, list, initial):
    return foldl(function, reverse(list), initial)

def reverse(list):
    return list[::-1]