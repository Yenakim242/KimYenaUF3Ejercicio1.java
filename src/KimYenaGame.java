import  java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KimYenaGame {
    private final String rankingFile = "ranking.dat";

    // Método para cargar el ranking desde el archivo
    public List<KimYenaPlayer> loadRanking() {
        List<KimYenaPlayer> ranking = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rankingFile))){
            ranking = (List<KimYenaPlayer>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de ranking no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el ranking: " + e.getMessage());
        }
        return ranking;
    }

    // Método para guardar el ranking en el archivo
    public void saveRanking(List<KimYenaPlayer> ranking) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rankingFile))){
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}
