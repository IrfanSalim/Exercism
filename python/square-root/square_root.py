def square_root(n):
    c = 0
    d = 1 << 30

    while d:
        if n >= c + d:
            n -= c + d
            c = (c >> 1) + d
        else:
            c >>= 1
        d >>= 2
    return c