def equilateral(sides):
    return is_valid(sides) and len(set(sides)) == 1

def isosceles(sides):
    return is_valid(sides) and len(set(sides)) < 3

def scalene(sides):
    return is_valid(sides) and len(set(sides)) == 3

def is_valid(sides):
    return all(sides) and (sum(sides) >= max(sides)*2)