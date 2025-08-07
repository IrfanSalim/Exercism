def recite(start_verse, end_verse):
    """Recite selections of verses of the nursery rhyme 'This is the House that Jack Built'."""

    return [build_verse(n) for n in range(start_verse - 1, end_verse)]


def build_verse(verse_number):
    """Recite a verse of the nursery rhyme 'This is the House that Jack Built'."""

    verse_text = "This is"

    the_players = [
        ["malt", "lay in"],
        ["rat", "ate"],
        ["cat", "killed"],
        ["dog", "worried"],
        ["cow with the crumpled horn", "tossed"],
        ["maiden all forlorn", "milked"],
        ["man all tattered and torn", "kissed"],
        ["priest all shaven and shorn", "married"],
        ["rooster that crowed in the morn", "woke"],
        ["farmer sowing his corn", "kept"],
        ["horse and the hound and the horn", "belonged to"],
    ]

    for player, action in reversed(the_players[:verse_number]):
        verse_text += " the " + player + " that " + action

    verse_text += " the house that Jack built."
    return verse_text