
public class Main {
    public static void main(String[] args) {
        Game game = new Game(true);
        while (true) {
            game.nextMove();
            game.printBoardValues();
            if (game.getBlack().boardValue(game.getGame_board()) < -1000) {
                System.out.println("WHITE WINS!");
                break;
            }
            else if (game.getWhite().boardValue(game.getGame_board()) < -1000) {
                System.out.println("BLACK WINS!");
                break;
            }
        }
        System.out.println("\nThank you for playing!\n");
    }
}

