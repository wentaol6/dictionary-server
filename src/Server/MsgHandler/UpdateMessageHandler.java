// Author: Wentao Li, 953828

package Server.MsgHandler;

import Server.DictionaryDBManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateMessageHandler implements MsgHandler {
    @Override
    public void Handle(DicMsg msg, BufferedWriter writer, DictionaryDBManager db) throws IOException, SQLException {
        if (db.UpdateWord(msg.GetWord(), msg.GetMeaning())) {
            writer.write("Updated!\n");
        } else {
            writer.write("Word not found!\n");
        }
        writer.flush();
    }
}
