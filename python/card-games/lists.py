from statistics import mean

def get_rounds(number):
    """
    :param number: int - current round number.
    :return: list - current round and the two that follow.
    """

    return [number, number + 1, number + 2]


def concatenate_rounds(rounds_1, rounds_2):
    """
    :param rounds_1: list - first rounds played.
    :param rounds_2: list - second set of rounds played.
    :return: list - all rounds played.
    """

    rounds_1.extend(rounds_2); return rounds_1

def list_contains_round(rounds, number):
    """
    :param rounds: list - rounds played.
    :param number: int - round number.
    :return:  bool - was the round played?
    """

    return number in rounds

def card_average(hand):
    """
    :param hand: list - cards in hand.
    :return:  float - average value of the cards in the hand.
    """

    return mean(hand)

def approx_average_is_average(hand):
    """
    :param hand: list - cards in hand.
    :return: bool - is approximate average the same as true average?
    """

    return (meanie := card_average(hand)) == (hand[0] + hand[-1]) / 2 or meanie == hand[len(hand)//2]

def average_even_is_average_odd(hand):
    """
    :param hand: list - cards in hand.
    :return: bool - are even and odd averages equal?
    """

    return card_average(hand[::2]) == card_average(hand[1::2])

def maybe_double_last(hand):
    """

    :param hand: list - cards in hand.
    :return: list - hand with Jacks (if present) value doubled.
    """

    if (last := hand[-1]) == 11: hand[-1] = last*2
    return hand