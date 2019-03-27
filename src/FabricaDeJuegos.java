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
	System.out.println("Qué juego quieres jugar");
	int i = Integer.parseInt(scan.nextLine());
	switch(i){
	case 1: juego = new PPT();
        break;
        
    case 2:
        sop("Selecciona el tamaño de tu tablero: ");
        i = Integer.parseInt(scan.nextLine());
        juego = new Submarinos(i);
        break;
	default: juego = new JuegoDummy();
	}
	return juego;
    }


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

    public class Coordenada extends Object{
        public int x;
        public int y;
        public Coordenada(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Coordenada){
                Coordenada c = (Coordenada)o;
                return (this.x == c.x) && (this.y == c.y);
            }
            return false;
        }

        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }

public class Submarinos extends Juego{
    /* Para ver si el juego continua*/
    private boolean juegoTerminado;

    /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
      clase concreta*/
    private Tablero tablero;

    private int tamTab; // tamaño del tablero

    private int[][] tableroJugador;
    private int[][] tableroComputadora;

    private ArrayList<Coordenada> BarcosJugador;
    private ArrayList<Coordenada> BarcosComputadora;

    public Submarinos(int tamTab){
        if(tamTab > 5){
            this.tamTab = tamTab;
            tableroJugador = new int[tamTab][tamTab];
            tableroComputadora = new int[tamTab][tamTab];
        }else{
            sop("Tamaño insuficiente, seleccionaremos el tamaño 5.");
            this.tamTab = 5;
            tableroJugador = new int[5][5];
            tableroComputadora = new int[5][5];
        }

        BarcosJugador = new ArrayList<Coordenada>();
        BarcosComputadora = new ArrayList<Coordenada>();
        creaTableroUsuario();
        creaTableroComputadora();
    }

    public void creaTableroUsuario(){
        boolean noPuestosTodos = true;
        ArrayList<Integer> Barcos = new  ArrayList<>(Arrays.asList(5,4,3,2,2));
        while(noPuestosTodos){
            sop("Tamaños de barcos disponibles: ");
            sop(Barcos.toString());
            sop("Selecciona el indice del barco que deseas agregar");
            try{
                int b = Integer.parseInt(scan.nextLine());
                if(b < 0 || b > Barcos.size()){
                    sop("Indice invalido, vuelve a seleccionar:");
                    continue;
                }
                int t = Barcos.get(b);
                sop("Has seleccionado el barco de tamaño " + t);
                sop("Selecciona las coordenadas:");
                sop("Coordenada y");
                int i = Integer.parseInt(scan.nextLine());
                sop("Coordenada x");
                int j = Integer.parseInt(scan.nextLine());
                if(i < 0 || i >= tamTab || j < 0 || j >= tamTab){
                    sop("Coordenadas inválidas.");
                    continue;
                }
                sop("Selecciona la orientacion: 1) Vertical 2) Horizontal");
                int o =  Integer.parseInt(scan.nextLine()); // Orientación del barco
                if((o == 1 && (i + t) > tamTab)|| (o == 2 && (j+t)>tamTab)){
                    sop("La posición o tamaño de tu barco no cuadra en el tablero.");
                    sop("Coordenada inicial: " + i + "," +  j);
                    if (o==1) {
                        sop("ultima coordenada: " + (i+t) + " , " + j);  
                    } else {
                        sop("ultima coordenada: " + i + " , " + (j+t));                                                
                    }
                    continue;
                }
                boolean posicionValida = true;
                if(o == 1){
                    for(int k = i; k<(i+t);k++){
                        tableroJugador[k][j] = 1;
                        Coordenada c = new Coordenada(k,j);
                        sop("Agregando coordenada: " + c.toString());
                        if(BarcosJugador.contains(c)){
                            for(int l = k-1; l==i;l--){
                                BarcosJugador.remove((new Coordenada(l,j)));
                            }
                            posicionValida = false;
                            break;
                        }
                        BarcosJugador.add(c);
                    }
                }else{
                    for(int k = j; k<(j+t);k++){
                        tableroJugador[i][k] = 1;
                        Coordenada c = new Coordenada(i,k);
                        sop("Agregando coordenada: " + c.toString());
                        if(BarcosJugador.contains(c)){
                            for(int l = k-1; l==i;l--){
                                BarcosJugador.remove((new Coordenada(i,l)));
                            }
                            posicionValida = false;
                            break;
                        }
                        BarcosJugador.add(c);
                    }
                }
                if(!posicionValida){
                    sop("La posicion del barco esta sobre otro barco.");
                    continue;
                }
                Barcos.remove(new Integer(t));
                noPuestosTodos = Barcos.size() != 0;
            }catch(NumberFormatException e){
                sop("Entrada inválida. Vuelve a elegir.");
            }
        }

        for (Coordenada var : BarcosJugador) {
            tableroJugador[var.x][var.y] = 1;
        }

    }

