import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {

        /**
         * @param args the command line arguments
         */

        Socket socket ;
        BufferedReader br ;
        //Scanner t = new Scanner(System.in);
        BufferedWriter bw;
        FileWriter fw;



        public Main(){
            this.setLayout(new GridLayout(1,1));
            JButton b = new JButton("Iniciar server");
            JButton b2 = new JButton("Conversacion");
            JPanel p = new JPanel();
            JPanel p2 = new JPanel();
            p2.setLayout(new GridLayout());
            JTextArea t = new JTextArea();
            p2.add(t);
            p.setLayout(new BorderLayout());
            p.add(b, BorderLayout.NORTH);
            p.add(b2,BorderLayout.SOUTH);
            p.add(p2,BorderLayout.CENTER);



            b.addActionListener(l -> {
                new Thread(() -> {
                    try (ServerSocket ss = new ServerSocket(10000);){
                        System.out.println("Servior iniciado esperando conexion del cliente");
                        // Aceptarla conexion de un cliente

                        try {
                            socket =  ss.accept();
                            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            //Scanner t = new Scanner(System.in);
                            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            System.out.println("Conexion aceptada");

                            // Recibimos del mensaje procedent del cliente

                            //Leemos el mensaje

                            LocalDateTime hoyHora = LocalDateTime.now();
                            try {
                                fw = new FileWriter("./Conversaciones.txt", true);
                                String registro="<--> Dia "+hoyHora.toString()+" <-->";
                                fw.write(registro);

                                while(!t.getText().toLowerCase().equals("fin")){
                                    String mensaje = br.readLine();
                                    guardar("El/Ella : " + mensaje+"\n");
                                    System.out.println("Mensaje recibido : " + mensaje);
                                }
                            } catch (IOException e) {
                                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                            }
                            System.out.println("Escribiendo....");
                        } catch (IOException e) {
                            System.out.println("Problemas a la hora de recibir y transmitir los datos del cliente");
                        }

                    } catch (IOException e) {
                        System.out.println("El puesrto 10000 se esta usando");
                    }


                }).start();
            });


            b2.addActionListener(l ->{

                try {

                    String mensaje = t.getText();

                    if(mensaje.toLowerCase().equals("fin")){
                        this.dispose();
                    }else{

                        if (!mensaje.isEmpty()) {

                            guardar("Tu : " + mensaje+"\n");

                            bw.write(mensaje);
                            bw.newLine();
                            bw.flush();

                            t.setText("");

                            System.out.println("Respuesta enviada.");
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Error al enviar: " + ex.getMessage());
                }
            });



            //p.add(new JButton("adios"));
            this.add(p);
            this.setSize(300, 300);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //this.pack();
            this.setVisible(true);
        }


        public static void guardar(String mensaje) throws IOException{
            try (FileWriter fw = new FileWriter("./Conversaciones.txt", true)) {
                if(mensaje!=null){
                    fw.write(mensaje);
                }
            }
        }



        public static void main(String[] args) {
            //String txt="";
            new Main();
        }

    }
