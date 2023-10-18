package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClockServidor {
    public static void main(String[] args) throws IOException {

        String port;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingresar numero del puerto");

        port = stdIn.readLine();
        int portNumber = Integer.parseInt(port);
        try(
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ){
            String inputLine;
            System.out.println("Servidor Iniciado");
            while (true){
                inputLine = in.readLine();
                if (inputLine.equalsIgnoreCase("Exit")){
                    System.out.println("Exiting");
                    out.println("Server Exiting");
                    break;
                }
                out.println(System.currentTimeMillis()+5000);
            }
        }catch (Exception ex){
            System.out.println("Tiempo Agotador :c");
        }


    }
}