    public void creaTableroComputadora(){
        ArrayList<Integer> Barcos = new  ArrayList<>(Arrays.asList(5,4,3,2,2));
        Random r = new Random();
        int contador = 0;
        while(contador < 5){
            int t = Barcos.get(contador);
            int i = r.nextInt(tamTab-t+1);
            int j = r.nextInt(tamTab-t+1);
            int o = r.nextInt(2)+1; // Orientación del barco
            if((o == 1 && (i + t) > tamTab)|| (o == 2 && (j+t)>tamTab)){
                sop("Elección invalida" + i + "," + j + ":" +t);
                continue;
            }
            boolean posicionValida = true;
            if(o == 1){
                for(int k = i; k<(i+t);k++){
                    tableroComputadora[k][j] = 1;
                    Coordenada c = new Coordenada(k,j);
                    if(BarcosComputadora.contains(c)){
                        for(int l = k-1; l==i;l--){
                            BarcosComputadora.remove((new Coordenada(l,j)));
                        }
                        posicionValida = false;
                        break;
                    }
                    BarcosComputadora.add(c);
                }
            }else{
                for(int k = j; k<(j+t);k++){
                    tableroComputadora[i][k] = 1;
                    Coordenada c = new Coordenada(i,k);
                    if(BarcosComputadora.contains(c)){
                        for(int l = k-1; l==i;l--){
                            BarcosComputadora.remove((new Coordenada(i,l)));
                        }
                        posicionValida = false;
                        break;
                    }
                    BarcosComputadora.add(c);
                }
            }
            if(!posicionValida){
                sop("Elección invalida, barcos sobrepuestos.");
                continue;
            }
            contador ++;
        }
        for(Coordenada elem : BarcosComputadora){
            tableroComputadora[elem.x][elem.y] = 1;
        }
        sop("Seleccionadas casillas para la maquina.");
    }


    /**
     * Método que sirve para crear un tablero de cualquier juego que implemente
     * la clase. No todos los juegos requieren un tablero
     *
     * @throws NoRequiereTableroException Si no requiere tablero y se invoca.
     */
    public void creaTablero() throws NoRequiereTableroException{
        creaTableroUsuario();
        creaTableroComputadora();
    }

    /**
     * Método que maneja el turno de la computadora.
     */
    public void turnoComputadora(){
        Random r = new Random();
        int i = r.nextInt(tamTab-1);
        int j = r.nextInt(tamTab-1);
        Coordenada c = new Coordenada(i,j);
        if(BarcosJugador.contains(c)){
            sop("Ataque en" + c.toString());
            tableroJugador[i][j]=2;
            BarcosJugador.remove(c);
        }
    }

