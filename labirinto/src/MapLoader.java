// MapLoader.java
import java.nio.file.*;
import java.util.*;

public class MapLoader {
    public static MapData load(String path) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(path));
        List<String> filtered = new ArrayList<>();
        for (String l : lines) if (!l.startsWith(";")) filtered.add(l);
        String[] header = filtered.get(0).split(" ");
        int rows = Integer.parseInt(header[0]);
        int cols = Integer.parseInt(header[1]);
        int cap = Integer.parseInt(header[2]);
        char[][] board = new char[rows][cols];
        int sR = 0, sC = 0;
        for (int i = 0; i < rows; i++) {
            String row = filtered.get(i + 1);
            for (int j = 0; j < cols; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'S') { sR = i; sC = j; }
            }
        }
        return new MapData(rows, cols, cap, board, sR, sC);
    }
}
