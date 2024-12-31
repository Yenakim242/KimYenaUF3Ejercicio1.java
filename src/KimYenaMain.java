import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class KimYenaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KimYenaGame game = new KimYenaGame();

        System.out.println("¡Bienvenido al juego de adivinar películas!");
        System.out.print("Introduce tu nombre de usuario: ");
        String nickname = scanner.nextLine();

        try {
            // Cargar las películas
            List<String> movies = game.loadMoviesFromFile("peliculas.txt");
            game.selectRandomMovie(movies);

            // Bucle principal del juego
            while (!game.isGameOver()) {
                System.out.println("\nPelícula: " + game.displayMaskedTitle());
                System.out.println("[1] Adivinar una letra");
                System.out.println("[2] Adivinar el título completo");
                System.out.println("[3] Salir");

                System.out.print("Elige una opción: ");
                int option = scanner.nextInt();
                // Consumir el salto de línea
                scanner.nextLine();

                switch (option) {
                    case 1 -> {
                        System.out.print("Introduce una letra: ");
                        char letter = scanner.nextLine().toLowerCase().charAt(0);
                        if (!Character.isLetter(letter)) {
                            System.out.println("¡Debes introducir una letra válida!");
                        } else if (game.guessLetter(letter)) {
                            System.out.println("¡La letra está en el título!");
                        } else {
                            System.out.println("La letra no está en el título");
                        }
                    }
                    case 2 -> {
                        System.out.print("Introduce el título completo: ");
                        String guess = scanner.nextLine();
                        if (game.guessMovie(guess)) {
                            System.out.println("¡Correcto! Has adivinado la película.");
                            break;
                        } else {
                            System.out.println("El título no es correcto.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Saliendo del juego...");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }

            // Fin del juego
            System.out.println("El juego ha terminado. Puntos obtenidos: " + game.getPoints());
            System.out.println("La película era: " + game.getMovieTitle());
        } catch (IOException e) {
            System.out.println("Error al cargar las películas: " + e.getMessage());
        }

        scanner.close();
    }
}