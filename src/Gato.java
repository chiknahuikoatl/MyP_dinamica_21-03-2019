import java.util.Scanner;
import java.util.Random;

public abstract class Gato extends Juego{

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
         * posicionValida. Metodo que determina si un par de 
         * coordenadas pueden ser usadas en el juego.
         * @param y Valor para acceder en el tablero
         * en la primer coordenada.
         * @param x Valor para acceder al tablero 
         * en la segunda coordenada.
         * @return Verdadero si no existe un elemento en esa posicion,
         * Falso si no puede accesar en esa posicion o esta ocupada.
         */
        public boolean posicionValida(int y, int x){
            if(x<0 || y<0 ||  y>2 || x > 2 || tablero[y][x] !=" "){
                return false;
            }
            return true;
        }

        /**
         * tirada. Metodo que agrega al tablero una tirada.
         * Si las coordenadas no son validas, no hará nada.
         * @param y Coordenada y en el arreglo, y < 3.
         * @param x Coordenada x en el arreglo, x < 3.
         * @param s Simbolo del jugador.
         */
        public void tirada(int y, int x, String s){
            if(posicionValida(y, x)){
                tablero[y][x] = s;
            }else{
                sop("La posición de tu tirada es invalida.");
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
        tablero = new Tab();
        // Crea un número aleatorio para decidir quien inicia.
        tiroInicial = new Random().nextInt(2);
        int[] marcador = {0,0};
    }

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
    public void turnoComputadora(){
        Random r = new Random();
        int i = 0;
        int j = 0;
        do {
            i = r.nextInt(3);
            j = r.nextInt(3);                
        } while (!tablero.posicionValida(i,j));
        tablero.tirada(i, j, "O");
    }

    /**
     * Método que maneja el turno del usuario.
     */
    public abstract void turnoUsuario();

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

    private void sop(String s){
        System.out.println(s);
    }
}
