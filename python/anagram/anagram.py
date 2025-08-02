def find_anagrams(word, candidates):
    return set(cand for cand in candidates if word.lower() != cand.lower() and sorted(word.lower())==sorted(cand.lower()))
    