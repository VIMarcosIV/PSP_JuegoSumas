import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Servidor Multihilo, dos jugadores sin competir.
 */
public class ServidorSumasMultihilo {
    public static void main(String[] args) {
        System.out.println(
                "\n*************************************\n*   Juego de las Sumas 2DAM 2023   *\n*************************************\n");
        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            System.out.println("Esperando conexion, puerto: " + serverSocket.getLocalPort() + "...");

            while (true) {
                try {
                    // Crea un nuevo hilo
                    new Thread(new HiloServidor(serverSocket.accept())).start();
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al establecer conexion: " + e.getMessage());
        }

    }
}

class HiloServidor implements Runnable {
    Socket clientSocket;
    int numUsuario;
    boolean acierto = false;

    public HiloServidor(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {

        System.out.println("Conexion establecida con " + clientSocket.getInetAddress());


        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {

            int sumando1 = (int) (Math.random() * 10);
            int sumando2 = (int) (Math.random() * 10);
            int resultadoSuma = sumando1 + sumando2;

            // 1
            out.println("¡Bienvenido al Juego de las Sumas!");
            System.out.println("La suma a adivinar es: " + sumando1 + " + " + sumando2 + " = " + resultadoSuma);
            while (!acierto) {
                // 2
                out.println("Introduce el resultado de esta suma: " + sumando1 + " + " + sumando2 + " = ");

                // 3
                numUsuario = Integer.parseInt(in.readLine());
                System.out.println("Cliente: " + clientSocket.getInetAddress() + " Se ha recibido el numero " + numUsuario);

                // 4
                if (numUsuario == resultadoSuma) {
                    out.println("*** ¡Acertaste! ***");
                    System.out.println(in.readLine());
                    break;
                } else {
                    out.println("¡Fallaste!");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


