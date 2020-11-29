import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
/**
 * Esta clase maneja el juego
 * Se tiene una referencia al tablero y al pacman
 * En esta clase se hace la interacción con el usuario
 * @author Helmuth Trefftz
 */
import java.util.Scanner;

public class Juego {

    /**
     * El número de puntos iniciales de vida del pacman
     */
    public static final int PUNTOS_VIDA_INICIALES = 10;
    Tablero tablero;
    Pacman pacman;
     ArrayList<Celda> visitadas = new ArrayList<>();

    /**
     * Constructor
     * Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
    }

    /**
     * Interacción con el usuario
     */
    public void jugar() {
        ArrayList<Celda> visitadas = new ArrayList<>();

        tablero.dibujarTablero();
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
           
            if(encontrarRuta(fila,col)) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
            tablero.dibujarTablero();
    }

    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * (iv) Que no haya un caracter en esa posición
     * 
     * @param nuevaFila Fila hacia donde se quiere mover el usuario
     * @param nuevaCol Columna hacia donde se quiere mover el usuario
     * @return true si es una jugada válida, false de lo contrario
     */
    private boolean validarCasilla(int nuevaFila, int nuevaCol) {
        
        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
        if(nueva.caracterCelda() =='*'){
            return false;
        }
        return true;
    }
    public boolean encontrarRuta(int fila, int col){
        try{
            Thread.sleep(100);
        }       
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        
       
        boolean coronamos;
        Celda actual = tablero.tablero[fila][col];
        if(((validarCasilla(fila, col-1))||(validarCasilla(fila+1, col))||(validarCasilla(fila-1, col))||(validarCasilla(fila, col+1)))==false){
            visitadas.remove(tablero.tablero[fila-1][col]);
            visitadas.remove(tablero.tablero[fila+1][col]);
            visitadas.remove(tablero.tablero[fila][col+1]);
            visitadas.remove(tablero.tablero[fila][col-1]);
            
            tablero.tablero[fila][col-1].caracter= null;
            tablero.tablero[fila][col+1].caracter= null;
            tablero.tablero[fila-1][col].caracter= null;
            tablero.tablero[fila+1][col].caracter= null;
        }
        if(tablero.tablero[fila][col].esSalida) return true;
        if(visitadas.contains(actual)){
            return false;
        }else visitadas.add(actual);
        
        //Izquierda
        if(validarCasilla(fila, col-1)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
            
            coronamos = encontrarRuta(fila, col-1);
            tablero.dibujarTablero();
            if(coronamos){
                visitadas.add(actual);
                return true;
            }
        }
        
        // Abajo
        if(validarCasilla(fila+1, col)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
         
            coronamos = encontrarRuta(fila+1, col);
            tablero.dibujarTablero();
            if(coronamos){
                visitadas.add(actual);
                return true;
            }
        }
        
        //Derecha
        if(validarCasilla(fila, col+1)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
            coronamos = encontrarRuta(fila, col+1);
            tablero.dibujarTablero();
            if(coronamos){
                visitadas.add(actual);
                return true;
            }
        }
        
        //Arriba
        if(validarCasilla(fila-1, col)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
            coronamos = encontrarRuta(fila-1, col);
            tablero.dibujarTablero();
            if(coronamos){
                visitadas.add(actual);
                return true;
            }
        }
        
        return false;
    }
}
