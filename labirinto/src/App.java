import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String mapPath = null;
        Long seed = null;
        String player = "Player";

        for (String arg : args) {
            if (arg.startsWith("--map=")) mapPath = arg.substring(6);
            else if (arg.startsWith("--seed=")) {
                try { seed = Long.parseLong(arg.substring(7)); } catch (Exception ex) { seed = null; }
            } else if (arg.startsWith("--player=")) player = arg.substring(9).replaceAll("\"", "");
        }

        if (mapPath == null) {
            System.out.println("Uso: java LabirintoLifo --map=./mapas/map1.txt [--seed=3] [--player=Nome]");
            return;
        }

        try {
            MapData map = MapLoader.load(mapPath);
            Game game = new Game(map, seed, player);
            game.run();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
    }
}
