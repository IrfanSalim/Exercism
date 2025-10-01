from itertools import combinations_with_replacement
def find_fewest_coins(coins, target):
    """
    Correctly determine the fewest number of coins to be given to a customer
    such that the sum of the coins' value would equal the correct amount of change.

    Args:
        coins ([list]): [a list of coins values]
        target ([int]): [total change to return]

    Raises:
        ValueError: [if target negative]
        ValueError: [if change can't be returned given the coins]

    Returns:
        [list]: [coins]
    """
    if target < 0:
        raise ValueError("target can't be negative")
    for i in range(target//coins[0]+1):
        for combination in combinations_with_replacement(coins, i):
            if sum(combination) == target:
                return list(combination)
    raise ValueError("can't make target with given coins")