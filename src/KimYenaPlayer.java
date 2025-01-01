import java.io.Serializable;

public class KimYenaPlayer implements Serializable {
    // Atributos privados para encapsular la información.
    // Apodo del jugador.
    private String name;
    // Puntuación del jugador.
    private int score;

    // Constructor: inicializa los valores de nickname y score al crear un objeto.
    public KimYenaPlayer(String name, int score) {
        // Asignamos el valor del parámetro al atributo.
        this.name = name;
        // Usamos 'this' para referirnos al atributo de la clase.
        this.score = score;
    }

    // Getter para el atributo nickname.
    public String getName() {
        // Devuelve el apodo del jugador.
        return name;
    }

    // Getter para el atributo score.
    public int getScore() {
        // Devuelve la puntuación del jugador.
        return score;
    }

    // Sobrescribimos el método toString() para personalizar cómo se imprime un objeto.
    @Override
    public String toString() {
        // Construimos una cadena legible con los atributos.
        return name + ": " + score + " puntos";
    }
}
