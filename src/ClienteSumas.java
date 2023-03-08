import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente del modo 1 jugador
 */
public class ClienteSumas {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 6969)) {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            // 1 "¡Bienvenido al Juego de las Sumas!"
            System.out.println(in.readLine());

            while (true) {
                // 2 "Introduce el resultado de esta suma: " + sumando1 + " + " + sumando2 + " = "
                System.out.print(in.readLine());
                
                // 3 "Se ha recibido el numero " + numUsuario
                out.println(sc.nextInt());

                String respuestaServidor = in.readLine();
                // "*** ¡Acertaste! ***" o "¡Fallaste!"
                System.out.println(respuestaServidor);
                // Si la respuesta del servidor empieza con tres asteriscos significa que
                // has acertado porque así está escrito en el servidor. Termina el juego
                if (respuestaServidor.startsWith("***")){
                    out.println(socket.getInetAddress() + " ha ganado.");
                    break;
                }
                
            }
            // Errores capturados
        } catch (IOException e) {
            System.out.println("Error al establecer conexion: " + e.getMessage());
        }
    }
}
