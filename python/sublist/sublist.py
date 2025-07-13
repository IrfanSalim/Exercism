"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST =   'SUBLIST'
EQUAL =     'EQUAL'
SUPERLIST = 'SUPERLIST'
UNEQUAL =   'UNEQUAL'


def sublist(first, second):
    smaller, larger =  sorted([first, second], key=len)

    if first == second:
        return EQUAL

    if not (first and second):
        return SUPERLIST if first else SUBLIST
    
    for index, _ in enumerate(larger):
        if larger[index : index + len(smaller)] == smaller:
            return SUPERLIST if larger == first else SUBLIST
    
    return UNEQUAL