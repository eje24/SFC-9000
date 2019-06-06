public class Spot {
    private int x;
    private int y;
    private Piece piece;

    public Spot(int a, int b){
        this.x=a;
        this.y=b;
        this.piece=null;
    }
    public void setPiece(Piece p){
        this.piece=p;
    }
    public Piece getPiece(){
        return this.piece;
    }

//    public Spot[] findNextMoves(){
//        return piece.findNextMoves(x,y);
//    }




}
