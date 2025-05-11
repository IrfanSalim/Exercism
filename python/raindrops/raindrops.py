def convert(number):

    return ''.join( v for k,v in { 3: "Pling", 5: "Plang", 7: "Plong" }.items() if number % k == 0 ) or str(number)

# def convert(number):
#     return (("" if number % 3 else "Pling") +
#             ("" if number % 5 else "Plang") +
#             ("" if number % 7 else "Plong")) or str(number)