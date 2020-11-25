

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
    Fantasma fantasma;
    Pacman pacman;
    boolean perdiste = false;
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
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego&&!perdiste) {
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int filaFantasma = fantasma.posicion.fila;
            int colFantasma = fantasma.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            boolean teclaInvalida = false;
            switch (linea) {
                // En este punto se inserta el código para las teclas
                // "a" y "d"
                case "w":
                    nuevaFila = fila - 1;
                    break;
                case "s":
                    nuevaFila = fila + 1;
                    break;
                    
                    //añadí las teclas a y d
                case "a":
                    nuevaCol = col-1;
                    break;
                case "d": 
                    nuevaCol = col+1;
                    break;
                default:
                    System.out.println("caracter inválido");
                    teclaInvalida = true;
                    break;
            }
            if (validarCasilla(nuevaFila, nuevaCol)&&!teclaInvalida) {
                Celda anterior = tablero.tablero[fila][col];
                Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
                if(nueva.caracterCelda()=='O'){
                     nueva.esSalida = true;
                    ganaElJuego = true;
                }
                nueva.caracter = pacman;
                anterior.caracter = null;
                nueva.tieneArepita = false;
                
                Celda anteriorFantasma = tablero.tablero[filaFantasma][colFantasma];
                anteriorFantasma.caracter = null;
                if(sePuedeVerFila(nuevaFila)){
                   if(colFantasma<nuevaCol){
                       
                       colFantasma += 2;
                    }else {
                        colFantasma -= 2;
                    }
                }
                else if(sePuedeVerCol(nuevaCol)){
                   if(filaFantasma<nuevaFila){
                       filaFantasma += 2;
                    }else filaFantasma -= 2;
                }
                else if((sePuedeVerFila(nuevaFila))&&(sePuedeVerCol(nuevaCol))){
                    perdiste = true;
                }
                if(validarCasilla(filaFantasma,colFantasma)){
                    Celda nuevaFantasma = tablero.tablero[filaFantasma][colFantasma];
                    nuevaFantasma.caracter = fantasma;
            }
                
                /**
                 * 1. Fantasma ve al Pacman? 
                 *      a. si si, entonces que se mueva dos unidades hacia él
                 *     
                 * 2. Validar Casilla Fantasma con posicion random
                 *      a. Si validarCasilla es false, ent no se debe mover
                 *      b. Si es true, mover fantasma
                 * 
                 */
                
                System.out.println(colFantasma);
                System.out.println(filaFantasma);

                fantasma.posicion = new Posicion(filaFantasma,colFantasma);
                pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                System.out.println("Te quedan: "+pacman.puntosVida+" vidas");
                // Aquí hay que verificar si el jugador ganó el juego
                // Esto es, si llega a una parte del laberinto
                // que es una salida
                 
            }
          if(!ganaElJuego&&!perdiste){ 
                tablero.dibujarTablero();
            linea = in.nextLine();
        }
        }
        if(ganaElJuego) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
        if(perdiste){
            System.out.println("Perdiste");
        }
        
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
        // Aquí hay que verificar que sea un movimiento válido
        // Ver los comentarios del método
        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
        if(nueva.caracterCelda() =='*'){
            return false;
        }
        
        if(nueva.caracterCelda()=='.'){   
            double arepita = Math.random();
        if(arepita<0.5){
             pacman.resta();
             System.out.println("OHHH, has caído en una BOMBA!!");
             if(pacman.puntosVida <=0){
                perdiste = true;
                return false;
                }
        }
         else if(arepita>=0.5){
                pacman.suma();
                System.out.println("Caíste en una arepita buena, tienes un punto de vida mas!!");
            }
        }
        
        return true;
    }
    
    private boolean sePuedeVerFila(int nuevaFila){
        if(nuevaFila == fantasma.posicion.fila){
            return true;
        }
        else{
             System.out.println(nuevaFila);
            System.out.println(fantasma.posicion.fila);
            return false;
        }
    }
    
    private boolean sePuedeVerCol(int nuevaCol){
        if(nuevaCol == fantasma.posicion.col){
            return true;
        }
        else return false;
    }
    public Posicion moverFantasma(int filaF, int colF){
       boolean arriba = false;
       boolean abajo = false;
       boolean izquierda = false;
       boolean derecha = false;
       double valorRandom = Math.random();
       if(valorRandom<0.25){
           arriba = true;
           filaF+=2;
        }else if(valorRandom<=0.25&&valorRandom<0.5){
            abajo = true;
            filaF-=2;
        }else if(valorRandom<=0.5&&valorRandom<0.75){
            izquierda = true;
            colF-=2;
        }else if(valorRandom<=0.75&&valorRandom<1.0){
            derecha = true;
            colF -= 2;
        }
        return new Posicion(filaF,colF);
    }
}
