def convert(number):

    return ''.join( v for k,v in { 3: "Pling", 5: "Plang", 7: "Plong" }.items() if number % k == 0 ) or str(number)

