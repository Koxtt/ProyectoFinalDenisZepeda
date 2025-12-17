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
                    // Si es el borde de la matriz, ponemos una pared #
                    if (i == 0 || i == tamaño - 1 || j == 0 || j == tamaño - 1) {
                        tablero[i][j] = '#'; 
                    } else {
                        tablero[i][j] = '.'; // El resto es comida
                    }
                }
            }
        }
        
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
