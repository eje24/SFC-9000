import java.util.Stack;

public class Board {
    private Spot[][] spot_array;
    private Stack<GameMove> moves=new Stack<GameMove>();
    private double[][][] evaluationMatrix;

    public Board(){
        spot_array=new Spot[8][8];
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                spot_array[x][y]=new Spot(x,y);
            }
        }
        initialize_spot_array();
        initializeEvaluationMatrix();
    }
    //getter and setter
    public Spot[][] getSpotArray(){
        return this.spot_array; }
    public Spot getSpot(int x, int y){
        return this.spot_array[x][y];}
    public Piece getPiece(int x, int y){
        return this.spot_array[x][y].getPiece();}
    public double[][][] getEvaluationMatrix() {
        return evaluationMatrix;
    }
    public double getEval(int x, int y, int z){
        return evaluationMatrix[x][y][z];
    }
    public Stack<GameMove> getMoves() {
        return moves;
    }

    public void initialize_spot_array(){
        for(int x=0;x<8;x++){
            spot_array[x][6].setPiece(new Pawn(false));
            spot_array[x][1].setPiece(new Pawn(true ));
        }
        //rooks
        spot_array[0][0].setPiece(new Rook(true));
        spot_array[7][0].setPiece(new Rook(true));
        spot_array[0][7].setPiece(new Rook(false));
        spot_array[7][7].setPiece(new Rook(false));
        //knights
        spot_array[1][0].setPiece(new Knight(true));
        spot_array[6][0].setPiece(new Knight(true));
        spot_array[1][7].setPiece(new Knight(false));
        spot_array[6][7].setPiece(new Knight(false));
        //bishops
        spot_array[2][0].setPiece(new Bishop(true));
        spot_array[5][0].setPiece(new Bishop(true));
        spot_array[2][7].setPiece(new Bishop(false));
        spot_array[5][7].setPiece(new Bishop(false));
        //Queen left
        spot_array[3][0].setPiece(new Queen(true));
        spot_array[3][7].setPiece(new Queen(false));
        //King right
        spot_array[4][0].setPiece(new King(true));
        spot_array[4][7].setPiece(new King(false));
    }
    public void print_board(){
        for(int i=7;i>=0;i--){
            System.out.println(" +-+-+-+-+-+-+-+-+");
            System.out.print(i+1);
            for(int j=0;j<8;j++){
                if(spot_array[j][i].getPiece()==null)
                    System.out.print("|.");
                else
                    System.out.print("|"+spot_array[j][i].getPiece().getChar());
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" +-+-+-+-+-+-+-+-+");
        System.out.println("  A B C D E F G H\n");
    }
    public Piece newConstructor(Piece p){
        Piece res=null;
        if(p instanceof Pawn)
             res=new Pawn(p.getTeam());
        else if(p instanceof Knight)
            res=new Knight(p.getTeam());
        else if(p instanceof Bishop)
            res=new Bishop(p.getTeam());
        else if(p instanceof King)
            res=new King(p.getTeam());
        else if(p instanceof Queen)
            res=new Queen(p.getTeam());
        else if(p instanceof Rook)
            res=new Rook(p.getTeam());
        return res;
    }
    public void initializeEvaluationMatrix(){
        //King-->0
        double[][] kingMatrix={
                {2,3,1,0,0,1,3,2},
                {2,2,0,0,0,0,2,2},
                {-1,-2,-2,-2,-2,-2,-2,-1},
                {-2,-3,-3,-4,-4,-3,-3,-2},
                {-3,-4,-4,-5,-5,-4,-4,-3},
                {-3,-4,-4,-5,-5,-4,-4,-3},
                {-3,-4,-4,-5,-5,-4,-4,-3},
                {-3,-4,-4,-5,-5,-4,-4,-3}
        };
        //Queen-->1
        double[][] queenMatrix={
                {-2,-1,-1,-0.5,-0.5,-1,-1,2},
                {-1,0,0.5,0,0,0.5,0,-1},
                {-1,0,0.5,0.5,0.5,0.5,0,-1},
                {-1,0.5,0.5,0.5,0.5,0.5,0.5,-1},
                {-1,0.5,0.5,0.5,0.5,0.5,0.5,-1},
                {-1,0,0.5,0.5,0.5,0.5,0,-1},
                {-1,0,0.5,0,0,0.5,0,-1},
                {-2,-1,-1,-0.5,-0.5,-1,-1,2}
        };
        //Rook-->2
        double[][] rookMatrix={
                {0,0,0,0.5,0.5,0,0,0},
                {-0.5,0,0,0,0,0,0,-0.5},
                {-0.5,0,0,0,0,0,0,-0.5},
                {-0.5,0,0,0,0,0,0,-0.5},
                {-0.5,0,0,0,0,0,0,-0.5},
                {-0.5,0,0,0,0,0,0,-0.5},
                {0.5,1,1,1,1,1,1,0.5},
                {0,0,0,0,0,0,0,0}
        };
        //Bishop-->3
        double[][] bishopMatrix={
                {-2,-1,-1,-1,-1,-1,-1,-2},
                {-1,0.5,0,0,0,0,0.5,-1},
                {-1,1,1,1,1,1,1,-1},
                {-1,0,1,1,1,1,0,-1},
                {-1,0.5,0.5,1,1,0.5,0.5,-1},
                {-1,0,0.5,1,1,0.5,0,-1},
                {-1,0,0,0,0,0,0,-1},
                {-2,-1,-1,-1,-1,-1,-1,-2}
        };
        //Knight-->4
        double[][] knightMatrix={
            {-5,-4,-3,-3,-3,-3,-4,-5},
            {-4,-2,0,0.5,0.5,0,-2,-4},
            {-3,0.5,1,1.5,1.5,1,0.5,-3},
            {-3,0,1.5,2,2,1.5,0,-3},
            {-3,0.5,1.5,2,2,1.5,0.5,-3},
            {-3,0,1,1.5,1.5,1,0,-3},
            {-4,-2,0,0,0,0,-2,-4},
            {-5,-4,-3,-3,-3,-3,-4,-5}
        };
        //Pawn-->5
        double[][] pawnMatrix={
            {0,0,0,0,0,0,0,0},
            {0.5,1,1,-2,-2,1,1,0.5},
            {0.5,-0.5,-1,0,0,-1,-0.5,0.5},
            {0,0,0,2,2,0,0,0},
            {0.5,0.5,1,2.5,2.5,1,0.5,0.5},
            {1,1,2,3,3,2,1,1},
            {5,5,5,5,5,5,5,5},
            {0,0,0,0,0,0,0,0}
        };
        //Initializing evaluationMatrix
        evaluationMatrix=new double[6][8][8];
        evaluationMatrix[0]=kingMatrix;
        evaluationMatrix[1]=queenMatrix;
        evaluationMatrix[2]=rookMatrix;
        evaluationMatrix[3]=bishopMatrix;
        evaluationMatrix[4]=knightMatrix;
        evaluationMatrix[5]=pawnMatrix;
    }
    public void makeMove(int fromX, int fromY, int toX, int toY){
        Piece newPiece=newConstructor(spot_array[toX][toY].getPiece());
        spot_array[toX][toY].setPiece(spot_array[fromX][fromY].getPiece());
        spot_array[fromX][fromY].setPiece(null);
        //add to stack
        moves.push(new GameMove(fromX,fromY,toX,toY,newPiece));
    }
    public void undoMove(){
        if(moves.empty())
            return;
        GameMove recentMove=moves.peek();
        int fromX=recentMove.getFromX();
        int fromY=recentMove.getFromY();
        int toX=recentMove.getToX();
        int toY=recentMove.getToY();
        spot_array[fromX][fromY].setPiece(spot_array[toX][toY].getPiece());
        spot_array[toX][toY].setPiece(recentMove.getPiece());
        moves.pop();
        ///
    }
    public boolean inCheck(boolean team){
        //find King's position
        int toX=0,toY=0;
        boolean found=false;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(getPiece(i,j)!=null&&getPiece(i,j).getTeam()==team&&getPiece(i,j).getValue()==9000){
                    toX=i; toY=j;
                    found=true;
                    break;
                }
            }
            if(found)
                break;
            if(i==7) {
                return false;
            }
        }
        //search all other pieces
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(getPiece(i,j)!=null&&getPiece(i,j).getTeam()!=team){
                    if(getPiece(i,j).isValid(i,j,toX,toY,this,false)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        Board b=new Board();
        b.print_board();
    }



}
