def sum_of_squares(n: int) -> int:
    return sum(map(lambda x: x ** 2, range(1, n + 1)))

def square_of_sum(n: int) -> int:
    return sum(range(1, n + 1)) ** 2

def difference_of_squares(n: int) -> int:
    return square_of_sum(n) - sum_of_squares(n)