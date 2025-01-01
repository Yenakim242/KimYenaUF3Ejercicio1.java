// Importa clases para trabajar con archivos y flujos.
import java.io.*;
import java.util.*;

public class KimYenaGame {
    private List<String> movies;
    private List<KimYenaPlayer> ranking;

    public KimYenaGame() {
        movies = new ArrayList<>();
        ranking = new ArrayList<>();
    }

    // Load movies from peliculas.txt
    public void loadMoviesFromFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                movies.add(line.trim());
            }
        }
    }

    // Get a random movie
    public String getRandomMovie() {
        if (movies.isEmpty()) return null;
        return  movies.get(new Random().nextInt(movies.size()));
    }

    // Load ranking from ranking.dat
    public void loadRanking(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ranking = (List<KimYenaPlayer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Default to empty if file not found
            ranking = new ArrayList<>();
        }
    }

    // Save ranking to ranking.dat
    public void saveRanking(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.out.println("Error save ranking: " + e.getMessage());
        }
    }

    // Add player to ranking
    public void addPlayerToRanking(String name, int score) {
        ranking.add(new KimYenaPlayer(name, score));
        ranking.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
    }

    // Get the top players
    public List<KimYenaPlayer> getTopPlayers() {
        return ranking;
    }
}
