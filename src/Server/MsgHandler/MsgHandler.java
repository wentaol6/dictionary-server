// Author: Wentao Li, 953828

package Server.MsgHandler;

import Server.DictionaryDBManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;

public interface MsgHandler {
    void Handle(DicMsg msg, BufferedWriter writer, DictionaryDBManager db) throws IOException, SQLException;
}
