def prime(number):

    if number < 1:
        raise ValueError('there is no zeroth prime')
    
    n = 2
    primes = [2]
    while len(primes) < number:
        n += 1
        if all(n % p > 0 for p in primes):
            primes.append(n)
    
    return primes[number - 1]