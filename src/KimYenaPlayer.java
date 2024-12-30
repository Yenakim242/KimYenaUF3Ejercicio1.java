// Permite que los objetos de esta clase puedan guardarse y recuperarse como flujo de bytes.
import java.io.Serializable;

public class KimYenaPlayer implements Serializable {
    // Atributos privados para encapsular la información.
    // Apodo del jugador.
    private String nickname;
    // Puntuación del jugador.
    private int score;

    // Constructor: inicializa los valores de nickname y score al crear un objeto.
    public KimYenaPlayer(String nickname, int score) {
        // Asignamos el valor del parámetro al atributo.
        this.nickname = nickname;
        // Usamos 'this' para referirnos al atributo de la clase.
        this.score = score;
    }

    // Getter para el atributo nickname.
    public String getNickname() {
        // Devuelve el apodo del jugador.
        return nickname;
    }

    // Getter para el atributo score.
    public int getScore() {
        // Devuelve la puntuación del jugador.
        return score;
    }

    // Setter para el atributo score.
    public void setScore(int score) {
        // Modifica la puntuación del jugador.
        this.score = score;
    }

    // Sobrescribimos el método toString() para personalizar cómo se imprime un objeto.
    @Override
    public String toString() {
        // Construimos una cadena legible con los atributos.
        return "Player{" + "ninkname='" + nickname + '\'' + ", score=" + score + '}';
    }
}
