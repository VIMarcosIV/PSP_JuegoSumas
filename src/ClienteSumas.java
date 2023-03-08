import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteSumas {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 6969)) {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            // 1
            System.out.println(in.readLine());

            while (true) {
                // 2
                System.out.print(in.readLine());
                
                // 3
                out.println(sc.nextInt());

                String respuestaServidor = in.readLine();
                System.out.println(respuestaServidor);
                
                if (respuestaServidor.startsWith("***")){
                    out.println(socket.getInetAddress() + " ha ganado.");
                    break;
                }
                
            }
        } catch (IOException e) {
            System.out.println("Error al establecer conexion: " + e.getMessage());
        }
    }
}
