import java.io.*;
import java.net.*;

public class ServidorSumas {
    public static int sumando1;
    public static int sumando2;

    public static int resultadoSuma;

    public static void main(String[] args) {
        int numUsuario;
        boolean acierto = false;


        System.out.println(
                "\n*************************************\n*   Juego de las Sumas 2DAM 2023   *\n*************************************\n");
        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            System.out.println("Esperando conexion, puerto: " + serverSocket.getLocalPort() + "...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexion establecida con " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // 1
            out.println("¡Bienvenido al Juego de las Sumas!");
            generarSuma();
            System.out.println("La suma a adivinar es: " + sumando1 + " + " + sumando2 + " = " + resultadoSuma);
            while (!acierto) {
                // 2
                out.println("Introduce el resultado de esta suma: " + sumando1 + " + " + sumando2 + " = ");

                // 3
                numUsuario = Integer.parseInt(in.readLine());
                System.out.println("Se ha recibido el numero " + numUsuario);

                // 4
                if(numUsuario == resultadoSuma){
                    out.println("*** ¡Acertaste! ***");
                    System.out.println(in.readLine());
                    break;
                }else{
                    out.println("¡Fallaste!");
                }

            }
        } catch (IOException e) {
            System.out.println("Error al establecer conexion: " + e.getMessage());
        }
    }

    public static void generarSuma() {
        sumando1 = (int) (Math.random() * 10);
        sumando2 = (int) (Math.random() * 10);
        resultadoSuma = sumando1 + sumando2;
    }
}
