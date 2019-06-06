import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChessGameDemo {
    JFrame table = new JFrame("Super Fantastic Chess 9000");
    JPanel board = new JPanel();
    JButton buttonArray[][];
    Game currGame;

    public ChessGameDemo() {
        //setup board
        board.setLayout(new GridLayout(8,8,0,0));
        currGame=new Game(false);
        buttonArray=new JButton[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                buttonArray[i][j]=new JButton();
                JButton currButton=buttonArray[i][j];
                if((i+j)%2==0)
                    currButton.setBackground(Color.white);
                else
                    currButton.setBackground(Color.black);
                //currButton.setContentAreaFilled(false);
                if(i>5||i<2)
                    currButton.setIcon(currGame.getGame_board().getPiece(j,7-i).getImageIcon());
                currButton.setOpaque(false);
                board.add(currButton);
            }
        }
        //setup toolbar
        JToolBar toolBar=new JToolBar();
        toolBar.setFloatable(false);
        JButton newPVP=new JButton("New PvP Game");
        JButton newComputer=new JButton("New Vs. Computer Game");
        JButton quitButton=new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonArray[4][4].setIcon((new Knight(true).getImageIcon()));
            }
        });
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
    public void initializePieces(Board game_board){

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        new ChessGameDemo();
    }

}