import javax.swing.*;
import java.util.ArrayList;

public class King extends Piece{
    public King(boolean team){
        super(team);
        //add moves to list
        Move m1=new Move(1,1,1);
        Move m2=new Move(-1,1,1);
        Move m3=new Move(1,-1,1);
        Move m4=new Move(-1,-1,1);
        Move m5=new Move(0,1,1);
        Move m6=new Move(0,-1,1);
        Move m7=new Move(1,0,1);
        Move m8=new Move(-1,0,1);
        this.addMove(m1);
        this.addMove(m2);
        this.addMove(m3);
        this.addMove(m4);
        this.addMove(m5);
        this.addMove(m6);
        this.addMove(m7);
        this.addMove(m8);
    }
    public boolean isValid(int fromX, int fromY, int toX, int toY, Board game_board,boolean countCheck){
        int dx=abs(fromX-toX);
        int dy=abs(fromY-toY);
        if((dx>1 || dy>1)||(dx==0&&dy==0)){
            return false;
        }
        boolean ignoreCheckWorks=inRange(fromX)&&inRange(fromY)&&inRange(toX)&&inRange(toY)&&finalPointValid(toX,toY,game_board);
        if(!countCheck)
            return ignoreCheckWorks;
        else
            return super.avoidsCheck(fromX, fromY, toX, toY, game_board, ignoreCheckWorks);
    }
    public ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck){
        ArrayList<GameMove> nextMoveList=new ArrayList<GameMove>();
        for(int dx=-1;dx<2;dx++){
            for(int dy=-1;dy<2;dy++){
                if(dx==0&&dy==0)
                    continue;
                if(isValid(fromX,fromY,fromX+dx,fromY+dy,game_board,!ignoreCheck)){
                    nextMoveList.add(new GameMove(fromX,fromY,fromX+dx,fromY+dy,game_board.getPiece(fromX+dx,fromY+dy)));
                }
            }
        }
        return nextMoveList;
    }

    public char getChar(){
        return(this.getTeam() ? 'K': 'k');
    }
    public double getValue() {return 9000;}
    public ImageIcon getImageIcon(){
        if(this.getTeam())
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/whiteKing.png");
        else
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/blackKing.png");
    }
    public int getEvalID(){
        return 0;
    }
}
