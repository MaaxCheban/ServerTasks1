package com.epam.socket;

import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static int port = 8080;

    public static void main(String[] args) {
        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            ServerSocket ss = new ServerSocket(port);
            Socket socket = null;
            System.out.println("Waiting for a client...");
            while (true) {
                socket = ss.accept();

                int id = new Random().nextInt(10000);
                System.out.println("Got a client " + id);
                System.out.println();

                File f = new File(buildFileName(id));

                try (
                        InputStream sin = socket.getInputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(sin));
                        PrintStream outputStream = new PrintStream(f)
                ) {

                    String line = null;
                    while (true) {
                        line = in.readLine();

                        if (!shouldContinue(line)) break;

                        System.out.println("Entering clients line to the file ");
                        outputStream.println(line);

                        System.out.println("Waiting for the next line...");
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String buildFileName(int id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("file ");
        stringBuffer.append(id);
        stringBuffer.append(".txt");

        return stringBuffer.toString();
    }

    private static boolean shouldContinue(String line) {
        if (line == null) {
            return false;
        }
        return line.equals("stop") ? false : true;
    }
}