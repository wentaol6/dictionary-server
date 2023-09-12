// Author: Wentao Li, 953828

package Server.MsgHandler;

public class MsgHandlerFactory {
    public static MsgHandler CreateMessageHandler(String operation) {
        switch (operation) {
            case "ADD" :
                return new AddMessageHandler();
            case "QUERY" :
                return new QueryMessageHandler();
            case "DELETE" :
                return new DeleteMessageHandler();
            case "UPDATE" :
                return new UpdateMessageHandler();
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}
