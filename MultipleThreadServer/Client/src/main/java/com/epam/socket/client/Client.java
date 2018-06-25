package com.epam.socket.client;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private static String host = "127.0.0.1";
    private static int port = 8080;


    public static void main(String[] args) {
        if (args.length != 0) {
            host = args[0].split(":")[0];
            port = Integer.parseInt(args[0].split(":")[1]);
        }

        new Client().communicateWithServer();
    }


    private static boolean shouldContinue(String line) {
        if (line == null) {
            return false;
        }

        return line.equals("stop") ? false : true;
    }

    public void communicateWithServer() {

        Socket socket = null;

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Got a program");
        try (
                OutputStream sout = socket.getOutputStream();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sout));
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))
        ) {

            String line = null;
            System.out.println("Type in something and press enter. It will send it to the server. Type \"stop\" if you want to stop");
            System.out.println();


            while (true) {
                line = keyboard.readLine();
                System.out.println("Sending this line to the server...");
                out.write(line + "\r\n");
                if (!shouldContinue(line)) break;
                out.flush();
                System.out.println("Enter another line or \"stop\", if you want to stop");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}