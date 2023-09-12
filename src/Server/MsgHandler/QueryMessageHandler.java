// Author: Wentao Li, 953828

package Server.MsgHandler;

import Server.DictionaryDBManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

public class QueryMessageHandler implements MsgHandler {
    @Override
    public void Handle(DicMsg msg, BufferedWriter writer, DictionaryDBManager db) throws IOException, SQLException {
        if (db.GetMeaning(msg.GetWord()) != null) {
            String def = db.GetMeaning(msg.GetWord());
            writer.write(def + "\n");
        } else {
            writer.write("Word not found!\n");
        }
        writer.flush();
    }
}
