import javax.swing.*;
import java.util.ArrayList;
public class Pawn extends Piece {
    public Pawn(boolean team){
        super(team);
        //add moves to list
        Move forward_one=new Move(0,1,1);
        this.addMove(forward_one);
    }
    public boolean isValid(int fromX, int fromY, int toX, int toY, Board game_board, boolean countCheck){ //fromX and toX from
        if(!super.isValid(fromX,fromY,toX,toY,game_board,true))
            return false;
        //which direction the pawn moves in
        int direction=this.getDir();
        boolean ignoreCheckWorks;
        if(fromX==toX&&game_board.getPiece(toX,toY)==null) {
            if (!this.getMoved())
                ignoreCheckWorks= (toY == fromY + direction) || (toY == fromY + 2 * direction && game_board.getPiece(fromX,fromY+direction)==null);
            else
                ignoreCheckWorks= toY == fromY + direction;
        }
        else if(abs(fromX-toX)==1 && toY==fromY+direction){
            ignoreCheckWorks=(game_board.getSpot(toX,toY).getPiece()!=null&&game_board.getSpot(toX,toY).getPiece().getTeam()!=this.getTeam());
        }
        else
            ignoreCheckWorks= false;
        if(!countCheck)
            return ignoreCheckWorks;
        else{
            return super.avoidsCheck(fromX, fromY, toX, toY, game_board, ignoreCheckWorks);
        }


    }
    public ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck){
        ArrayList<GameMove> nextMoveList=new ArrayList<GameMove>();
        if(isValid(fromX,fromY,fromX,fromY+getDir(),game_board,!ignoreCheck)){
            nextMoveList.add(new GameMove(fromX,fromY,fromX,fromY+getDir(),game_board.getPiece(fromX,fromY+getDir())));
        }
        if(isValid(fromX,fromY,fromX,fromY+2*getDir(),game_board,!ignoreCheck)){
            nextMoveList.add(new GameMove(fromX,fromY,fromX,fromY+2*getDir(),game_board.getPiece(fromX,fromY+2*getDir())));
        }
        if(isValid(fromX,fromY,fromX+1,fromY+getDir(),game_board,!ignoreCheck)) {
            nextMoveList.add(new GameMove(fromX,fromY,fromX+1,fromY+getDir(),game_board.getPiece(fromX+1,fromY+getDir())));
        }
        if(isValid(fromX,fromY,fromX-1,fromY+getDir(),game_board,!ignoreCheck)) {
            nextMoveList.add(new GameMove(fromX,fromY,fromX-1,fromY+getDir(),game_board.getPiece(fromX-1,fromY+getDir())));
        }
        return nextMoveList;
    }
    public char getChar(){
        return(this.getTeam() ? 'P': 'p');
    }
    public double getValue(){ return 10;}
    public ImageIcon getImageIcon(){
        if(this.getTeam())
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/whitePawn.png");
        else
            return new ImageIcon("/Users/Ezra/IdeaProjects/Super Fantastic Chess 9000/src/images/blackPawn.png");
    }
    public int getEvalID(){
        return 5;
    }




}
