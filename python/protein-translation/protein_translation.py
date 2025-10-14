def proteins(strand):
    #set dictionary of codons
    codons = {"AUG": "Methionine", "UUU": "Phenylalanine", "UUC": "Phenylalanine", "UUA": "Leucine", "UUG": "Leucine", "UCU": "Serine", "UCC": "Serine", "UCA": "Serine", "UCG": "Serine", "UAU": "Tyrosine", "UAC": "Tyrosine", "UGU": "Cysteine", "UGC": "Cysteine", "UGG": "Tryptophan"}
    #create empty list for proteins
    proteins = []
    #while loop for strand containing some string
    while len(strand) > 0:
        #if strand contains stop codon, break out
        if strand[:3] in ("UAA", "UAG", "UGA"):
            break
        # append the protein list with the value from the key within the codons dict
        proteins.append(codons[strand[0:3]])
        #create the next strand to analyse from where we finished
        strand = strand[3:]
    #return the updated protein list
    return proteins