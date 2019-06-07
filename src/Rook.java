import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(boolean team){
        super(team);
        //add moves to list
        Move up=new Move(0,1,9);
        Move down=new Move(0,-1,9);
        Move left=new Move(-1,0,9);
        Move right=new Move(1,0,9);
        this.addMove(up);
        this.addMove(down);
        this.addMove(left);
        this.addMove(right);
    }
    public boolean isValid(int fromX, int fromY, int toX, int toY, Board game_board, boolean countCheck){
        boolean ignoreCheckWorks= horizontalValid(fromX,fromY,toX,toY,game_board);
        if(!countCheck)
            return ignoreCheckWorks;
        else
            return super.avoidsCheck(fromX, fromY, toX, toY, game_board, ignoreCheckWorks);
    }
    public ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck){
        ArrayList<GameMove> nextMoveList=new ArrayList<GameMove>();
        for(Move move: getMoveList()){
            int dx=move.getX(), dy=move.getY(), M=move.getMax_dist();
            int toX=fromX,toY=fromY;
            for(int m=1;m<=M;m++){
                toX+=dx; toY+=dy;
                if(!isValid(fromX,fromY,toX,toY,game_board,!ignoreCheck))
                    break;
                if(game_board.getPiece(toX,toY)==null){
                    nextMoveList.add(new GameMove(fromX,fromY,toX,toY,null));
                }
                else {
                    nextMoveList.add(new GameMove(fromX,fromY,toX,toY,game_board.getPiece(toX,toY)));
                    break;
                }
            }
        }
        return nextMoveList;
    }

    public char getChar(){
        return(this.getTeam() ? 'R': 'r');
    }
    public double getValue() {return 50;}
    public ImageIcon getImageIcon(){
        if(this.getTeam())
            return new ImageIcon("./src/images/whiteRook.png");
        else
            return new ImageIcon("./src/images/blackRook.png");
    }
    public int getEvalID(){
        return 2;
    }
}
