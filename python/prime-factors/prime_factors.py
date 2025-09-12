def factors(value):
    factors = []
    candidate = 2
    while value > 1:
        while value % candidate == 0:
            factors.append(candidate)
            value /= candidate
        candidate += 1
    return factors