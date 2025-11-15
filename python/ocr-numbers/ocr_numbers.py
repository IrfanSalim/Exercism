import re

NUMS = {"     |  |" : "1", " _  _||_ " : "2", " _  _| _|" : "3", "   |_|  |" : "4", " _ |_  _|" : "5",
		" _ |_ |_|" : "6", " _   |  |" : "7", " _ |_||_|" : "8", " _ |_| _|" : "9", " _ | ||_|" : "0"}

def convert(input_grid):
	if len(input_grid) % 4 > 0:
		raise ValueError("Number of input lines is not a multiple of four")
	res = []
	for st in range(0, len(input_grid), 4):
		n = len(input_grid[st])
		ocr = re.compile(r"^[ |_]{" + str(n) + r"}$")
		if n == 0 or n % 3 > 0 or input_grid[st + 3] != " " * n \
			or not all(ocr.match(input_grid[st + i]) for i in range(3)):
				raise ValueError("Number of input columns is not a multiple of three")
		res.append("".join(NUMS.get("".join(input_grid[st+j][i:i+3] for j in range(3)), "?") for i in range(0, n, 3)))
	return ",".join(res)