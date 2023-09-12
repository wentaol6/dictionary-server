// Author: Wentao Li, 953828

package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;

public class Client extends Thread {
    private BufferedReader reader;
    private BufferedWriter writer;
    Socket client = null;
    static String ip = "localhost";
    static int port = 3005;
    public LinkedBlockingQueue<String> reqQueue = new LinkedBlockingQueue<>(1);

    public void run()
    {
        try {
            client = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8));
            System.out.println("Connected!");
            String reqMsg = null;
            while ((reqMsg = reqQueue.take()) != null) {
                writer.write(reqMsg + "\n");
                writer.flush();
            }

        } catch (Exception e) {
            System.out.println("Disconnected!");
        }
    }

    public String GetRsp()
    {
        String rsp = null;
        try {
            rsp = reader.readLine();
        } catch (Exception e) {
            System.out.println("Log Error: Get response failed!");
        }
        return rsp;
    }

}
