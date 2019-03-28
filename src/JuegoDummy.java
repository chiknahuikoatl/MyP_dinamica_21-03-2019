/**
 * Clase muestra de las clases concretas de juegos. Las definiciones de las
 * clases del juego no necesariamente deben ser clases internas, acá les
 * dejo una muestra para su guía únicamente
 */

public class JuegoDummy extends Juego{

    /**
     * Constructor
     */
    public JuegoDummy(){
    System.out.println("Se creó un juego Dummy");
}
// este método no hace nada porque no hay tablero para este juego.
    @Override
public void creaTablero() throws NoRequiereTableroException{
    throw new NoRequiereTableroException(this);
}

    /**
     * Método que maneja el turno de la computadora.
     */
    @Override
    public void turnoComputadora(){
    System.out.println("Es turno de la computadora");
}

    /**
     * Método que maneja el turno del usuario.
     */
    @Override
    public void turnoUsuario(){
    System.out.println("Es turno del usuario");
}

    /**
     * Método que maneja el turno del usuario invitado.
     */
    @Override
    public void turnoUsuarioInvitado(){
    System.out.println("Es turno del usuarioInvitado");
}

    /**
     * Método que guarda la puntuación del usuario que está jugando.
     */
    @Override
    public void guardaPuntuacion(){
}

    /**
     * Método que muestra las puntuaciones en algún momento del juego.
     */
    @Override
    public void muestraPuntuaciones(){
}
    /*Aunque la clase abstracta supone un tablero, en este
      caso simplemente se manda un mensaje, se vale sobreescribir el
      método*/

        /**
         * Método que muestra el tablero en pantalla.
         * Al utilizar JavaFX, puede sustituirse la impresión en pantalla por
         * mostrar una ventana de la interfaz.
         */

    @Override
    public void muestraTablero(){
        String s = "******************\n"+
        "Este es el tablero\n"+
        "******************";
        System.out.println(s);
    }
}
