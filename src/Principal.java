/**
 *
 * @author Alejandro Hernández Mora
 * @version 1.0
 */
public class Principal{

    static FabricaDeJuegos fabrica;
    /**
     *
     */
    public static Juego juego;

    /**
     *
     * @param args
     */
    public static void main(String[] args){
    	fabrica = new FabricaDeJuegos();
    	Juego juego = fabrica.juegoNuevo();
    	try{
    	    juego.jugar();
    	}catch (NoRequiereTableroException nrte){
    	    nrte.printStackTrace();
    	}catch(NullPointerException e){
            System.out.println("Has matado al juego.");
        }
    }
}
