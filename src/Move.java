public class Move {
    private int dx;
    private int dy;
    private int max_dist;

    public Move(int dX, int dY, int md){
        dx=dX;
        dy=dY;
        max_dist=md;
    }

    public int getX(){
        return this.dx;
    }
    public int getY() { return this.dy; }
    public int getMax_dist(){
        return this.max_dist;
    }
}
