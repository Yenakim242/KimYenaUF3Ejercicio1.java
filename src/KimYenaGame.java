// Importa clases para trabajar con archivos y flujos.
import java.io.*;
import java.util.*;

public class KimYenaGame {
    // Nombre del archivo donde se almacenará el ranking.
    private static final String RANKING_FILE = "ranking.dat";

    // Método para cargar el ranking desde el archivo
    public List<KimYenaPlayer> loadRanking() {
        // Crea una lista vacía almacenar el ranking.
        List<KimYenaPlayer> ranking = new ArrayList<>();
        // Intenta abrir el archivo de ranking y leer su contenido.
        try (BufferedReader reader = new BufferedReader(new FileReader(RANKING_FILE))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    ranking.add(new KimYenaPlayer(name, score));
                }
            }
        } catch (FileNotFoundException e) {
            // El archivo no exite; informa y continuará con una lista vacía.
            System.out.println("El archivo de ranking no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            // Maneja otros errores, com problemas de lectura o formato de archivo.
            System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
        }
        // Devuelve la lista de ranking (puede estar vacía si ocurrió un error).
        return ranking;
    }

    // Método para guardar el ranking en el archivo
    public void saveRanking(List<KimYenaPlayer> ranking) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RANKING_FILE))) {
            for (KimYenaPlayer player : ranking) {
                writer.write(player.getName() + "," + player.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            // Maneja errores que ocurran al guardar el archivo.
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}
