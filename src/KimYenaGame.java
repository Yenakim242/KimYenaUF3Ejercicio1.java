// Importa clases para trabajar con archivos y flujos.
import java.io.*;
// Importa la clase ArrayList.
import java.util.ArrayList;
// Importa la interfaz List.
import java.util.List;

public class KimYenaGame {
    // Nombre del archivo donde se almacenará el ranking.
    private final String rankingFile = "ranking.dat";

    // Método para cargar el ranking desde el archivo
    public List<KimYenaPlayer> loadRanking() {
        // Crea una lista vacía almacenar el ranking.
        List<KimYenaPlayer> ranking = new ArrayList<>();
        // Intenta abrir el archivo de ranking y leer su contenido.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rankingFile))){
            // Deserializa el objeto y lo convierte a una lista de jugadores.
            ranking = (List<KimYenaPlayer>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo no exite; informa y continuará con una lista vacía.
            System.out.println("Archivo de ranking no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            // Maneja otros errores, como problemas de lectura o formato de archivo.
            System.out.println("Error al cargar el ranking: " + e.getMessage());
        }
        // Devuelve la lista de ranking (puede estar vacía si ocurrió un error).
        return ranking;
    }

    // Método para guardar el ranking en el archivo
    public void saveRanking(List<KimYenaPlayer> ranking) {
        // Intenta escribir el ranking en el archivo.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rankingFile))){
            // Serializa la lista de jugadores y la guarda en el archivo.
            oos.writeObject(ranking);
        } catch (IOException e) {
            // Maneja errores que ocurran al guardar el archivo.
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}
