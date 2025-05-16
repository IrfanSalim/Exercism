import re

RE = re.compile('^(x(?!r)|y(?!t)|[^aeiouqxy]*(?:qu?)?)(.+)$')

def translate(phrase):
    return ' '.join(map(translate_word, phrase.split()))

def translate_word(word):
    return RE.sub(lambda m: '{1}{0}ay'.format(*m.groups()), word)