
import java.util.Scanner;
/**
 * Programa principal del juego
 * @author Helmuth Trefftz
 */
public class Principal {
    public static void main(String [] args) {
        System.out.println("Elige el modo de juego: ");
        System.out.println("Escribe Fantasma para correr el modo Fantasma");
        System.out.println("Escribe Recursion para correr el modo Recursion");
        Scanner in = new Scanner(System.in);
        String entrada = in.nextLine();
        if(entrada.equals("Fantasma")){
        Juego juego = new Juego();
        juego.jugar();
    }
    if(entrada.equals("Recursion")){
        Juego juego = new Juego();
        juego.moverseSolo();
    }
    }
}
