// Author: Wentao Li, 953828

package Server.MsgHandler;
import org.json.JSONObject;

public class DicMsgDecoder {
    public static DicMsg Decode(String msg)
    {
        JSONObject json = new JSONObject(msg);
        String operation = json.getString("operation");
        String word = json.getString("word");
        String meaning = json.getString("meaning");

        return new DicMsg(operation, word, meaning);
    }
}
