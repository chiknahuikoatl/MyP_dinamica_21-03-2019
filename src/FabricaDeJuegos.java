import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alejandro Hernández Mora
 * @version 1.0
 */
public class FabricaDeJuegos{
    Scanner scan;

    /**
     *  Constructor simple de la clase
     */
    public FabricaDeJuegos(){
        scan = new Scanner(System.in);
    }

    /**
     *  Factory method que genera instancias de juegos nuevos.
     *  El método se encarga de crear un juego dependiendo de los parámetros
     *  que recibe del usuario. Utiliza un scanner para leer la entrada.
     * @return Una instancia de un juego concreto.
     */
    public Juego juegoNuevo(){
        Juego juego = null;
        menuDeJuegos();
        try{
            int i = Integer.parseInt(scan.nextLine());
            switch(i){
            	case 1:
                    juego = new PPT();
                    break;
                case 2:
                    sop("Selecciona el tamaño de tu tablero: ");
                    i = Integer.parseInt(scan.nextLine());
                    juego = new Submarinos(i);
                    break;
                case 3:
                    juego = new Gato();
                    break;
                case 4:
                    juego = new JuegoDummy();
                    break;
            	default:
                    break;
            }
        }catch(NumberFormatException e){
                sop("Por favor introduce un número válido");
        }catch(NoRequiereTableroException e){
            sop("El juego no requiere tablero.");
        }
        return juego;
    }

    /**
     * Método "auxiliar" que imprime los juegos disponibles para jugar.
     * No recibe parámetros ni devuelve nada.
     */
    private void menuDeJuegos(){
        sop("*************************************************");
        sop("             ----Menu de Juegos----"              );
        sop("*************************************************");
        sop("Selecciona la opción del juego que quieres jugar:");
        sop("1) Piedra, papel o tijeras.");
        sop("2) Submarinos.");
        sop("3) Gato.");
        sop("4) Juego Dummy");
        sop("Presiona otro número para salir.");
    }

    private void sop(String s){
        System.out.println(s);
    }
}
