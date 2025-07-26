"""
This module calculates resistor values based on color codes.
"""

RESISTOR_COLORS = (
    "black",
    "brown",
    "red",
    "orange",
    "yellow",
    "green",
    "blue",
    "violet",
    "grey",
    "white",
)

RESISTOR_COLORS_TOLERANCE = {
    "silver": "10%",
    "gold": "5%",
    "red": "2%",
    "brown": "1%",
    "green": "0.5%",
    "blue": "0.25%",
    "violet": "0.1%",
    "grey": "0.05%",
}


def value(color):
    """
    Return the index of a color in the RESISTOR_COLORS tuple.
    Color is expected to be a string corresponding to a valid color.
    """
    return RESISTOR_COLORS.index(color)


def resistor_label(colors):
    """
    Calculate and return the resistor value with units based on a list of color codes.
    The colors parameter should be a list where the first few elements are color
    codes representing digits, and the last element is the tolerance color.
    Handles both 4-band and 5-band resistors.
    """
    tolerance_color = colors[-1]
    color_values = [value(color) for color in colors[:-1]]

    if len(colors) == 4:
        resistance_value = (
            10 * color_values[0] + color_values[1]
        ) * 10 ** color_values[2]
    elif len(colors) == 5:
        resistance_value = (
            100 * color_values[0] + 10 * color_values[1] + color_values[2]
        ) * 10 ** color_values[3]
    else:
        return "0 ohms"

    for factor, prefix in [
        (1_000_000_000, "giga"),
        (1_000_000, "mega"),
        (1_000, "kilo"),
    ]:
        if resistance_value >= factor:
            resistance_value /= factor
            return (
                f"{resistance_value:g}"
                " "
                f"{prefix}ohms"
                " "
                f"±{RESISTOR_COLORS_TOLERANCE[tolerance_color]}"
            )

    return (
        f"{resistance_value:g} ohms"
        " "
        f"±{RESISTOR_COLORS_TOLERANCE[tolerance_color]}"
    )