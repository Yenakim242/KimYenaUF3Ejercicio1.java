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

    // Método para seleccionar una película aleatoria
    public void selectRandomMovie(List<String> movies) {
        Random random = new Random();
        movieTitle = movies.get(random.nextInt(movies.size())).toLowerCase();
    }

    // Método para mostrar el título oculto
    public String displayMaskedTitle() {
        StringBuilder maskedTitle = new StringBuilder();
        for (char c : movieTitle.toCharArray()) {
            if (guessedLetters.contains(c) || !Character.isLetter(c)) {
                maskedTitle.append(c);
            } else {
                maskedTitle.append('_');
            }
        }
        return maskedTitle.toString();
    }

    // Método para advinar una letra
    public boolean guessLetter(char letter) {
        if (movieTitle.indexOf(letter) >= 0) {
            guessedLetters.add(letter);
            points += 10;
            return true;
        } else {
            wrongLetters.add(letter);
            remainingAttempts--;
            points -= 5;
            return false;
        }
    }

    // Método para adivinar el título completo
    public boolean guessMovie(String guess) {
        if (movieTitle.equalsIgnoreCase(guess)) {
            points += 50;
            return true;
        } else {
            remainingAttempts--;
            return false;
        }
    }

    // Método auxiliares
    public boolean isGameOver() {
        return remainingAttempts <= 0 || movieTitle.equals(displayMaskedTitle());
    }

    public int getPoints() {
        return points;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
