import java.util.List;

public class Test {
    public Test(){ }
    public static void test1() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece p = Piece.createPiece("bp");
        System.out.println(p.moves(b, "d7"));


        //assert b.getPiece("a3") == p;
    public static void testRook(){

		Board myBoard = Board.theBoard();
	
		Piece.registerPiece(new PawnFactory());
		Piece wp = Piece.createPiece("wp");
		myBoard.addPiece(wp, "b4");

		Piece wp2 = Piece.createPiece("wp");
		myBoard.addPiece(wp2, "d2");
	
		Piece bp = Piece.createPiece("bp");
		myBoard.addPiece(bp, "f4");
		Piece bp2 = Piece.createPiece("bp");
		myBoard.addPiece(bp2, "d7");
	
		Piece.registerPiece(new RookFactory());
		Piece wr = Piece.createPiece("wr");
		myBoard.addPiece(wr, "d4");
		List<String> movesList = wr.moves(myBoard, "d4");
	
		assert movesList.size() == 7;
		assert movesList.contains("d3");
		assert movesList.contains("d5");
		assert movesList.contains("d6");
		assert movesList.contains("d7");
		assert movesList.contains("c4");
		assert movesList.contains("e4");
		assert movesList.contains("f4");
        
	}
	
	public static void testPawn() {
		Board b = Board.theBoard();
		Piece.registerPiece(new PawnFactory());
		Piece wp = Piece.createPiece("wp");
		b.addPiece(wp, "a2");
		assert b.getPiece("a2") == wp;

		List<String> wpPossibleMoves = wp.moves(b, "a2");
		assert wpPossibleMoves.contains("a3");
		assert wpPossibleMoves.contains("a4");

		Piece.registerPiece(new QueenFactory());
		Piece bq = Piece.createPiece("bq");
		b.addPiece(bq, "b3");
		assert b.getPiece("b3") == wp;

		wpPossibleMoves = wp.moves(b, "a2");
		assert wpPossibleMoves.contains("a3");
		assert wpPossibleMoves.contains("a4");
		assert wpPossibleMoves.contains("b3");

		b.movePiece("a2", "a3");
		assert b.getPiece("a3") == wp;
		assert wpPossibleMoves.contains("a4");
	}
	
	public static void testKnight()
{
	Board b = Board.theBoard();
	Piece.registerPiece(new KnightFactory());
	Piece bk = Piece.createPiece("bn");
	b.addPiece(bn, "g8");
	
	List<String> moves = bk.moves(b, "g8");
	
	assert movesList.size() == 1;
	assert movesList.contains("f6");
	assert movesList.contains("h6");
	assert !movesList.contains("g6");
}
	
	public static void testBishop() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece wb = Piece.createPiece("wb");
        b.addPiece(wb, "f1");
        assert b.getPiece("f1") == wb;
        List<String> moves = wb.moves(b,"f1");
        assert moves.size() == 7;
        assert moves.contains("e2");
        assert moves.contains("d3");
        assert moves.contains("c4");
        assert moves.contains("b5");
        assert moves.contains("a6");
        assert moves.contains("g2");
        assert moves.contains("h3");

        /* put pawns to block off all paths */
        Piece p = Piece.createPiece("wp");
        b.addPiece(p, "e2");
        assert b.getPiece("e2") == p;
        p = Piece.createPiece("wp");
        b.addPiece(p, "g2");
        assert b.getPiece("g2") == p;

        moves = wb.moves(b,"f1");
        assert moves.size() == 0;
}
	
	public static void testKing(){
		Board.theBoard().clear();
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		Piece bk = Piece.createPiece("bk");
		Board.theBoard().addPiece(bk, "d5");
		Board.theBoard().addPiece(Piece.createPiece("bp"), "d6");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "d4");
		Board.theBoard().addPiece(Piece.createPiece("wn"), "c5");
		Board.theBoard().addPiece(Piece.createPiece("wp"), "c6");
		Board.theBoard().addPiece(Piece.createPiece("bp"), "c4");

		List<String> toList = bk.moves(Board.theBoard(), "d5" );
		List<String> cList = Arrays.asList("d4", "c5","e5","c6","e4","e6");

		//size equal
		assert  toList.size() == cList.size();
		for(String loc : cList){
			//contain all elements
			assert toList.contains(loc);
		}
		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
}
	
	public static void testQueen() {
    	Board b = Board.theBoard();
    	
    	Piece.registerPiece(new QueenFactory());
    	Piece.registerPiece(new PawnFactory());
    	
    	Piece p1 = Piece.createPiece("bp");
    	Piece p2 = Piece.createPiece("bp");
    	Piece p3 = Piece.createPiece("bp");
    	Piece p4 = Piece.createPiece("wp");
    	Piece p5 = Piece.createPiece("wp");
    	Piece p6 = Piece.createPiece("wp");
    	Piece p7 = Piece.createPiece("bq");
    	
    	b.addPiece(p1, "a5");
    	b.addPiece(p2, "a3");
    	b.addPiece(p3, "c4");
    	b.addPiece(p4, "e5");
    	b.addPiece(p5, "d3");
    	b.addPiece(p6, "c2");
    	b.addPiece(p7, "c3");
    	
    	List<String> res = p7.moves(b, "c3");
    	assert res.size() == 10;
    	assert res.contains("b4");
    	assert res.contains("b3");
    	assert res.contains("b2");
    	assert res.contains("a1");
    	assert res.contains("c2");
    	assert res.contains("d4");
    	assert res.contains("d3");
    	assert res.contains("d2");
    	assert res.contains("e5");
    	assert res.contains("e1");
    	
    }
	
    public static void main(String[] args) throws Exception {

        test1();
		testRook();
		testPawn();
		testKnight();
		testBishop();
		testKing();
		testQueen();

    }
}
