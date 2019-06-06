import java.lang.Math;

public class Computer_Player extends Player {
    private GameMove bestMove;
    private int iterations;
    private final int DEPTH=5;
    private final double infinity=100000;
    public Computer_Player(boolean player_team){
        super(player_team);
        iterations=0;
        bestMove=null;
    }
    public double minimax(Board game_board,int depth, double alpha, double beta, boolean team, boolean root){
        if(depth==0 || this.boardValue(game_board)<-1000||this.boardValue(game_board)>1000){
            return this.boardValue(game_board);
        }
        if(team==this.getTeam()){
            //computes moves for friendly player
            double maxEval=-infinity,eval;
            for(GameMove gm: this.getMoves(game_board,true,false,true)){
                iterations++;
                game_board.makeMove(gm.getFromX(),gm.getFromY(),gm.getToX(),gm.getToY());
                eval=minimax(game_board,depth-1,alpha,beta,!team,false);
                game_board.undoMove();
                maxEval=Math.max(eval,maxEval);
                if(root&&eval>alpha)
                    bestMove=gm;
                alpha=Math.max(alpha,eval);
                if(beta<=alpha)
                    break;
            }
            return maxEval;
        }
        else {
            //compute moves for enemy player
            double minEval = infinity, eval;
            for (GameMove gm : this.getMoves(game_board, false,false,true)) {
                iterations++;
                game_board.makeMove(gm.getFromX(), gm.getFromY(), gm.getToX(), gm.getToY());
                eval = minimax(game_board, depth - 1, alpha, beta, !team, false);
                game_board.undoMove();
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                    break;
            }
            return minEval;
        }
    }

    public GameMove getBestMove() {
        return bestMove;
    }

    public void nextMove(Board game_board, boolean blah){
        double val=minimax(game_board, DEPTH, -infinity,infinity,this.getTeam(),true);
        //System.out.println("Positions evaluated: " + iterations);
        iterations=0;
    }

    //keep track of best game_move --> removes need for miniMax to return a game_move
}
