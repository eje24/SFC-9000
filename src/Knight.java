import javax.swing.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean team){
        super(team);
        //add moves to list
        Move m1=new Move(2,1,1);
        Move m2=new Move(-2,1,1);
        Move m3=new Move(2,-1,1);
        Move m4=new Move(-2,-1,1);
        Move m5=new Move(1,2,1);
        Move m6=new Move(-1,2,1);
        Move m7=new Move(1,-2,1);
        Move m8=new Move(-1,-2,1);
        this.addMove(m1);
        this.addMove(m2);
        this.addMove(m3);
        this.addMove(m4);
        this.addMove(m5);
        this.addMove(m6);
        this.addMove(m7);
        this.addMove(m8);
    }
    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY, Board game_board, boolean countCheck){
        if(!super.isValid(fromX,fromY,toX,toY,game_board,true))
            return false;
        int dx=toX-fromX; int dy=toY-fromY;
        boolean ignoreCheckWorks=(abs(dx)>0&&abs(dy)>0&&abs(dx)+abs(dy)==3);
        if(!countCheck)
            return ignoreCheckWorks;
        else
            return super.avoidsCheck(fromX, fromY, toX, toY, game_board, ignoreCheckWorks);
    }
    public ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck){
        ArrayList<GameMove> nextMoveList=new ArrayList<GameMove>();
        for(Move move: getMoveList()){
            int dx=move.getX(), dy=move.getY();
            if(isValid(fromX,fromY,fromX+dx,fromY+dy,game_board,!ignoreCheck)){
                nextMoveList.add(new GameMove(fromX,fromY,fromX+dx,fromY+dy,game_board.getPiece(fromX+dx,fromY+dy)));
            }
        }
        return nextMoveList;
    }

    public char getChar(){
        return(this.getTeam() ? 'N': 'n');
    }
    public double getValue() {return 30;}
    public ImageIcon getImageIcon(){
        if(this.getTeam())
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/whiteKnight.png");
        else
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/blackKnight.png");
    }
    public int getEvalID(){
        return 4;
    }
}
