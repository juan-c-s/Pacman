

/**
 * En esta clase se guarda la información acerca del Pacman
 * El Pacman extiende la clase Caracter, y por lo tanto hereda sus
 * atributos y métodos
 * @author Juan Camilo Salazar y Federico Velez
 */
public class Pacman extends Caracter {
    // Cuántos puntos de vida tiene el Pacman
    int puntosVida;

    public Pacman(int tipo, Posicion posicion, char representacion, int puntosVida) {
        super(tipo, posicion, representacion);
        //super is to use parents class methods or atributes
        this.puntosVida = puntosVida;
    }
/**
 * @return puntoVida con la resta respectiva
 */
    public int resta(){
     puntosVida = puntosVida-5;  
     return puntosVida;
    }
/**
 * @return puntoVida con la suma respectiva
 */
        public int suma(){
     puntosVida = puntosVida+1;  
     return puntosVida;
    }

}
