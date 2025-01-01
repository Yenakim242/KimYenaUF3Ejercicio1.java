// Importa clases para trabajar con archivos y flujos.
import java.io.*;
import java.util.*;

public class KimYenaGame {
    private List<KimYenaPlayer> topPlayers;

    public KimYenaGame() {
        this.topPlayers = new ArrayList<>();
    }

    public List<KimYenaPlayer> getTopPlayers() {
        return topPlayers;
    }

    public void addPlayerToRanking(String name, int score){
        topPlayers.add(new KimYenaPlayer(name, score));
        Collections.sort(topPlayers, Comparator.comparingInt(KimYenaPlayer::getScore).reversed());
    }

    public void saveRanking(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (KimYenaPlayer player : topPlayers) {
                writer.write(player.getName() + " : " + player.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("El error al guardar el ranking : " + e.getMessage());
        }
    }

    public List<KimYenaPlayer> loadRanking(String filename) {
        List<KimYenaPlayer> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0].trim(); // Elimina espacios al principio y al final
                    int score = Integer.parseInt(parts[1].trim()); // Elimina espacios antes de convertir
                    players.add(new KimYenaPlayer(name, score));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el ranking: " + e.getMessage());
        }
        return players;
    }

}
