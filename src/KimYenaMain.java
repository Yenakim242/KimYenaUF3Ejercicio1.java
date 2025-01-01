import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class KimYenaMain {
    public static void main(String[] args) {
        // Crea una instancia de KimYenaGame para gestionar el juego
        KimYenaGame game = new KimYenaGame();
        // Carga el ranking desde el archivo
        List<KimYenaPlayer> ranking = game.loadRanking("ranking.dat");

        Scanner scanner = new Scanner(System.in);
        List<String> movieTitles = loadMoviesFromFile("peliculas.txt");

        if (movieTitles.isEmpty()) {
            System.out.println("No se encontraron películas en el archivo. Asegúrate de que el archivo 'peliculas.txt' existe y contiene títulos.");
            return;
        }

        // Escoge un título aleatrio de la lista.
        String movieTitle = movieTitles.get(new Random().nextInt(movieTitles.size())).toLowerCase();
        String maskedTitle = maskTitle(movieTitle);
        Set<Character> guessedLetters = new HashSet<>();
        List<Character> wrongLetters = new ArrayList<>();

        // Puntuación inicial y número de intentos
        int points = 0;
        int remainingTurns = 10;
        boolean won = false;

        // Mostrar el título del juego
        System.out.println("\uD83C\uDFAF\uD83C\uDFAF\uD83C\uDFAF Guess the Movie \uD83C\uDFAF\uD83C\uDFAF\uD83C\uDFAF");
        System.out.println("El título de la película tiene " + movieTitle.length() + " caracteres (incluyendo espacios y puntuación).");

        // Bucle principal del juego, continuará hasta que se acaban los intentos
        while (remainingTurns > 0) {
            // Muestra el título enmascarado, los intentos restantes y la puntuación
            System.out.println("\nTítulo: " + maskedTitle);
            System.out.println("Intentos restantes: " + remainingTurns);
            System.out.println("Puntuación: " + points);
            System.out.println("Letras incorrectas: " + wrongLetters);
            System.out.println("Elige una opción:");
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el título");
            System.out.println("[3] Salir");

            try {
                // Obtener la opción del usuario
                int choice = scanner.nextInt();
                // Limpia el buffer del scanner
                scanner.nextLine();

                switch (choice) {
                    // Opción 1: Adivinar una letra
                    case 1 -> {
                        System.out.print("Introduce una letra: ");
                        String input = scanner.nextLine().toLowerCase();
                        // Validación de que la entrada es una letra válida
                        if (!Pattern.matches("[a-z]", input)) {
                            System.out.println("Debes introducir una letra válida.");
                            continue;
                        }
                        char guessedLetter = input.charAt(0);

                        // Verifica si ya se ha adivinado esa letra
                        if (guessedLetters.contains(guessedLetter)) {
                            System.out.println("Ya has adivinado esa letra. Intenta con otra.");
                            continue;
                        }

                        // Añade la letra adivinada al conjunto de letras adivinadas
                        guessedLetters.add(guessedLetter);

                        // Verifica si la letra está en el título
                        if (movieTitle.contains(String.valueOf(guessedLetter))) {
                            maskedTitle = revealLetter(movieTitle, maskedTitle, guessedLetter);
                            // Suma puntos por adivinar correctamente.
                            points += 10;
                            System.out.println("¡Correcto! La letra está en el título.");
                        } else {
                            // Añade la letra incorrecta a la lista
                            wrongLetters.add(guessedLetter);
                            // Resta puntos por error
                            points -= 10;
                            // Resta un intento
                            remainingTurns--;
                            System.out.println("Incorrecto. La letra no está en el título.");
                        }
                    }

                    // Opción 2: Adivinar el título completo
                    case 2 -> {
                        System.out.print("Introduce el título completo: ");
                        String guess = scanner.nextLine().toLowerCase();
                        if (guess.equals(movieTitle)) {
                            // Suma puntos si se adivina el título
                            points += 20;
                            won = true;
                            // Fin del juego si se adivina el título
                            remainingTurns = 0;
                        } else {
                            // Resta puntos si el título no conincide
                            points -= 20;
                            // Fin del juego si no coincide el título
                            remainingTurns = 0;
                        }
                    }
                    // Opción 3: Salir del juego
                    case 3 -> {
                        System.out.println("Saliendo del juego...");
                        // Termiina el juego
                        remainingTurns = 0;
                    }
                    // Opción no válida
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                // Limpiar el buffer en caso de error
                scanner.nextLine();
            }

            // Verifica si el jugador ha adivinado todo el título
            if (maskedTitle.equals(movieTitle)) {
                won = true;
                break;
            }
        }

        // Fin del juego, muestra el resultado
        System.out.println("\n--- Fin del Juego ---");
        System.out.println("El título era: " + movieTitle);
        System.out.println("Puntuación final: " + points);
        if (won) {
            System.out.println("¡Has ganado!");
        } else {
            System.out.println("Has perdido.");
        }

        // Si la puntuación es suficiente, permite el jugador añadir su nombre al ranking
        if (points > 0 && rankingEligible(points, game.getTopPlayers())) {
            String nickname;
            do {
                System.out.print("Introduce un nickname para el ranking: ");
                nickname = scanner.nextLine();
            } while (nickNameExists(nickname, game.getTopPlayers()));

            // Añadir al ranking
            game.addPlayerToRanking(nickname, points);
            // Guardar al ranking
            game.saveRanking("ranking.dat");
        } else {
            System.out.println("Tu puntuación no es suficiente para entrar al ranking.");
        }

        // Muestra el ranking final
        System.out.println("\n--- Ranking ---");
        for (KimYenaPlayer player : ranking) {
            System.out.println(player);
        }

        // Cierra el scanner
        scanner.close();
    }

    // Método para enmascarar el título de la película con asteriscos, mantenimiendo espacios y puntuación
    private static String maskTitle(String title) {
        StringBuilder masked = new StringBuilder();
        for (char c : title.toCharArray()) {
            if (Character.isLetter(c)) {
                // Sustituye letras por asteriscos
                masked.append('*');
            } else {
                // Mantén espacios y puntuación
                masked.append(c);
            }
        }
        return masked.toString();
    }

    private static List<String> loadMoviesFromFile(String filename) {
        List<String> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                movies.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return movies;
    }

    // Método para revelar una letra en el título enmascarado
    private static String revealLetter(String original, String masked, char letter) {
        char[] maskedChars = masked.toCharArray();
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == letter) {
                // Reemplaza el asterisco con la letra correcta
                maskedChars[i] = letter;
            }
        }
        return new String(maskedChars);
    }

    // Método que verifica si la puntuación del jugador es suficiente entrar al ranking
    private static boolean rankingEligible(int points, List<KimYenaPlayer> ranking) {
        return ranking.size() < 5 || points > ranking.get(ranking.size() - 1).getScore();
    }

    // Método que verifica si el nickname ya existe en el ranking
    private static boolean nickNameExists(String nickname, List<KimYenaPlayer> ranking) {
        return ranking.stream().anyMatch(player -> player.getName().equalsIgnoreCase(nickname));
    }
}