def is_armstrong_number(number):
    digits = list(str(number))
    power = len(digits)
    digit_sum = 0
    for num in digits:
        digit_sum += int(num)**power
    return digit_sum == number