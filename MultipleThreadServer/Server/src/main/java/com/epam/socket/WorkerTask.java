package com.epam.socket;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class WorkerTask implements Runnable {
    private final Socket socket;

    public WorkerTask(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {

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

            socket.close();
            System.out.println("Socket " + id + " is closed");

        } catch (IOException e) {
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
