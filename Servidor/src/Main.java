//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main(){

    // Abrimos el puesrto que acepta las conexiones de los clientes

    try ( ServerSocket ss = new ServerSocket(10000);){
        System.out.println("Servior iniciado esperando conexion del cliente");
        // Aceptarla conexion de un cliente

        try(Socket socket =  ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner t = new Scanner(System.in);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            System.out.println("Conexion aceptada");

            // Recibimos del mensaje procedent del cliente

            //Leemos el mensaje

LocalDateTime hoyHora = LocalDateTime.now();
    try (FileWriter fw = new FileWriter("./Conversaciones.txt", true)) {
        String registro="<--> Dia "+hoyHora.toString()+" <-->";
        String m="";
        while(!m.toLowerCase().equals("fin")){
        String mensaje = br.readLine();
        fw.write("Mensaje recibido : " + mensaje+"\n");
        System.out.println("Mensaje recibido : " + mensaje);

        m = t.nextLine();
            fw.write("Tu -> "+m+"\n");
        bw.write("Respuesta del servidor : " + m);
        bw.newLine();
        bw.flush();
        System.out.println("Respuesta enviada.");
        }
        System.out.println("Archivo escrito correctamente.");
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
}
