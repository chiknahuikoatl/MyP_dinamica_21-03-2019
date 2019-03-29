import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public class Gato extends Juego{

    Scanner scan;

    /* Para ver si el juego continua*/
    private boolean juegoTerminado;

    /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
      clase concreta*/
    private Tablero tablero;

    private Tab t;

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
            if(x<0 || y<0 ||  y>2 || x > 2 || tablero[y][x] != " "){
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
                victoria();
            }else{
                sop("La posición de tu tirada es invalida.");
            }
        }

        /**
         * victoria. Revisa si el tablero actual tiene algun
         * elemento que cumpla la condición de que existan
         * tres elementos iguales juntos.
         * @return Verdadero si ha gando alguno.
         */
        public boolean victoria(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(tablero[i][j]!=" "){
                        String tiro = tablero[i][j];
                        //Revisa si cumple la condición vertical
                        if((i+1)<3){
                            if (tablero[i+1][j]==tiro){
                                if((i+2)<3){
                                    if(tablero[i+2][j]==tiro){
                                        sop("Ha ganado " +tiro);
                                        return true;
                                    }
                                }
                            }
                        }
                        //Revisa si cumple la condición horizontal
                        if((j+1)<3){
                            if (tablero[i][j+1]==tiro){
                                if((j+2)<3){
                                    if(tablero[i][j+2]==tiro){
                                        sop("Ha ganado " +tiro);
                                        return true;
                                    }
                                }
                            }
                        }
                        //Revisa si cumple la condición en diagonal abajo
                        if((i+1)<3 && (j+1)<3){
                            if (tablero[i+1][j+1]==tiro){
                                if((i+2)<3 && (j+2)<3){
                                    if(tablero[i+2][j+2]==tiro){
                                        sop("Ha ganado " +tiro);
                                        return true;
                                    }
                                }
                            }
                        }
                        //Revisa si cumple la condición en diagonal arriba
                        if((i-1)>=0 && (j+1)<3){
                            if (tablero[i-1][j+1]==tiro){
                                if((i-2)>=0&& (j+2)<3){
                                    if(tablero[i-2][j+2]==tiro){
                                        sop("Ha ganado " +tiro);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
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
        try {
            creaTablero();            
        } catch (NoRequiereTableroException e) {
            sop("Este juego si requiere tablero.");
        }
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
        tablero =  new Tab();
        t = (Tab) tablero;
    }

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
        } while (!t.posicionValida(i,j));
        t.tirada(i, j, "O");
    }

    /**
     * Método que maneja el turno del usuario.
     */
    public void turnoUsuario(){
        try{
            while(true){
                sop("Elige tus coordenadas para tirar:");
                sop("Coordenada x: ");
                int x = Integer.parseInt(scan.nextLine());
                sop("Coordenada y: ");
                int y = Integer.parseInt(scan.nextLine());
                if(t.posicionValida(x,y)){
                    t.tirada(x,y,"X");
                    break;
                }else{
                    sop("Tus \"coordenadas\" no son válidas. Vuelve a intentarlo.");
                }
            }
        }
        catch(NumberFormatException e){
            sop("Entrada inválida. Vuelve a elegir coordenadas.");
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
     * Método que decide si el juego ha terminado.
     * @return
     */
    public boolean juegoTerminado() {
        return t.victoria();
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
        sop("El usuario     ha ganado %i partidas."+ marcador[0]);
        sop("La computadora ha ganado %i partidas."+ marcador[1]);
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
