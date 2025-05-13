def classify(number):
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """
    if number < 1:
        raise ValueError("Classification is only possible for positive integers.")
    total = sum(i for i in range(1, number//2+1) if not number % i)
    if total == number:
        return "perfect"
    elif total > number:
        return "abundant"
    else:
        return "deficient"