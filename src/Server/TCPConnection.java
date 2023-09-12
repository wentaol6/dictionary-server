// Author: Wentao Li, 953828

package Server;

import Server.MsgHandler.DicMsg;
import Server.MsgHandler.DicMsgDecoder;
import Server.MsgHandler.MsgHandler;
import Server.MsgHandler.MsgHandlerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class TCPConnection extends Thread {

    Socket s = null;
    BufferedReader reader;
    BufferedWriter writer;

    public TCPConnection(Socket socket)
    {
        this.s = socket;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void run()
    {
        String request = null;
        try {
            while ((request = reader.readLine()) != null) {
                DicMsg tmpMsg = DicMsgDecoder.Decode(request);
                Date day=new Date();
                Server.serverUI.AddRow(day.toString(), tmpMsg.GetOperation(), tmpMsg.GetWord(), tmpMsg.GetMeaning());
                System.out.println(day.toString());

                MsgHandler handler = MsgHandlerFactory.CreateMessageHandler(tmpMsg.GetOperation());
                handler.Handle(tmpMsg, writer, Server.dbManager);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}


































