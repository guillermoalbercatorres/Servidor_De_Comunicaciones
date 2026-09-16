/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clienteecho;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
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
    try (Socket s = new Socket("192.168.21.4",10000);
            Scanner t = new Scanner(System.in);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    ){

        // Enviamos un mensaje eal servidor
        LocalDateTime hoyHora = LocalDateTime.now();
        try (FileWriter fw = new FileWriter("./Conversaciones.txt", true)) {
            String registro="<--> Dia "+hoyHora.toString()+" <-->";
            String m="";
            while(!m.toLowerCase().equals("fin")){
                m = t.nextLine();
                fw.write("Tu -> "+m+"\n");
                bw.write(m);
                bw.newLine();
                bw.flush();
// Recibimoss la respuesta
                System.out.println("Escribiendo...");
                String respuesta = br.readLine();
                System.out.println("Servidor -> "+respuesta);
                fw.write("Servidor -> "+respuesta+"\n");
            }
            System.out.println("Archivo escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    } catch (UnknownHostException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }
    
}
