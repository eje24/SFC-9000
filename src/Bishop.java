import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Bishop extends Piece {
    public Bishop(boolean team){
        super(team);
        //add moves to list
        Move diag1=new Move(1,1,9);
        Move diag2=new Move(-1,1,9);
        Move diag3=new Move(1,-1,9);
        Move diag4=new Move(-1,-1,9);
        this.addMove(diag1);
        this.addMove(diag2);
        this.addMove(diag3);
        this.addMove(diag4);
    }
    public boolean isValid(int fromX, int fromY, int toX, int toY, Board game_board, boolean countCheck) {
        boolean ignoreCheckWorks=diagonalValid(fromX,fromY,toX,toY,game_board);
        if(!countCheck)
            return ignoreCheckWorks;
        else
            return super.avoidsCheck(fromX, fromY, toX, toY, game_board, ignoreCheckWorks);
    }
    public ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck){
        ArrayList<GameMove> nextMoveList=new ArrayList<GameMove>();
        for(Move move: getMoveList()){
            int dx=move.getX(),dy=move.getY(),M=move.getMax_dist();
            int toX=fromX,toY=fromY;
            for(int m=1;m<=M;m++){
                toX+=dx; toY+=dy;
                if(!isValid(fromX,fromY,toX,toY,game_board,!ignoreCheck))
                    break;
                if(game_board.getPiece(toX,toY)==null){
                    nextMoveList.add(new GameMove(fromX,fromY,toX,toY,null));
                }
                else{
                    nextMoveList.add(new GameMove(fromX,fromY,toX,toY,game_board.getPiece(toX,toY)));
                    break;
                }
            }
        }
        return nextMoveList;
    }

    public char getChar(){
        return(this.getTeam() ? 'B': 'b');
    }
    public double getValue() { return 30;}
    public ImageIcon getImageIcon(){
        if(this.getTeam())
            return new ImageIcon("./src/images/whiteBishop.png");
        else
            return new ImageIcon("./src/images/blackBishop.png");
    }
    public int getEvalID(){
        return 3;
    }
}
