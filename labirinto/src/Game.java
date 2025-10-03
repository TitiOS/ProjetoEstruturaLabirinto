// Game.java
import java.util.*;
import java.io.IOException;

public class Game {
    private final MapData map;
    private final Stack<Character> keys;
    private final SinglyLinkedList trapLog;
    private int pr, pc, energy = 1000, score = 0;
    private final String playerName;
    private final Random rnd;
    private final int[][] treasureValues;
    private final boolean[][] revealed;
    private final RankingManager ranking;
    private final SinglyLinkedList actionLog;

    public Game(MapData map, Long seed, String playerName) throws IOException {
        this.map = map;
        this.playerName = playerName;
        this.pr = map.startR; this.pc = map.startC;
        this.keys = new Stack<>(map.keyCapacity);
        this.trapLog = new SinglyLinkedList();
        this.actionLog = new SinglyLinkedList();
        this.rnd = new Random(seed == null ? System.currentTimeMillis() : seed);
        this.treasureValues = new int[map.rows][map.cols];
        this.revealed = new boolean[map.rows][map.cols];
        this.ranking = new RankingManager("ranking.csv");
        generateTreasures();
    }

    private void generateTreasures() {
        for (int r = 0; r < map.rows; r++)
            for (int c = 0; c < map.cols; c++)
                if (map.board[r][c] == '$')
                    treasureValues[r][c] = 10 + rnd.nextInt(41);
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (energy > 0) {
            render();
            System.out.print("Comando: ");
            String line = sc.nextLine().trim().toUpperCase();
            if (line.isEmpty()) continue;
            char cmd = line.charAt(0);
            if (cmd == 'Q') break;
            int dr = 0, dc = 0;
            if (cmd == 'W') dr = -1;
            else if (cmd == 'S') dr = 1;
            else if (cmd == 'A') dc = -1;
            else if (cmd == 'D') dc = 1;
            else continue;
            move(dr, dc);
        }
        System.out.println("Fim de jogo. Pontos: " + score);
        System.out.println("===== LOG DE AÇÕES =====");
        actionLog.printAll();
    }

    private void render() {
        for (int r = 0; r < map.rows; r++) {
            for (int c = 0; c < map.cols; c++) {
                if (r == pr && c == pc) System.out.print('P');
                else System.out.print(map.board[r][c]);
            }
            System.out.println();
        }
        System.out.print("Chaves: ");
        for (int i = 0; i < keys.size(); i++) System.out.print(keys.peek());
        System.out.println(" | Pontos: " + score);
    }

    private void move(int dr, int dc) {
        int nr = pr + dr, nc = pc + dc;
        if (nr < 0 || nr >= map.rows || nc < 0 || nc >= map.cols) return;
        char cell = map.board[nr][nc];
        if (cell == '#') return;

        score -= 1;
        actionLog.add("Andou para (" + nr + "," + nc + ") -1 ponto");

        if (cell >= 'a' && cell <= 'z') {
            if (!keys.isFull()) {
                keys.push(cell);
                actionLog.add("Coletou chave: " + cell + " (+0 pontos)");
            }
            map.board[nr][nc] = '.';
        } else if (cell >= 'A' && cell <= 'Z') {
            if (!keys.isEmpty() && Character.toLowerCase(keys.peek()) == Character.toLowerCase(cell)) {
                keys.pop();
                score += 15;
                actionLog.add("Abriu porta: " + cell + " (+15 pontos)");
                map.board[nr][nc] = '.';
            } else return;
        } else if (cell == '$') {
            if (!revealed[nr][nc]) {
                int pts = treasureValues[nr][nc];
                score += pts;
                actionLog.add("Pegou tesouro: +" + pts + " pontos");
                revealed[nr][nc] = true;
                map.board[nr][nc] = '.';
            }
        } else if (cell == 'T') {
            score -= 20;
            actionLog.add("Caiu em armadilha: -20 pontos");
            trapLog.add("Armadilha em ("+nr+","+nc+")");
        } else if (cell == 'E') {
            int bonus = keys.size()*5;
            score += 100 + bonus;
            actionLog.add("Chegou à saída: +" + (100 + bonus) + " pontos (incluindo bônus de chave)");
            ranking.save(new ScoreEntry[]{new ScoreEntry(playerName, score)});
            System.out.println("Você chegou à saída! Pontos finais: " + score);
        }
        pr = nr; pc = nc;
        energy--;
    }
}
