// Author: Wentao Li, 953828

package Server.MsgHandler;

public class DicMsg {
    private String operation;
    private String word;
    private String meaning;

    public DicMsg(String operation, String word, String meaning) {
        this.operation = operation;
        this.word = word;
        this.meaning = meaning;
    }

    public String GetOperation() {
        return operation;
    }

    public String GetWord() {
        return word;
    }

    public String GetMeaning() {
        return meaning;
    }



}
