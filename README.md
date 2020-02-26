# Chess-Problem
## Layout and Move Files
### Layouts
A layout file is a possibly empty sequence of lines
If a line begins (0th character) with a #, that line is a comment and should be ignored
Otherwise, a line should have the form xn=cp, indicating that position xn starts out with piece cp, where:
x is a column (a-h)
n is a row (1-8)
c is a color (b or w)
p is a piece kind (k, q, n, b, r, or p)
All files we supply will not have any extra whitespace at the beginning or end of a line, so you can allow or disallow extra whitespace as you like.
A layout file may not place two pieces in the same location.
### Moves
A moves file is a possibly empty sequence of lines
If a line begins (0th character) with a #, that line is a comment and should be ignored
Otherwise, a line should have the form xn-ym, indicating that the piece at position xn moves to position ym, where:
x and y are columns (a-h)
n and m are rows (1-8)
All files we supply will not have any extra whitespace at the beginning or end of a line.
All moves must be valid according to a subset of the chess rules we discuss below.
