import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Submarinos extends Juego{

    Scanner scan;

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
        scan = new Scanner(System.in);
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
                if(b < 0 || b >= Barcos.size()){
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
                        if(BarcosJugador.contains(c)){
                            for(int l = k-1; l==i;l--){
                                BarcosJugador.remove((new Coordenada(l,j)));
                            }
                            posicionValida = false;
                            break;
                        }
                        BarcosJugador.add(c);
                        tableroJugador[k][j] = 1;
                    }
                }else{
                    for(int k = j; k<(j+t);k++){
                        tableroJugador[i][k] = 1;
                        Coordenada c = new Coordenada(i,k);
                        if(BarcosJugador.contains(c)){
                            for(int l = k-1; l==i;l--){
                                BarcosJugador.remove((new Coordenada(i,l)));
                            }
                            posicionValida =false;
                            break;
                        }
                        BarcosJugador.add(c);
                        tableroJugador[i][k] = 1;
                    }
                }
                if(!posicionValida){
                    sop("La posicion del barco esta sobre otro barco.");
                    continue;
                }
                Barcos.remove(new Integer(t));
                noPuestosTodos = Barcos.size() != 0;
                for (int k = 0; k < tamTab; k++) {
                    for (int l = 0; l < tamTab; l++) {
                        if (tableroJugador[k][l]==0) {
                            System.out.print("M");
                        }else{
                            System.out.print("B");
                        }
                    }
                    sop("");
                }
            }catch(NumberFormatException e){
                sop("Entrada inválida. Vuelve a elegir.");
            }
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
        int i = 0;
        int j = 0;
        do {
            i = r.nextInt(tamTab);
            j = r.nextInt(tamTab);                
        } while (tableroJugador[i][j]>=2);
        Coordenada c = new Coordenada(i,j);
        if(BarcosJugador.contains(c)){
            sop("Ataque en" + c.toString());
            tableroJugador[i][j]=2;
            BarcosJugador.remove(c);
        }else{
            sop("Fallo en "+c.toString());
            tableroJugador[i][j] = 3;
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
                sop("Entrada invalida, vuelve a elegir.");
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

    private class Coordenada extends Object{
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

    private void sop(String s){
        System.out.println(s);
    }
}
