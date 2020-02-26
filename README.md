# Chess-Problem

## Layout and Move Files
The program will readd data from the layout file and setd up the board and then play the sequence of moves in the moves files

### Layouts
A layout file is a possibly empty sequence of lines

If a line begins (0th character) with a #, that line is a comment and should be ignored

Otherwise, a line should have the form xn=cp, indicating that position xn starts out with piece cp, where:

x is a column (a-h)
n is a row (1-8)
c is a color (b or w)
p is a piece kind (k, q, n, b, r, or p)

All files we take are assumed not have any extra whitespace at the beginning or end of a line

A layout file may not place two pieces in the same location.

### Moves
A moves file is a possibly empty sequence of lines

If a line begins (0th character) with a #, that line is a comment and should be ignored

Otherwise, a line should have the form xn-ym, indicating that the piece at position xn moves to position ym, where:

x and y are columns (a-h)
n and m are rows (1-8)

All moves must be valid according to a subset of the chess rules we discuss below.

## Chess Pieces
Each chess piece has a color, which is either Color.BLACK or Color.WHITE. This typesafe enum is defined in Color.java. The color is passed to the piece's constructor.

Each piece's toString() method will return the piece name in the form it appears in the layout file, e.g., a black king will return bk, a white pawn should return wp.

Each piece has a method List<String> moves(Board b, String loc) that returns a list containing the locations the piece could legally move to starting from the position loc, taking the positions of other pieces on the board into account. (However, there should not be any check by this method that the piece is actually at position loc.) The loc is given in the same notation as the layout file, e.g., "a3" is a location. Each piece has a different set of moves. Following movemnt rules applied in the program are slightly simplified from the rules of chess:
  
The king can move to one adjacent space in any direction (left/right, up/down, or diagonal). 

The queen can move any number of vacant spaces in any direction, left/right, up/down, or diagonal. The queen can stop moving before capturing a piece, or can capture a piece at the end of the move.

The knight moves in an L-shape: two squares horizontally and then one square vertically, or two squares vertically and one square horizontally. See the rules of chess wikipedia page for a picture. The knight jumps over other pieces. It can either land on an unoccupied space, or it can capture a piece by landing on an occupied space.

The bishop is like a queen that can only move diagonally. It moves any number of vacant spaces along any diagonal, and may or may not capture a piece at the end of its move.

The rook is like a queen that can only move horizontally or vertically. It moves any number of vacant spaces either horizontally or vertically, and may or may not capture a piece at the end of its move. 

The pawn can move one space vertically forward, only toward the opponent's side of the board, i.e., a black pawn can move toward row 1, and a white pawn can move toward row 8. However, the pawn can move two spaces forward if it is in its home row (row 2 for white, row 7 for black) and the intervening space is vacant. The preceding two moves can only be made to vacant spaces; they cannot capture pieces. A pawn can capture an opponent's piece by moving one square diagonally toward the opponent. It cannot move diagonally if it is not capturing a piece. En passant or pawn promotion is not implemented.

Pieces can only capture the opponent's pieces (i.e., of the opposite color).
  
## Chess Piece Factories
Method Piece Piece.createPiece(String name) will create an instance of the piece described by its argument in the same format as the layout file, e.g., Piece.createPiece("br") will return a black rook.

For each subclass C of Piece, there is a corresponding class CFactory that has two methods: char symbol(), which returns the piece's one-letter symbol (e.g., 'p' for Pawn) and Piece create(Color c), which returns a new instance of the corresponding piece with the given color, e.g., PieceFactory p = new PawnFactory(); p.create(Color.WHITE) returns a new white pawn.

In Chess.java, there is a sequence of calls of the form Piece.registerPiece(new PawnFactory()), which makes a new instance of the factory object and passes it to Piece.registerPiece. 
  
## The Chess Board
Piece getPiece(String loc) returns the piece at the given location (in the usual notation, e.g., "a3"), or null if the location is empty. It will raise an exception if loc is invalid.

void addPiece(Piece p, String loc) adds piece p to the board at the given location. It will raise an exception if the location is already occupied or is invalid.

void movePiece(String from, String to) moves the piece at location from to location to. This method will check that there is a piece at from (and throw an exception if not), and it will check that the move is valid for that piece. If the move is valid, then location from becomes vacant and the piece is placed at position to. It is possible there was another piece at to, in which case it has been captured; see below. If the move is invalid or from or to are invalid locations, movePiece will raise an exception.

void clear() will remove all pieces from the board.

## The Chess Board Observer
void registerListener(BoardListener bl) will add a listener that will subsequently be called at the appropriate times.There can be any number of listeners at any time (zero or more). The case of the same listener being registered more than once is not considered.

void removeListener(BoardListener bl) removes a listener so it will subsequently not be notified of events.

void removeAllListeners() removes all listeners.

## The Chess Board Iterator
The BoardExternalIterator interface (sorry for the terribly long name) defines a method void visit(String loc, Piece p).The iterate method of Board takes a BoardExternalIterator and, for every square on the board, calls the external iterator's visit method, passing that square's location and the piece at the location, or null if the location is vacant. The iteration will visit every square of the board. (So, visit will be called 64 times.)
