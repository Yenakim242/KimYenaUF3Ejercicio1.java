import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class KimYenaGame {
    private String movieTitle;
    private Set<Character> guessedLetters = new HashSet<>();
    private Set<Character> wrongLetters = new HashSet<>();
    private int remainingAttempts = 5;
    private int points = 0;

    // Método para cargar peliculas desde un archivo
    public List<String> loadMoviesFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}
