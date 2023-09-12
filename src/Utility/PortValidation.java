// Author: Wentao Li, 953828

package Utility;

public class PortValidation {
    static public boolean PortIsValid(String port)
    {
        try {
            int p = Integer.valueOf(port);
            if (p < 1024 || p > 65535) {
                System.out.println("Log Error: Invalid port[" + port + "], should be 1024 - 65535");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Log Error: Invalid port[" + port + "]!");
            return false;
        }
        return true;
    }
}
