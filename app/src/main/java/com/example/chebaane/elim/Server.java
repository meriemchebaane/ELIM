package com.example.chebaane.elim;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chebaane on 21/12/2016.
 */

public class Server implements Runnable {
    private Socket socket;

    private String message = null;

    private boolean ready = false;

    @Override
    public void run() {
        try {
            socket = new Socket("192.168.1.82", 3000);
            OutputStream outputStream = socket.getOutputStream();
            while(true){
                if(ready){
                    envoyer(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void envoyer(String message){
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(message);
            printWriter.flush();
            printWriter.close();
            ready = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
