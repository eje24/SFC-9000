import java.util.ArrayList;

public abstract class Player {
    private boolean team; //true for white, false for black
    public Player(boolean player_team){
        this.team=player_team;
    }


    public boolean getTeam(){
        return team;
    }

    public double boardValue(Board game_board){
        double res=0;
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                Piece curr=game_board.getSpot(x,y).getPiece();
                if(curr==null)
                    continue;
                if(this.team){
                    //white move
                    if(curr.getTeam()==this.getTeam())
                        res+=(curr.getValue()+game_board.getEval(curr.getEvalID(),y,x));
                    else
                        res-=(curr.getValue()+game_board.getEval(curr.getEvalID(),7-y,x));
                }
                else{
                    //black move
                    if(curr.getTeam()==this.getTeam())
                        res+=(curr.getValue()+game_board.getEval(curr.getEvalID(),7-y,x));
                    else
                        res-=(curr.getValue()+game_board.getEval(curr.getEvalID(),y,x));
                }
            }
        }
        return res;
    }
    //same team is whether getting moves for this team or the other team (point is that it is hard to access the opposite player if only given a reference to one player in computerplayer.java)
    public ArrayList<GameMove> getMoves(Board game_board,boolean sameTeam, boolean checkTest, boolean ignoreCheck){
        if(checkTest)
            System.out.println("Checking getMoves for potentialcheckmate!");
        ArrayList<GameMove> moveList=new ArrayList<GameMove>();
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                Piece curr=game_board.getPiece(x,y);
                if(curr==null)
                    continue;
                if(sameTeam&&curr.getTeam()==this.getTeam()){
                    for(GameMove gm: curr.findNextMoves(x,y,game_board,ignoreCheck))
                        moveList.add(gm);
                }
                else if(!sameTeam&&curr.getTeam()!=this.getTeam()){
                    for(GameMove gm: curr.findNextMoves(x,y,game_board,ignoreCheck))
                        moveList.add(gm);
                }
                if(checkTest&&moveList.size()>0) {
                    System.out.println("Found a move, returning checklist!");
                    return moveList;
                }
            }
        }
        return moveList;
    }
    public void printPossibleMoves(Board game_board){
        System.out.println("Possible moves: ");
        for(GameMove gm: this.getMoves(game_board,true,false,false)){
            System.out.println(gm.toString());
        }
    }
    public abstract GameMove getBestMove();
    public abstract void nextMove(Board game_board,boolean test);


}
