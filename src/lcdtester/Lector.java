/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdtester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author JOHN
 */
public class Lector {
    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    private String[][] matrizImpr;
    static final String CADENA_FINAL = "0,0";
    
    // Define el Tamaño de los Caracteres
    private int size;

    // Calcula el numero de filasDig
    private int filasDig;
    private int columDig;
    private int totalFilas;
    private int totalColum;
    
    public Lector(){
        
        // Inicializa variables
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
    }
    List<String> listaComando = new ArrayList<>();
    Scanner lector = new Scanner(System.in);  
    ImpresorLCD impresora = new ImpresorLCD();
    
    /**
     * Metodo Encargado de capturar el espacio entre caracteres, sin.
     * @return 
     */
    public int capturarCadena(){
        String comando; 
        int espacioCar=0;        
        comando = lector.next();
        
        do{             
        if (isNumeric(comando)){
            espacioCar = Integer.parseInt(comando);
            // se valida que el espaciado este entre 0 y 5
                    if(espacioCar <0 || espacioCar >5)
                    {                        
                        System.out.print("El espacio entre "
                                + "digitos debe estar entre 0 y 5, digite de nuevo: ");
                        comando = lector.next();
                    }
        }else 
                {                    
                    System.out.print("Cadena " + comando
                            + " no es un entero, digite de nuevo: ");
                    comando = lector.next();
                    espacioCar =6;
                }
        }while ((espacioCar <0 || espacioCar >5) || !isNumeric(comando)); 
        
        return espacioCar;
    }
        
        
    /**
     * Metodo Encargado de capturar y Validar la cadena de texto
     * ademas identifica si el usuario ingreso 0,0
     */
    public void validarCadena(){
                String comando;                             
                do
                {   System.out.print("Entrada: ");
                    comando = lector.next();
                    if(!comando.equalsIgnoreCase(CADENA_FINAL))
                    {
                        listaComando.add(comando);
                    }
                }while (!comando.equalsIgnoreCase(CADENA_FINAL));        
    }
    
    /**
     * Metodo encargado de iterar la cadena para enviar a impresión
     * cada Caracter
     * @param espacioCar 
     */      
    public void iterarCadena(int espacioCar){
      Iterator<String> iterator = listaComando.iterator();
            while (iterator.hasNext()) 
            {
                try 
                {
                    procesar(iterator.next(), espacioCar);
                } catch (Exception ex) 
                {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
    }  
    
    /**
     *
     * Metodo encargado de validar si size es una cadena numerica
     *
     * @param cadena Cadena
     */  
    public boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioCar Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioCar) {
        
        String[] parametros;
        
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2 )
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }
        
        // Realiza la impresion del numero
        construirMatriz(tam, parametros[1],espacioCar);

    }
    
    /**
     *
     * Metodo encargado de construir la Matriz para enviar a impresion
     *
     * @param size Tamaño Segmento Caracteres
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre Caracteres
     */    
    private void construirMatriz(int size, String caracterImp, int espacio) 
    {   
        int pivotX = 0;
        char[] caracteres;
        
        this.size = size;

        // Calcula el numero de filas cada digito
        this.filasDig = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.columDig = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los caracteres
        this.totalFilas = this.filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los caracteres
        this.totalColum = (this.columDig * caracterImp.length())
                + (espacio * caracterImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.matrizImpr = new String[this.totalFilas][this.totalColum];

        

        // Inicializa matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColum; j++) {
                this.matrizImpr[i][j] = " ";
            }
        }
        
        // crea el arreglo de caracteres
        caracteres = caracterImp.toCharArray();
        
        for (char caracter : caracteres) {
            validarCaracter(caracter);
            int numero = Integer.parseInt(String.valueOf(caracter));

            //Calcula puntos fijos
            this.pf1[0] = 0;
            this.pf1[1] = 0 + pivotX;

            this.pf2[0] = (this.filasDig / 2);
            this.pf2[1] = 0 + pivotX;

            this.pf3[0] = (this.filasDig - 1);
            this.pf3[1] = 0 + pivotX;

            this.pf4[0] = (this.columDig - 1);
            this.pf4[1] = (this.filasDig / 2) + pivotX;

            this.pf5[0] = 0;
            this.pf5[1] = (this.columDig - 1) + pivotX;

            pivotX = pivotX + this.columDig + espacio;
            int[] puntoFinal = {pf1[0],pf1[1],pf2[0],pf2[1],pf3[0],pf3[1],pf4[0],pf4[1],pf5[0],pf5[1]};
            impresora.construirDigito(numero,size,matrizImpr, puntoFinal);
        
        }
        impresora.imprimirMatriz(matrizImpr, totalFilas, totalColum);
        
    
    }
    
    /**
     * Metodo encargado de validar si el caracter es un digito.
     * 
     * @param caracter 
     */
    
    private void validarCaracter(char caracter){
       
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(caracter))
            {
                throw new IllegalArgumentException("Caracter " + caracter
                    + " no es un digito");
            }       
    }
     
   
            
}
