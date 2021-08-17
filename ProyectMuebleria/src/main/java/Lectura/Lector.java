/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author joel
 */
public class Lector {

    public void leerTXT(String pathArchivo) {

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(pathArchivo), "utf-8"));

            String sCadena;
            while ((sCadena = bf.readLine()) != null) {
                System.out.println(sCadena);
            }
        } catch (Exception e) {
        }
    }
}
