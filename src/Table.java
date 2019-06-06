import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    JFrame table=new JFrame("Super Fantastic Chess 9000");
    JPanel board = new JPanel();
    JButton buttonArray[][];
    public Game currGame;

    public Table(){
        board.setLayout(new GridLayout(8,8,0,0));
        buttonArray=new JButton[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                buttonArray[i][j]=new JButton();
                JButton currButton=buttonArray[i][j];
                Color darkBrown=new Color(130, 82, 1);
                Color lightBrown=new Color(182, 155, 76);
                if((i+j)%2==0)
                    currButton.setBackground(darkBrown);
                else
                    currButton.setBackground(lightBrown);
                final int currX=i; final int currY=j;

                currButton.addActionListener(new actionListentener(currGame,this,currX,currY));
                /*
                currButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Piece curr=getPiece(currX,currY);
                        int gameType=currGame.getGameType();
                        int gameState=currGame.getGameState();
                        if(gameType==0){
                            if(gameState==0&&curr!=null&&curr.getTeam()){
                                System.out.println("State: "+0);
                                currGame.setFrom(currY,7-currX);
                                currGame.changeGameState(1);
                            }
                            else if(gameState==1){
                                System.out.println("State: "+1);
                                currGame.setTo(currY,7-currX);
                                currGame.printCurrentMove();
                                if(!currGame.isValid()){
                                    currGame.changeGameState(-1);
                                    return;
                                }
                                System.out.println("Implementing current move!");
                                implementCurrentMove();
                                //set that piece to moved
                                currGame.changeGameState(1);
                                //checks for checkmate
                                System.out.println("Checking for checkmate!!");
                                if(currGame.getBlack().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                                    currGame.setGameState(5);
                                    System.out.println("BLUEUEUEOU>");
                                    announceCheckmate("White Wins!");
                                    return;
                                }
                                System.out.println("No checkmate!");
                            }
                            else if(gameState==2&&curr!=null&&!curr.getTeam()){
                                System.out.println("State: "+2);
                                currGame.setFrom(currY,7-currX);
                                currGame.changeGameState(1);
                            }
                            else if(gameState==3){
                                System.out.println("State: "+3);
                                currGame.setTo(currY,7-currX);
                                if(!currGame.isValid()) {
                                    currGame.changeGameState(-1);
                                    return;
                                }
                                implementCurrentMove();
                                //set that piece to moved
                                currGame.changeGameState(1);
                                System.out.println("Checking for checkmate!");
                                if(currGame.getWhite().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                                    System.out.println("bleueuedSHASHA");
                                    currGame.setGameState(5);
                                    announceCheckmate("Black Wins!");
                                    return;
                                }
                                System.out.println("No checkmate!");
                            }
                        }
                        else if(gameType==1){
                            if(gameState==0&&curr!=null&&curr.getTeam()){
                                currGame.setFrom(currY,7-currX);
                                currGame.changeGameState(1);
                            }
                            else if(gameState==1) {
                                currGame.setTo(currY,7-currX);
                                if (!currGame.isValid()) {
                                    currGame.changeGameState(-1);
                                    return;
                                }
                                //updates JFrame as well as game_board
                                implementCurrentMove();
                                //set that piece to moved
                                currGame.changeGameState(1);
                                //check for checkmate against black
                                if(currGame.getBlack().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                                    currGame.setGameState(5);
                                    announceCheckmate("White Wins!");
                                    return;
                                }
                                //Printing transition:
                                System.out.println("TRANSITION TO COMPUTER");
                                //implement AI move below
                                computerThread newComputerThread=new computerThread(currGame,);
//                                currGame.getBlack().nextMove(currGame.getGame_board(),false);
//                                GameMove computerMove=currGame.getBlack().getBestMove();
//                                currGame.setFrom(computerMove.getFromX(),computerMove.getFromY());
//                                currGame.setTo(computerMove.getToX(),computerMove.getToY());
//                                implementCurrentMove();
//                                currGame.changeGameState(2);

                                System.out.println("ENDING COMPUTER MOVE");

                                //check for checkmate against white
                                if(currGame.getWhite().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                                    currGame.setGameState(5);
                                    announceCheckmate("Black Wins!");
                                    return;
                                }
                            }
                        }

                    }
                });
                */

                currButton.setOpaque(false);
                board.add(currButton);
            }
        }
        //adding in toolbar
        JToolBar toolBar=new JToolBar();
        toolBar.setFloatable(false);
        //New Player vs Player game button
        JButton newPVP=new JButton("New PvP Game");
        newPVP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeNewPVPGame();
            }
        });
        //New Player vs Computer game button
        JButton newComputer=new JButton("New Computer Game");
        newComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeNewComputerGame();
            }
        });
        //Quit button
        JButton quitButton=new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //Todo: add undo button
        toolBar.add(newPVP);
        toolBar.add(newComputer);
        toolBar.addSeparator();
        toolBar.add(quitButton);
        table.add(toolBar,BorderLayout.PAGE_START);
        table.add(board);
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setSize(800,800);
        table.setVisible(true);
    }

    public void initializeNewPVPGame(){
        currGame=new Game(false);
        placePieces();

    }
    public void initializeNewComputerGame(){
        currGame=new Game(true);
        placePieces();
    }
    public void implementCurrentMove(){
        //currGame.printCurrentMove();
        currGame.makeMove();
        //currGame.getGame_board().print_board();
        resetIcon(7-currGame.getFromY(),currGame.getFromX());
        resetIcon(7-currGame.getToY(),currGame.getToX());
    }
    public void announceCheckmate(String s){
        JOptionPane.showMessageDialog(table,
                "CHECKMATE: " + s);
    }

    public void placePieces(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                //JButton currButton=buttonArray[i][j];
                resetIcon(i,j);
            }
        }
    }
    public void resetIcon(int x, int y){
        Piece curr=getPiece(x,y);
        if(curr==null)
            buttonArray[x][y].setIcon(null);
        else
            buttonArray[x][y].setIcon(curr.getImageIcon());
    }
    public Piece getPiece(int x, int y){
        if(currGame==null)
            return null;
        else
            return currGame.getGame_board().getPiece(y,7-x);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        new Table();
    }
}


