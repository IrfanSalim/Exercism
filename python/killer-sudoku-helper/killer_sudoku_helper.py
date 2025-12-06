import itertools

def combinations(target, size, exclude):
    available_numbers = list(set([1, 2, 3, 4, 5, 6, 7, 8, 9]) - set(exclude))
    return [list(combination) for combination in itertools.combinations(available_numbers, size) if sum(combination) == target]