def to_rna(sequence):
    translations = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ''
    for letter in sequence:
        rna += translations[letter]
    return rna