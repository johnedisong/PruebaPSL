/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcdtester;
/**
 *
 * @author JOHN
 */
public class LCDTester {

  
    
    public static void main(String[] args) {
        Lector lectorCadena = new Lector();
        System.out.print("Espacio entre Digitos (0 a 5): ");
        int espacio=lectorCadena.capturarCadena();
        lectorCadena.validarCadena();
        lectorCadena.iterarCadena(espacio);
               

    }
}
