public class GameMove {
    private int fromX; //x coordinate of origin of moving piece
    private int fromY; //y coordinate of origin of moving piece
    private int toX; //x coordinate of destination of moving piece
    private int toY; //y coordinate of destination of moving piece
    private Piece p; //piece captured, can be null if just regular move

    //piece is recorded so that moves can be undone
    public GameMove(int fX, int fY, int tX, int tY, Piece P){
        fromX=fX;
        fromY=fY;
        toX=tX;
        toY=tY;
        p=P;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public Piece getPiece() {
        return p;
    }

    @Override
    public String toString() {
        String to=(char)(toX+65)+""+(char)(toY+49);
        String from=(char)(fromX+65)+""+(char)(fromY+49);
        return from + " to " + to;
    }
}
