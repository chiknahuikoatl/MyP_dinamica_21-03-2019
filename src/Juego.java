/**
 *
 * @author Alejandro Hernández Mora
 * @version 1.0
 */

/** Interfaz para modelar juegos * */
public abstract class Juego {

    /* Para ver si el juego continua*/
    private boolean juegoTerminado;

    /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
      clase concreta*/
    private Tablero tablero;

    /**
     * Método que sirve para crear un tablero de cualquier juego que implemente
     * la clase. No todos los juegos requieren un tablero
     *
     * @throws NoRequiereTableroException Si no requiere tablero y se invoca.
     */
    public abstract void creaTablero() throws NoRequiereTableroException;

    /**
     * Método que maneja el turno de la computadora.
     */
    public abstract void turnoComputadora();

    /**
     * Método que maneja el turno del usuario.
     */
    public abstract void turnoUsuario();

    /**
     * Método que maneja el turno del usuario invitado.
     */
    public abstract void turnoUsuarioInvitado();

    
    /**
     * Método que maneja toda la partida de la instancia del juego. Aquí se
     * pueden generar los ciclos para distintas partidas jugadas.
     *
     * Aquí podría ir una implementación del flujo general de un juego en
     * potencial para todos los juegos. Si no funciona para algún juego, se
     * puede sobreescribir con @Override en la clase concreta.
     *
     * @throws NoRequiereTableroException
     */
    public void jugar() throws NoRequiereTableroException {
        //Comenta la siguiente línea para que puedas ver el ejemplo
        //creaTablero();
        while (!juegoTerminado()) {
            System.out.println("Seguimos jugando");
            //Implementen un volado para que el primer turno sea aleatorio.
            turnoUsuario();
            turnoComputadora();
            muestraTablero();
            juegoTerminado = true;
        }
        System.out.println("Fin del juego");
    }

    /**
     * Método invocado para saber si el juego ha terminado.
     *
     * @return
     */
    public boolean juegoTerminado() {
        return juegoTerminado;
    }

    /**
     * Método que guarda la puntuación del usuario que está jugando.
     */
    public abstract void guardaPuntuacion();

    /**
     * Método que muestra las puntuaciones cuando es invocado.
     */
    public abstract void muestraPuntuaciones();

    /**
     * Método que muestra el tablero en pantalla. Al utilizar JavaFX, puede
     * sustituirse la impresión en pantalla por mostrar una ventana de la
     * interfaz.
     */
    public void muestraTablero() {
        //Muestra el método toString()
        System.out.println(tablero.toString());
    }
}
