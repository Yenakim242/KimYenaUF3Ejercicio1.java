// Maneja errores de entrada/salida.
import java.io.IOException;
// Métodos para leer/escribir archivos.
import java.nio.file.Files;
// Representa rutas de archivo.
import java.nio.file.Paths;
import java.util.*;

public class KimYenaGame {
    // Almacena el titulo de la película.
    private String movieTitle;
    // Letras acertadas.
    private Set<Character> guessedLetters = new HashSet<>();
    // Letras falladas.
    private Set<Character> wrongLetters = new HashSet<>();
    // Intentos restantes.
    private int remainingAttempts = 5;
    // Puntos acumulados.
    private int points = 0;

    // Método para cargar peliculas desde un archivo.
    public List<String> loadMoviesFromFile(String filePath) throws IOException {
        // Devuelve las líneas del archivo como lista.
        return Files.readAllLines(Paths.get(filePath));
    }

    // Método para seleccionar una película aleatoria de la lista.
    public void selectRandomMovie(List<String> movies) {
        // Generador de números aleatorios.
        Random random = new Random();
        // Selección aleatoria.
        movieTitle = movies.get(random.nextInt(movies.size())).toLowerCase();
    }

    // Método para mostrar el título oculto (con letras adivinadas reveladas).
    public String displayMaskedTitle() {
        // Construye la cadena.
        StringBuilder maskedTitle = new StringBuilder();
        // Recorre cada carácter del título.
        for (char c : movieTitle.toCharArray()) {
            // Si la letra ya fue adivinada o no es letra.
            if (guessedLetters.contains(c) || !Character.isLetter(c)) {
                maskedTitle.append(c);
            } else {
                // Oculta las letras no adivinadas.
                maskedTitle.append('_');
            }
        }
        // Devuelve el título con las letras ocultas.
        return maskedTitle.toString();
    }

    // Método para advinar una letra.
    public boolean guessLetter(char letter) {
        // Si la letra está en el título.
        if (movieTitle.indexOf(letter) >= 0) {
            // Añade a las letras acertadas.a
            guessedLetters.add(letter);
            // Suma puntos.
            points += 10;
            return true;
        } else {
            // Añade a las letras falladas.
            wrongLetters.add(letter);
            // Reduce intentos.
            remainingAttempts--;
            // Resta puntos.
            points -= 5;
            return false;
        }
    }

    // Método para adivinar el título completo.
    public boolean guessMovie(String guess) {
        // Compara ignorando mayúsculas/minúsculas.
        if (movieTitle.equalsIgnoreCase(guess)) {
            // Suma puntos.
            points += 50;
            return true;
        } else {
            // Reduce intentos.
            remainingAttempts--;
            return false;
        }
    }

    // Método auxiliares.
    public boolean isGameOver() {
        return remainingAttempts <= 0 || movieTitle.equals(displayMaskedTitle());
    }

    // Devuelve los puntos acumulados.
    public int getPoints() {
        return points;
    }

    // Devuelve el título de la peli.
    public String getMovieTitle() {
        return movieTitle;
    }
}
