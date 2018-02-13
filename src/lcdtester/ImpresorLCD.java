/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdtester;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author JOHN
 */
public class ImpresorLCD {
 
    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    // TODO code application logic here
    

    public ImpresorLCD() {
        // Inicializa variables
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
    }

    

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     * @param sizeCar
     * @param matrizDig
     * @param puntoF
     */
    public void construirDigito(int numero,int sizeCar, String[][] matrizDig, int[] puntoF) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList.add(3);
                segList.add(4);
                break;
            case 2:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                break;
            case 3:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 4:
                segList.add(1);
                segList.add(6);
                segList.add(3);
                segList.add(4);
                break;
            case 5:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 6:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                segList.add(4);
                break;
            case 7:
                segList.add(5);
                segList.add(3);
                segList.add(4);
                break;
            case 8:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 9:
                segList.add(1);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 0:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            construirSegmento(iterator.next(), sizeCar, matrizDig, puntoF);
        }
    }
    
    /**
     *
     * Metodo encargado de indicar el segmento de la matriz para Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void construirSegmento(int segmento,int size, String[][] matrizImpr, int[] puntofijo) {
        pf1[0]= puntofijo[0];
        pf1[1]= puntofijo[1];
        pf2[0]= puntofijo[2];
        pf2[1]= puntofijo[3];
        pf3[0]= puntofijo[4];
        pf3[1]= puntofijo[5];
        pf4[0]= puntofijo[6];
        pf4[1]= puntofijo[7];
        pf5[0]= puntofijo[8];
        pf5[1]= puntofijo[9];
        switch (segmento) {
            case 1:
                dibujarLinea(matrizImpr, this.pf1, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 2:
                dibujarLinea(matrizImpr, this.pf2, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 3:
                dibujarLinea(matrizImpr, this.pf5, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 4:
                dibujarLinea(matrizImpr, this.pf4, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 5:
                dibujarLinea(matrizImpr, this.pf1, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 6:
                dibujarLinea(matrizImpr, this.pf2, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 7:
                dibujarLinea(matrizImpr, this.pf3, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de añadir las lineas que se requieran segun el segmento 
     * de la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void dibujarLinea(String[][] matriz, int[] punto, String posFija,
            int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     * Metodo que se encarga de realizar la Impresion de la Matriz creada
     * con base en la Cadena capturada y el tamaño seleccionado
     * @param matrizImpr
     * @param totalFilas
     * @param totalColum 
     */
    public void imprimirMatriz(String[][] matrizImpr,int totalFilas,int totalColum){
    // Imprime matriz
        
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                System.out.print(matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

   
}