class computerThread implements Runnable{
    public Game currGame;
    public Table table;
    computerThread(Game cg, Table tb){
        currGame = cg;
        table = tb;
    }
    @Override
    public void run() {
        currGame.getBlack().nextMove(currGame.getGame_board(),false);
        GameMove computerMove=currGame.getBlack().getBestMove();
        currGame.setFrom(computerMove.getFromX(),computerMove.getFromY());
        currGame.setTo(computerMove.getToX(),computerMove.getToY());
        table.implementCurrentMove();
        currGame.changeGameState(2);
        if(currGame.getWhite().getMoves(currGame.getGame_board(),true,true,false).size()==0){
            currGame.setGameState(5);
            table.announceCheckmate("Black Wins!");
            return;
        }
    }
}

class actionListentener implements ActionListener{
    public Game currGame;
    public Table table;
    public int currX,currY;
    public actionListentener(Game cg, Table tb, int cX, int cY) {
        currGame = cg;
        table = tb;
        currY = cY;currX=cX;
        if (currGame == null){
            System.out.println("got a null ref");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        currGame=table.currGame;
        Piece curr= table.getPiece(currX,currY);
        int gameType=currGame.getGameType();
        int gameState=currGame.getGameState();
        if(gameType==0){
            if(gameState==0&&curr!=null&&curr.getTeam()){
                System.out.println("State: "+0);
                currGame.setFrom(currY,7-currX);
                currGame.changeGameState(1);
            }
            else if(gameState==1){
                System.out.println("State: "+1);
                currGame.setTo(currY,7-currX);
                currGame.printCurrentMove();
                if(!currGame.isValid()){
                    currGame.changeGameState(-1);
                    return;
                }
                System.out.println("Implementing current move!");
                table.implementCurrentMove();
                //set that piece to moved
                currGame.changeGameState(1);
                //checks for checkmate
                System.out.println("Checking for checkmate!!");
                if(currGame.getBlack().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                    currGame.setGameState(5);
                    System.out.println("BLUEUEUEOU>");
                    table.announceCheckmate("White Wins!");
                    return;
                }
                System.out.println("No checkmate!");
            }
            else if(gameState==2&&curr!=null&&!curr.getTeam()){
                System.out.println("State: "+2);
                currGame.setFrom(currY,7-currX);
                currGame.changeGameState(1);
            }
            else if(gameState==3){
                System.out.println("State: "+3);
                currGame.setTo(currY,7-currX);
                if(!currGame.isValid()) {
                    currGame.changeGameState(-1);
                    return;
                }
                table.implementCurrentMove();
                //set that piece to moved
                currGame.changeGameState(1);
                System.out.println("Checking for checkmate!");
                if(currGame.getWhite().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                    System.out.println("bleueuedSHASHA");
                    currGame.setGameState(5);
                    table.announceCheckmate("Black Wins!");
                    return;
                }
                System.out.println("No checkmate!");
            }
        }
        else if(gameType==1){
            if(gameState==0&&curr!=null&&curr.getTeam()){
                currGame.setFrom(currY,7-currX);
                currGame.changeGameState(1);
            }
            else if(gameState==1) {
                currGame.setTo(currY,7-currX);
                if (!currGame.isValid()) {
                    currGame.changeGameState(-1);
                    return;
                }
                //updates JFrame as well as game_board
                table.implementCurrentMove();
                //set that piece to moved
                currGame.changeGameState(1);
                //check for checkmate against black
                if(currGame.getBlack().getMoves(currGame.getGame_board(),true,true,false).size()==0){
                    currGame.setGameState(5);
                    table.announceCheckmate("White Wins!");
                    return;
                }
                //Printing transition:
                System.out.println("TRANSITION TO COMPUTER");
                //implement AI move below
                computerThread newComputerThread=new computerThread(currGame,table);


                Thread compAIThread = new Thread(newComputerThread);
                compAIThread.start();
                //

                //check for checkmate against white

            }
        }

    }
}