import java.util.Scanner;
import java.util.Random;

public class PPT extends Juego{
    int piedra = 1, papel = 2, tijeras = 3; // Codificación de los valores de piedra papel y tijeras.

        int tiradaJugador; //Guarda el valor que el jugador eligió
        int tiradaComputadora; // Guarda la elección de la computadora.

        /* Para ver si el juego continua*/
        private boolean juegoTerminado;

        /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
          clase concreta*/
        private Tablero tablero;
        private Scanner scan;

        // Constructor
        public PPT(){
            // Asigna valores por omisión a las tiradas.
            tiradaJugador = 0;
            tiradaComputadora = 0;
            scan = new Scanner(System.in);
        }

        /**
         * Método que sirve para crear un tablero de cualquier juego que implemente
         * la clase. No todos los juegos requieren un tablero
         *
         * @throws NoRequiereTableroException Si no requiere tablero y se invoca.
         */
        public void creaTablero() throws NoRequiereTableroException{
            throw new NoRequiereTableroException("Piedra Papel o tijera no requiere un tablero.");
        }

        /**
         * Método que maneja el turno de la computadora.
         */
        public void turnoComputadora(){
            // Elige aleatoriamente un valor para la computadora.
            tiradaComputadora = ((new Random()).nextInt(100)%3)+1;
        }

        /**
         * Método que maneja el turno del usuario.
         */
        public void turnoUsuario(){
            boolean valorValido = false; // Revisa si el valor dado por el usuario es válido.
            while(!valorValido){ // Revisa que el usuario introduzca un valor válido.
                try{
                    sop("Puedes elegir tirar: \n"+// Menú de elección
                        "1) Piedra:\n"+
                        "2) Papel:\n"+
                        "3) Tijeras:\n");
                    int i = Integer.parseInt(scan.nextLine());
                    if(i>0 && i< 4){
                        valorValido = true;
                        tiradaJugador = i;
                    }
                }catch(NumberFormatException e){
                    // Vuelve a pedir una entrada válida.
                    sop("Entrada inválida. Vuelve a elegir.");
                }
            }
        }

        /**
         * Método que maneja el turno del usuario invitado.
         */
        public void turnoUsuarioInvitado(){
            sop("Metodo no implementado");
        }

        /**
         * Método invocado para saber si el juego ha terminado.
         *
         * @return
         */
        public boolean juegoTerminado() {
            boolean bandera = tiradaJugador != tiradaComputadora;
            //El juego termina si no hay empate.
            if(bandera){// Revisa e imprime quién ganó dependiendo de las elecciones de cada jugador.
                if(tiradaJugador == 1){
                    if(tiradaComputadora == 2){
                        sop("Gana la maquina");
                    }else if(tiradaComputadora == 3){
                        sop("Gana el jugador");
                    }
                }else if(tiradaJugador == 2){
                    if(tiradaComputadora == 1){
                        sop("Gana el jugador");
                    }else if(tiradaComputadora == 3){
                        sop("Gana la maquina");
                    }
                }else{
                    if(tiradaComputadora == 1){
                        sop("Gana la maquina");
                    }else if(tiradaComputadora == 2){
                        sop("Gana el jugador");
                    }
                }
            }
            return bandera;
        }

        /**
         * Método que guarda la puntuación del usuario que está jugando.
         */
        public void guardaPuntuacion(){}

        /**
         * Método que muestra las puntuaciones cuando es invocado.
         */
        public void muestraPuntuaciones(){}

        /**
         * Método que muestra el tablero en pantalla. Al utilizar JavaFX, puede
         * sustituirse la impresión en pantalla por mostrar una ventana de la
         * interfaz.
         */
        public void muestraTablero() {
            //Muestra el método toString()
            sop("Jugador: ");
            if(tiradaJugador == 1){
                sop("Piedra");
            }else if(tiradaJugador == 2){
                sop("Papel");
            }else if(tiradaJugador == 3){
                sop("Tijera");
            }
            sop("Computadora: ");
            if(tiradaComputadora == 1){
                sop("Piedra");
            }else if(tiradaComputadora == 2){
                sop("Papel");
            }else if(tiradaComputadora == 3){
                sop("Tijera");
            }
        }


    private void sop(String s){
        System.out.println(s);
    }

}
