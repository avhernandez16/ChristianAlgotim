package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockCliente {
    public static void main(String[] args) throws IOException {

        String port, hostName;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingresar numero del puerto");
        port = stdIn.readLine();

        int portNumber = Integer.parseInt(port);
        System.out.println("Ingresar nombre del host");
        hostName = stdIn.readLine();
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        ) {
            String userInput;
            System.out.println("Cliente Iniciado");
            System.out.println("Enter Exit to Stop");
            long TiempoCero;
            long Tiempo_servidor;
            long TiempoUno;
            long Tiempo_Final;

            out.println((TiempoCero = System.currentTimeMillis()));
            Tiempo_servidor = Long.parseLong(in.readLine());

            TiempoUno = System.currentTimeMillis();
            Tiempo_Final = (Tiempo_servidor + (TiempoUno - TiempoCero) / 2);
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");

            System.out.println("Tiempo del Cliente: " + formatter.format(new Date(TiempoUno)));
            System.out.println("Tiempo del Servidor: " + formatter.format(new Date(Tiempo_servidor)));
            System.out.println("Tiempo del Cliente despues del reinicio: " + formatter.format(new Date(Tiempo_Final)));
            out.println("Salida");

        } catch (Exception ex) {
            System.out.println("Tiempo Agotador :c");
        }
    }
}
