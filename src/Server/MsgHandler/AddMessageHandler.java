// Author: Wentao Li, 953828

package Server.MsgHandler;

import Server.DictionaryDBManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

public class AddMessageHandler implements MsgHandler {
    @Override
    public void Handle(DicMsg msg, BufferedWriter writer, DictionaryDBManager db) throws IOException, SQLException {
        if (db.InsertWord(msg.GetWord(), msg.GetMeaning())) {
            writer.write("Added!\n");
        } else {
            writer.write("Duplicate word!\n");
        }
        writer.flush();
    }
}
