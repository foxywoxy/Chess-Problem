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
    }

    public static void main(String[] args) throws Exception {

        test1();


    }
}
