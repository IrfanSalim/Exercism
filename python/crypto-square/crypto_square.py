def cipher_text(plain_text: str) -> str:
    normalized = ''.join(char for char in plain_text if char.isalnum()).lower()
    norm_len = len(normalized)

    cols = rows = round(norm_len ** 0.5)
    if cols ** 2 < norm_len:
        cols += 1

    normalized += ' ' * (cols * rows - norm_len)

    return ' '.join(normalized[i::cols] for i in range(cols))