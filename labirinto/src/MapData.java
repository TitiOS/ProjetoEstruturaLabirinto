// MapData.java
public class MapData {
    public final int rows;
    public final int cols;
    public final int keyCapacity;
    public final char[][] board;
    public final int startR, startC;

    public MapData(int rows, int cols, int keyCapacity, char[][] board, int startR, int startC) {
        this.rows = rows;
        this.cols = cols;
        this.keyCapacity = keyCapacity;
        this.board = board;
        this.startR = startR;
        this.startC = startC;
    }
}
