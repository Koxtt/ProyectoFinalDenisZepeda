/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.pkgfinal.denis.zepeda;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class ProyectoFinalDenisZepeda {

private char[][] tablero;       
    private int tamaño;          
    private int pacmanX, pacmanY; 
    private int[][] fantasmas;     
    private int cantidadFantasmas;  
    private int puntos;             
    private Scanner teclado = new Scanner(System.in);
    private Random azar = new Random();
    
    public ProyectoFinalDenisZepeda(int t, int dificultad) {
        this.tamaño = t;
        this.tablero = new char[t][t];
        this.pacmanX = 1;
        this.pacmanY = 1; 
        this.puntos = 0;

 
        switch (dificultad) {
            case 1: 
                    cantidadFantasmas = 2;
                    break;
            case 2: 
                    cantidadFantasmas = 3;
                    break;
            case 3: 
                    cantidadFantasmas = 4; 
                     break;
            case 4: 
                    cantidadFantasmas = 6; 
                    break;
            default: 
                    cantidadFantasmas = 2;
        }
        
        fantasmas = new int[cantidadFantasmas][2];
        for (int i = 0; i < cantidadFantasmas; i++) {
            fantasmas[i][0] = tamaño - 2; 
            fantasmas[i][1] = tamaño - 2;
        }
        
        inicializarTablero();
    }
    
        public void inicializarTablero() {
               for (int i = 0; i < tamaño; i++) {
                   for (int j = 0; j < tamaño; j++) {
                    if (i == 0 || i == tamaño - 1 || j == 0 || j == tamaño - 1) {
                           tablero[i][j] = '#'; 
                       } else {
                           tablero[i][j] = '.';
                       }
                   }
               }
           }
        
        public void dibujarTablero() {
        System.out.println("\n");
        System.out.println("PUNTOS: " + puntos);
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (i == pacmanX && j == pacmanY) {
                    System.out.print(":v "); //Pacman
                } else {
                   
                    boolean hayFantasma = false;
                    for (int f = 0; f < cantidadFantasmas; f++) {
                        if (fantasmas[f][0] == i && fantasmas[f][1] == j) {
                            System.out.print("G "); //Fantasmas
                            hayFantasma = true;
                            break;
                        }
                    }
                    if (!hayFantasma) System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
        
        public void moverPacman(int direccion) {
        int proximaX = pacmanX;
        int proximaY = pacmanY;

        if (direccion == 1) proximaX--;      
        else 
            if (direccion == 2) proximaX++; 
        else 
                if (direccion == 3) proximaY--;
        else
                    if (direccion == 4) proximaY++;

        if (tablero[proximaX][proximaY] != '#') {
            pacmanX = proximaX;
            pacmanY = proximaY;
            if (tablero[pacmanX][pacmanY] == '.') {
                tablero[pacmanX][pacmanY] = ' '; 
                puntos += 10;
            }
        }
    }
        public void moverFantasmas() {
        for (int i = 0; i < cantidadFantasmas; i++) {
            int dir = azar.nextInt(4) + 1; // Aleatorio entre 1 y 4
            int fX = fantasmas[i][0];
            int fY = fantasmas[i][1];

            if (dir == 1 && tablero[fX-1][fY] != '#') fX--;
            else if (dir == 2 && tablero[fX+1][fY] != '#') fX++;
            else if (dir == 3 && tablero[fX][fY-1] != '#') fY--;
            else if (dir == 4 && tablero[fX][fY+1] != '#') fY++;

            fantasmas[i][0] = fX;
            fantasmas[i][1] = fY;
        }
    }
        

        
        public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int opc = 0;

        while (opc != 2) {
                System.out.println("====MENU====");
                System.out.println("1. Jugar");
                System.out.println("2. Salir");
                opc = entry.nextInt();
            opc = entry.nextInt();

            if (opc == 1) {
                System.out.print("Ingrese el tamano de la matriz");
                int t = entry.nextInt();
                System.out.println("Elija el nivel de dificul;tadd 1-Facil, 2-Medio, 3-Dificil, 4 Imposible");
                int d = entry.nextInt();

                ProyectoFinalDenisZepeda juego = new ProyectoFinalDenisZepeda(t, d);
                boolean activo = true;

                while (activo) {
                    juego.dibujarTablero();
                    System.out.print("Mover 1=Arriba 2=Abajo, 3=Izquierda, 4=Derecha, 0=Salir): ");
                    int mov = entry.nextInt();
                    if (mov == 0) break;

                    juego.moverPacman(mov);
                    juego.moverFantasmas();

                    // Verificar si chocaron
                    for (int i = 0; i < juego.cantidadFantasmas; i++) {
                        if (juego.fantasmas[i][0] == juego.pacmanX && juego.fantasmas[i][1] == juego.pacmanY) {
                            juego.dibujarTablero();
                            System.out.println("¡GAME OVER! Te atraparon.");
                            activo = false;
                        }
                    }
                }
            }
        }
        System.out.println("El Juego ha terminado!");
    }
} // Fin de la clase
