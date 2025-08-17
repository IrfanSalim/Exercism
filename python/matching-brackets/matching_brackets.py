import re
def is_paired(stg):
    brackets, has_pair = re.sub(r"[^{}[\]()]", "", stg), 1
    while has_pair:
        brackets, has_pair = re.subn(r"{}|\[]|\(\)", "", brackets)
    return not brackets