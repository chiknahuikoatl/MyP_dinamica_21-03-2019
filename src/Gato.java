import java.util.Scanner;
import java.util.Random;

public abstract class Gato extends Juego{

    Scanner scan;

    /* Para ver si el juego continua*/
    private boolean juegoTerminado;

    /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
      clase concreta*/
    private Tablero tablero;

    // Señala si inicia el usuario o la computadora.
    private int tiroInicial;

    // Marcador del juego.
    private int[] marcador;

    /**
     * Clase privada que crea un tablero para el juego de gato
     */
    private class Tab implements Tablero{

        private String[][] tablero;

        /**
         * Constructor del tablero
         */
        public Tab(){
            tablero = new String[3][3]; //Crea un nuevo tablero 3*3
            for(int i = 0; i < 3; i++){//Llena el tablero con espacios
                for(int j = 0; j < 3; j++){
                    tablero[i][j] = " ";
                }
            }
        }

        /**
         * Representación en cadena del tablero.
         * @return s la representación en cadena del tablero.
         */
        public String toString(){
            String s = "";
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    s += tablero[i][j];
                }
                s += "\n";
            }
            return s;
        }
    }

    public Gato(){
        creaTablero();
        tiroInicial = new Random().nextInt(2); // Crea un número aleatorio para decidir quien inicia.
        int[] marcador = {0,0};
        scan = new Scanner(System.in);
    }

    /**
     * Método que sirve para crear un tablero de cualquier juego que implemente
     * la clase. No todos los juegos requieren un tablero
     *
     * @throws NoRequiereTableroException Si no requiere tablero y se invoca.
     */
    public void creaTablero() throws NoRequiereTableroException{
        tablero = new Tab();
    }

    /**
     * Método que maneja el turno de la computadora.
     */
    public abstract void turnoComputadora();

    /**
     * Método que maneja el turno del usuario.
     */
    public void turnoUsuario(){
        try{
            sop("Elige tus coordenadas para tirar:");
            sop("Coordenada x: ");
            int x = Integer.parseInt(scan.nextLine());
            sop("Coordenada y: ");
            int y = Integer.parseInt(scan.nextLine());
            if(tablero.posicionValida(x,y))
        }
    }

    /**
     * Método que maneja el turno del usuario invitado.
     */
    public void turnoUsuarioInvitado(){
        sop("Método no implementado.");
        sop("Tomen turnos para jugar con la computadora.");
    }


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
    @Override
    public void jugar() throws NoRequiereTableroException {
        while (!juegoTerminado()) {
            if(true){
                sop("Holi");
            }
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
    public void guardaPuntuacion(){
        sop("Puntuación guardada.");
    }

    /**
     * Método que muestra las puntuaciones cuando es invocado.
     */
    public void muestraPuntuaciones(){
        sop("El usuario     ha ganado %i partidas.", marcador[0]);
        sop("La computadora ha ganado %i partidas.", marcador[1]);
    }

    /**
     * Método que muestra el tablero en pantalla. Al utilizar JavaFX, puede
     * sustituirse la impresión en pantalla por mostrar una ventana de la
     * interfaz.
     */
    public void muestraTablero() {
        //Muestra el método toString()
        System.out.println(tablero.toString());
    }

    private void sop(String s){
        System.out.println(s);
    }
}
