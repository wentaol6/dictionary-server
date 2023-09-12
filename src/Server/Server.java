// Author: Wentao Li, 953828

package Server;

import Utility.PortValidation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends  Thread {
    private static Server server;
    ServerSocket ss = null;
    static  int port = 8888;
    public static DictionaryDBManager dbManager;

    public static ServerUI serverUI;

    public static void main(String[] args)
    {
        if (!IsValid(args)) {
            System.exit(1);
        }
        server.port = Integer.valueOf(args[0]);
        serverUI = new ServerUI();
        serverUI.start();
        server = new Server();
        server.ReadDic();
        server.start();
    }

    public void run()
    {
        try {
            ss = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Socket clientSocket = null;
        ExecutorService executor = Executors.newFixedThreadPool(5);
        while (true) {
            try {
                clientSocket = ss.accept();
                TCPConnection clientConnection = new TCPConnection(clientSocket);
                executor.execute(clientConnection);
            } catch (IOException e) {
                System.out.println(e.toString());
                break;
            }
        }
        try {
            ss.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void ReadDic()
    {
        try {
            dbManager = DictionaryDBManager.getInstance();
            dbManager.connect();
        } catch (SQLException e) {
            System.out.println("SQLException");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }

    }

    private static boolean IsValid(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Log Error: Incorrect number of arguments for main function: " + args.length);
            return false;
        }
        return PortValidation.PortIsValid(args[0]);
    }

}
