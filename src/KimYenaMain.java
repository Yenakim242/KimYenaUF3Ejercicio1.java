import java.util.*;
import java.util.regex.Pattern;

public class KimYenaMain {
    public static void main(String[] args) {
        // Crea una instancia de KimYenaGame
        KimYenaGame game = new KimYenaGame();
        // Carga el ranking desde el archivo
        List<KimYenaPlayer> ranking = game.loadRanking();

        Scanner scanner = new Scanner(System.in);
        // Título de prueba
        String movieTitle = "joker";
        // Título enmascarado
        String maskedTitle = maskTitle(movieTitle);
        Set<Character> guessedLetters = new HashSet<>();
        List<Character> wrongLetters = new ArrayList<>();

        int points = 0;
        int remainingTurns = 10;
        boolean won = false;

        System.out.println("\uD83C\uDFAF\uD83C\uDFAF\uD83C\uDFAF Guess the Movie \uD83C\uDFAF\uD83C\uDFAF\uD83C\uDFAF");
        System.out.println("El título de la película tiene " + movieTitle.length() + " caracteres (incluyendo espacios y puntuación).");

        while (remainingTurns > 0) {
            System.out.println("\nTítulo: " + maskedTitle);
            System.out.println("Intentos restantes: " + remainingTurns);
            System.out.println("Puntuación: " + points);
            System.out.println("Letras incorrectas: " + wrongLetters);
            System.out.println("Elige una opción:");
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el título");
            System.out.println("[3] Salir");

            try {
                int choice = scanner.nextInt();
                // Limpia la entrada
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Introduce una letra: ");
                        String input = scanner.nextLine().toLowerCase();
                        if (!Pattern.matches("[a-z]", input)) {
                            System.out.println("Debes introducir una letra válida.");
                            continue;
                        }
                        char guessedLetter = input.charAt(0);

                        if (guessedLetters.contains(guessedLetter)) {
                            System.out.println("Ya has adivinado esa letra. Intenta con otra.");
                            continue;
                        }

                        guessedLetters.add(guessedLetter);

                        if (movieTitle.contains(String.valueOf(guessedLetter))) {
                            maskedTitle = revealLetter(movieTitle, maskedTitle, guessedLetter);
                            points += 10;
                            System.out.println("¡Correcto! La letra está en el título.");
                        } else {
                            wrongLetters.add(guessedLetter);
                            points -= 10;
                            remainingTurns--;
                            System.out.println("Incorrecto. La letra no está en el título.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Introduce el título completo: ");
                        String guess = scanner.nextLine().toLowerCase();
                        if (guess.equals(movieTitle)) {
                            points += 20;
                            won = true;
                            remainingTurns = 0;
                        } else {
                            points -= 20;
                            remainingTurns = 0;
                        }
                    }
                    case 3 -> {
                        System.out.println("Saliendo del juego...");
                        remainingTurns = 0;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                // Limpiar buffer
                scanner.nextLine();
            }

            if (maskedTitle.equals(movieTitle)) {
                won = true;
                break;
            }
        }

        System.out.println("\n--- Fin del Juego ---");
        System.out.println("El título era: " + movieTitle);
        System.out.println("Puntuación final: " + points);
        if (won) {
            System.out.println("¡Has ganado!");
        } else {
            System.out.println("Has perdido.");
        }

        if (points > 0 && rankingEligible(points, ranking)) {
            String nickname;
            do {
                System.out.print("Introduce un nickname para el ranking: ");
                nickname = scanner.nextLine();
            } while (nickNameExists(nickname, ranking));

            ranking.add(new KimYenaPlayer(nickname, points));
            ranking.sort(Comparator.comparingInt(KimYenaPlayer::getScore).reversed());
            if (ranking.size() > 5) {
                ranking.remove(ranking.size() - 1);
            }
            game.saveRanking(ranking);
        } else {
            System.out.println("Tu puntuación no es suficiente para entrar al ranking.");
        }

        System.out.println("\n--- Ranking ---");
        for (KimYenaPlayer player : ranking) {
            System.out.println(player);
        }

        scanner.close();
    }

    private static String maskTitle(String title) {
        return title.replaceAll("[a-zA-Z]", "*");
    }

    private static String revealLetter(String original, String masked, char letter) {
        char[] maskedChars = masked.toCharArray();
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == letter) {
                maskedChars[i] = letter;
            }
        }
        return new String(maskedChars);
    }

    private static boolean rankingEligible(int points, List<KimYenaPlayer> ranking) {
        return ranking.size() < 5 || points > ranking.get(ranking.size() - 1).getScore();
    }

    private static boolean nickNameExists(String nickname, List<KimYenaPlayer> ranking) {
        return ranking.stream().anyMatch(player -> player.getName().equalsIgnoreCase(nickname));
    }
}