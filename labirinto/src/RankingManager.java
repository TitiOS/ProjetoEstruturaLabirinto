import java.io.*;
import java.util.*;

public class RankingManager {
    private final String file;

    public RankingManager(String file) {
        this.file = file;
    }

    public ScoreEntry[] load() {
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(file));
            ScoreEntry[] entries = new ScoreEntry[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] p = lines.get(i).split(",");
                entries[i] = new ScoreEntry(p[0], Integer.parseInt(p[1]));
            }
            return entries;
        } catch (Exception e) {
            return new ScoreEntry[0];
        }
    }

    public void save(ScoreEntry[] entries) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (ScoreEntry s : entries) pw.println(s.player + "," + s.score);
        } catch (Exception ignored) {}
    }
}
