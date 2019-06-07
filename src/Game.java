public class Game {
    private Board game_board;
    private Player white;
    private Player black;
    private int turnCounter;
    private int gameType; //1 for computer/human, 0 for human/human
    private int gameState;
    private int fromX,fromY,toX,toY;
    //private JPanel display

    public Game(boolean computer){
        game_board=new Board();
        white=new Human_Player(true);
        if(computer) {
            black = new Computer_Player(false);
            gameType=1;
        }
        else {
            black = new Human_Player(false);
            gameType=0;
        }
        gameState=0;
        turnCounter=0;
        //insert initialization here
    }

    public Player getBlack() {
        return black;
    }

    public Player getWhite() {
        return white;
    }

    public Board getGame_board() {
        return game_board;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int newState){
        gameState=newState;
    }

    public void changeGameState(int change){
        gameState+=4+change; gameState%=4;
    }

    public int getGameType() {
        return gameType;
    }

    public void setFrom(int fX,int fY){
        fromX=fX; fromY=fY;
    }

    public void setTo(int tX,int tY){
        toX=tX; toY=tY;
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

    public boolean isValid(){
        return game_board.getPiece(fromX,fromY).isValid(fromX,fromY,toX,toY,game_board,true);
    }

    public void makeMove(){
        //printCurrentMove();
        //getGame_board().print_board();
        getGame_board().getPiece(fromX,fromY).setMoved();
        getGame_board().makeMove(fromX,fromY,toX,toY);
    }

    public void printCurrentMove(){
        //System.out.println("Current selected move: " + fromX+" "+fromY + " " + toX + " " + toY);
    }


    public void nextMove(){
        game_board.print_board();
        if(turnCounter%2==0) {
            System.out.println("MOVE FOR WHITE (upper case)");
            //white.printPossibleMoves(game_board);
            white.nextMove(game_board,true);
        }
        else {
            System.out.println("MOVE FOR BLACK (lower case)");
            //black.printPossibleMoves(game_board);
            black.nextMove(game_board,true);
        }
        turnCounter++;
    }

    public void printBoardValues(){
        System.out.println("White: "+white.boardValue(game_board));
        System.out.println("Black: "+black.boardValue(game_board));
    }


    //getter and setter

}
