import javax.swing.*;
import java.util.ArrayList;
public abstract class Piece {
    private boolean team; //true for white, false for black
    private boolean moved;
    private int dir; //+1 for white, -1 for black
    private ArrayList<Move> move_list;

    public Piece(boolean tm){
        team=tm;
        moved=false;
        dir= team ? 1 : -1;
        move_list=new ArrayList<Move>();
    }
    //getter and setter
    public boolean getTeam(){ return this.team; }
    public int getDir(){
        return this.dir;
    }
    public boolean getMoved(){
        return this.moved;
    }
    public ArrayList<Move> getMoveList(){
        return this.move_list;
    }
    public void setMoved(){
        moved=true;
    }
    public void addMove(Move m){
        move_list.add(m);
    }
    public void printStats() {
        System.out.println("Piece stats:");
        String tm = team ? "White" : "Black";
        System.out.println("Team: " + tm);
        System.out.println("Direction: " + dir);
        System.out.println("Moved: " + moved);
    }
    //functions related to pieces moving
    public boolean inRange(int x){
        return (x>=0 && x<8);
    }
    public int max(int a, int b){return (a>=b) ? a: b;}
    public int min(int a, int b){return (a>=b) ? b: a;}
    public int dist(int a, int b){return (a>=b) ? a-b: b-a;}
    public int abs(int a){return dist(a,0);}
    //checks to make sure fromX and fromY are in game board and to_move is not occupied by friendly piece
    //checking if move vector is legal to specific piece toYpe is delegated to board
    public boolean isValid(int fromX, int fromY, int toX, int toY,Board game_board,boolean blah) {
        if(!(inRange(fromX)&&inRange(fromY)&&inRange(toX)&&inRange(toY)))
            return false;
        if(fromX==toX && fromY==toY)
            return false;
        Spot to_spot=game_board.getSpot(toX,toY);
        if(to_spot.getPiece()!=null){
            if(to_spot.getPiece().getTeam()==this.getTeam())
                return false;
        }
        return true;
    }
    public boolean finalPointValid(int toX,int toY, Board game_board){
        if(game_board.getPiece(toX,toY)!=null&&game_board.getPiece(toX,toY).getTeam()==this.getTeam())
            return false;
        return true;
    }
    public boolean horizontalValid(int fromX, int fromY, int toX, int toY, Board game_board){
        if(!(inRange(fromX)&&inRange(fromY)&&inRange(toX)&&inRange(toY)))
            return false;
        if(fromX!=toX&&fromY!=toY){
            return false;
        }
        if(fromX==toX){
            for(int y=min(toY,fromY)+1;y<max(toY,fromY);y++){
                if(game_board.getPiece(fromX,y)!=null)
                    return false;
            }
        }
        else{
            for(int x=min(toX,fromX)+1;x<max(toX,fromX);x++){
                if(game_board.getPiece(x,fromY)!=null)
                    return false;
            }
        }
        return finalPointValid(toX,toY,game_board);
    }
    public boolean diagonalValid(int fromX, int fromY, int toX, int toY, Board game_board){
        if(!(inRange(fromX)&&inRange(fromY)&&inRange(toX)&&inRange(toY)))
            return false;
        if(dist(toX,fromX)!=dist(toY,fromY))
            return false;
        int dx=(fromX<toX) ? 1 : -1;
        int dy=(fromY<toY) ? 1 : -1;
        int d=dist(fromX,toX);
        for(int i=1;i<d;i++){
            fromX+=dx; fromY+=dy;
            if(game_board.getPiece(fromX,fromY)!=null)
                return false;
        }
        return finalPointValid(toX,toY,game_board);
    }
    public boolean avoidsCheck(int fromX, int fromY, int toX, int toY, Board game_board, boolean ignoreCheckWorks){
        if(!ignoreCheckWorks)
            return false;
        game_board.makeMove(fromX, fromY, toX, toY);
        boolean inCheck=game_board.inCheck(this.getTeam());
        game_board.undoMove();
        if(!inCheck)
            return true;
        else
            return false;
    }
    public abstract ArrayList<GameMove> findNextMoves(int fromX, int fromY, Board game_board, boolean ignoreCheck);
    public void printMoveMap(int fromX, int fromY, Board game_board){
        System.out.println("Possible moves for selected piece: ");
        for(GameMove gm: this.findNextMoves(fromX,fromY,game_board,false)){
            System.out.println((char)(gm.getToX()+65)+""+(char)(gm.getToY()+49));
        }
    }
    public abstract char getChar();
    public abstract double getValue();
    public abstract ImageIcon getImageIcon();
    public abstract int getEvalID();
}

//to determine legal moves:
//for all pieces except knights:
//check all possible jump vectors to see if they are valid (via slide
//if hit friendly piece, stop and break before that piece
//if hit enemy piece, take that piece and stop going forward
//for knight
//check end points of jump

//function to generate move list
//separate function to check if move works as above can be done efficiently by calculating a list of all the moves that work