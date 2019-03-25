/**
 *
 * @author Alejandro Hernández Mora
 * @version 1.0
 */
public class NoRequiereTableroException extends Exception{
    final static String MENSAJE = "Este juego no requiere de un tablero"+
	    "\nEste método NO debería ser invocado por una clase de tipo: ";

    /**
     *
     * @param o
     */
    public NoRequiereTableroException(Object o){
	super(MENSAJE+o.getClass().getName());
    }
}
