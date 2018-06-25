package com.epam.socket;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int port = 8080;

    public static void main(String[] args)    {
        try {
            if(args.length != 0){
                port = Integer.parseInt(args[0]);
            }
            ServerSocket ss = new ServerSocket(port);
            ExecutorService executor = Executors.newCachedThreadPool();
            System.out.println("Waiting for a client...");

            while(true){
                Socket socket = ss.accept();
                executor.execute(new WorkerTask(socket));
            }

        } catch(Exception e) { e.printStackTrace(); }
    }


}