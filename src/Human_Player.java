import java.util.Scanner;

public class Human_Player extends Player {
    public Human_Player(boolean player_team){
        super(player_team);
    }
    //gets a position feasible position
    public String getPosition(boolean b){
        Scanner reader=new Scanner(System.in);
        String from="@@";
        int counter=0;
        while((int)(from.charAt(0))<65||(int)(from.charAt(0))>72||(int)(from.charAt(1))<49||(int)(from.charAt(1))>56){
            if(counter>0)
                System.out.println("Invalid position!");
            if(b)
                System.out.print("Enter start: ");
            else
                System.out.print("Enter end: ");
            from=reader.next();
            counter++;
        }
        return from;
    }
    public String getFeasiblePosition(boolean b,Board game_board){
        String from=null;
        while(true){
            from=getPosition(b);
            int fromX=(int)(from.charAt(0))-65;
            int fromY=(int)(from.charAt(1))-49;
            if(game_board.getPiece(fromX,fromY)==null){
                System.out.println("No piece selected!");
                continue;
            }
            if(game_board.getPiece(fromX,fromY).getTeam()!=this.getTeam()){
                System.out.println("Wrong team selected!");
                continue;
            }
            if(game_board.getPiece(fromX,fromY).findNextMoves(fromX,fromY,game_board,false).size()>0){
                break;
            }
            System.out.println("Selected piece cannot be moved!");
        }
        return from;
    }
    public void nextMove(Board game_board, boolean test){
        while(true){
            //String from=getFeasiblePosition(true,game_board);
            String from=getFeasiblePosition(true,game_board);
            int fromX=(int)(from.charAt(0))-65;
            int fromY=(int)(from.charAt(1))-49;
            Piece p=game_board.getSpot(fromX,fromY).getPiece();
            if(test) {
                p.printMoveMap(fromX, fromY, game_board);
            }
            //p.printStats();
            String to=getPosition(false);
            int toX=(int)(to.charAt(0))-65;
            int toY=(int)(to.charAt(1))-49;
            if(p.isValid(fromX,fromY,toX,toY,game_board,true)){
                p.setMoved();
                game_board.makeMove(fromX,fromY,toX,toY);
                break;
            }
            System.out.println("\nInvalid move!\n");
        }
    }
    //below method is never used
    public GameMove getBestMove(){
        return new GameMove(0,0,0,0, new King(true));
    }

}
