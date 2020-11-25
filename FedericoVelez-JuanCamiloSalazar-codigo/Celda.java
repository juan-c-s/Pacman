

/**
 * En esta clase se guarda la información de una celda y su contenido
 * @author Helmuth Trefftz
 * @author Juan Camilo Salazar
 * @author Federico Velez
 */
public class Celda {
    boolean esMuro;             // True si esta celda es muro (pared)
    boolean esSalida;           // True si esta celda es una salida del laberinto
    boolean tieneArepita;// True si esta celda tiene una arepita
    Caracter caracter;          // Referenia al caracter que ocupa la celda o null si no hay caracter
    boolean esFantasma;

    /**
     * Constructor
     * @param esMuro true si esta celda es muro, false de lo contrario
     * @param esSalida true si esta celda es Salida, false de lo contrario
     * @param tieneArepita true si esta celda tiene una arepita, false de lo contrario
     * @param caracter  referencia al caracter que ocupa la celda, o null si no hay caracter
     */
    public Celda(boolean esMuro, boolean esSalida, boolean tieneArepita, Caracter caracter,boolean esFantasma) {
        this.esMuro = esMuro;
        this.esSalida = esSalida;
        this.tieneArepita = tieneArepita;
        this.caracter = caracter;
        this.esFantasma = esFantasma;
    }
    
    /**
     * Retorna el caracter con el que se representa esta celda
     * @return caracter que representa el contenido de la celda
     */
    public char caracterCelda() {
        if(esMuro) {
            return '*';
        } else if(tieneArepita) {
            return '.';
        } else if(caracter != null) {
            return caracter.representacion;
        } else if (esSalida) {
            return 'O';
        } else if(esFantasma){
            return 'W';
        }
        else {
            return ' ';
        }
    }
    
}