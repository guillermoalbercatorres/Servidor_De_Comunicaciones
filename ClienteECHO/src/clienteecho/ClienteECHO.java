/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clienteecho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author guillermo
 */
public class ClienteECHO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    try (Socket s = new Socket("127.0.0.1",10000);
            Scanner t = new Scanner(System.in);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    ){

        // Enviamos un mensaje eal servidor
        while(true){
String m = t.nextLine();
bw.write(m);
bw.newLine();
bw.flush();
// Recibimoss la respuesta
System.out.println("Escribiendo...");
        String respuesta = br.readLine();
        System.out.println("Servidor -> "+respuesta);
        }
    } catch (UnknownHostException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
    
}