    /**
     * Método que maneja el turno del usuario.
     */
    public void turnoUsuario(){
        boolean tiroValido = false;
        while(!tiroValido){
            try{
                sop("Coordenada y");
                int i = Integer.parseInt(scan.nextLine());
                sop("Coordenada x");
                int j = Integer.parseInt(scan.nextLine());
                if((i >= tamTab || i < 0)|| (j >= tamTab || j < 0)){
                    sop("Tu tiro no cae dentro del tablero.");
                    continue;
                }
                tiroValido = true;
                Coordenada c = new Coordenada(i,j);
                if(BarcosComputadora.contains(c)){
                    tableroComputadora[i][j]=2;
                    BarcosComputadora.remove(c);
                }else if(tableroComputadora[i][j]<2){
                    tableroComputadora[i][j] = 3;
                }
            }catch(NumberFormatException e){
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
        if(BarcosJugador.size()==0){
            sop("La computadora ha ganado.");
            return true;
        }else if(BarcosComputadora.size() == 0){
            sop("El jugador ha ganado.");
            return true;
        }
        return false;
    }

    /**
     * Método que guarda la puntuación del usuario que está jugando.
     */
    public void guardaPuntuacion(){
        sop("Listo");
    }

    /**
     * Método que muestra las puntuaciones cuando es invocado.
     */
    public void muestraPuntuaciones(){
        sop("La puntuacion del usuario es: " + (16-BarcosComputadora.size()));
    }

    /**
     * Método que muestra el tablero en pantalla. Al utilizar JavaFX, puede
     * sustituirse la impresión en pantalla por mostrar una ventana de la
     * interfaz.
     */
    public void muestraTablero() {
        //Muestra el método toString()
        for(int i =0; i < tamTab ; i++){
            for(int j = 0; j < tamTab; j++){
                if(tableroComputadora[i][j] == 2){
                    System.out.print("X");
                }else if(tableroComputadora[i][j] == 3){
                    System.out.print("O");
                }else{
                    System.out.print("?");
                }
            }
            sop("");
        }
    }
}




    // public class Gato extends Juego{
    //
    //         /* Para ver si el juego continua*/
    //         private boolean juegoTerminado;
    //
    //         /*Tablero donde se llevará a cabo el juego, debe instanciarse en la
    //           clase concreta*/
    //         private Tablero tablero;
    //
    //         /**
    //          * Método que sirve para crear un tablero de cualquier juego que implemente
    //          * la clase. No todos los juegos requieren un tablero
    //          *
    //          * @throws NoRequiereTableroException Si no requiere tablero y se invoca.
    //          */
    //         public abstract void creaTablero() throws NoRequiereTableroException;
    //
    //         /**
    //          * Método que maneja el turno de la computadora.
    //          */
    //         public abstract void turnoComputadora();
    //
    //         /**
    //          * Método que maneja el turno del usuario.
    //          */
    //         public abstract void turnoUsuario();
    //
    //         /**
    //          * Método que maneja el turno del usuario invitado.
    //          */
    //         public abstract void turnoUsuarioInvitado();
    //
    //
    //         /**
    //          * Método que maneja toda la partida de la instancia del juego. Aquí se
    //          * pueden generar los ciclos para distintas partidas jugadas.
    //          *
    //          * Aquí podría ir una implementación del flujo general de un juego en
    //          * potencial para todos los juegos. Si no funciona para algún juego, se
    //          * puede sobreescribir con @Override en la clase concreta.
    //          *
    //          * @throws NoRequiereTableroException
    //          */
    //         public void jugar() throws NoRequiereTableroException {
    //             //Comenta la siguiente línea para que puedas ver el ejemplo
    //             //creaTablero();
    //             while (!juegoTerminado()) {
    //                 System.out.println("Seguimos jugando");
    //                 //Implementen un volado para que el primer turno sea aleatorio.
    //                 turnoUsuario();
    //                 turnoComputadora();
    //                 muestraTablero();
    //                 juegoTerminado = true;
    //             }
    //             System.out.println("Fin del juego");
    //         }
    //
    //         /**
    //          * Método invocado para saber si el juego ha terminado.
    //          *
    //          * @return
    //          */
    //         public boolean juegoTerminado() {
    //             return juegoTerminado;
    //         }
    //
    //         /**
    //          * Método que guarda la puntuación del usuario que está jugando.
    //          */
    //         public abstract void guardaPuntuacion();
    //
    //         /**
    //          * Método que muestra las puntuaciones cuando es invocado.
    //          */
    //         public abstract void muestraPuntuaciones();
    //
    //         /**
    //          * Método que muestra el tablero en pantalla. Al utilizar JavaFX, puede
    //          * sustituirse la impresión en pantalla por mostrar una ventana de la
    //          * interfaz.
    //          */
    //         public void muestraTablero() {
    //             //Muestra el método toString()
    //             System.out.println(tablero.toString());
    //         }
    // }

    private void sop(String s){
        System.out.println(s);
    }
}
