def largest(min_factor, max_factor):
    return get_palindr(min_factor, max_factor, smallest = False)

def smallest(min_factor, max_factor):
    return get_palindr(min_factor, max_factor)

def get_palindr(min, max, smallest = True):
    if min > max:
        raise ValueError("min must be <= max")

    arr = (min**2, max**2+1) if smallest else (max**2, min**2-1, -1)
    factors = []
    for prod in range(*arr):
        s = str(prod)
        if s == s[::-1]: # if palindrom
            for i in range(min, max+1):
                if prod%i == 0 and min <= i <= prod//i <= max:
                    factors.append([i, prod//i])
            if factors != []:
                return (prod, factors)
    return (None, [])