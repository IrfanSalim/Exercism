from string import ascii_lowercase as lower, punctuation as punct

table = str.maketrans(lower, lower[::-1], ' '+punct)
csize = 5


def encode(plain_text):
    out = plain_text.lower().translate(table)
    return ' '.join([out[i:i+5] for i in range(0, len(out), 5)])


def decode(ciphered_text):
    return ciphered_text.translate(table)