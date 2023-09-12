// Author: Wentao Li, 953828

package Server;

import java.sql.*;

public class DictionaryDBManager {
    private Connection connection;
    private Statement statement;

    private static final DictionaryDBManager instance = new DictionaryDBManager();

    private DictionaryDBManager() {}

    public static DictionaryDBManager getInstance() {
        return instance;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/dictionary";
        String username = "root";
        String password = "lwt000609";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database loaded");
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
         statement = connection.createStatement();
    }

    public void disconnect() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public synchronized boolean InsertWord(String word, String meaning) throws SQLException {
        if (GetMeaning(word) == null) {
            String sql = "INSERT INTO words (word, meaning) VALUES ('" + word + "', '" + meaning + "')";
            statement.executeUpdate(sql);
            return true;
        }
        return false;
    }

    public synchronized boolean DeleteWord(String word) throws SQLException {
        if (GetMeaning(word) == null) {
            return false;
        }
        String sql = "DELETE FROM words WHERE word='" + word + "'";
        statement.executeUpdate(sql);
        return true;
    }

    public synchronized boolean UpdateWord(String word, String newMeaning) throws SQLException {
        if (GetMeaning(word) == null) {
            return false;
        }
        String sql = "UPDATE words SET meaning='" + newMeaning + "' WHERE word='" + word + "'";
        statement.executeUpdate(sql);
        return true;
    }

    public synchronized String GetMeaning(String word) throws SQLException {
        String sql = "SELECT meaning FROM words WHERE word='" + word + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("meaning");
        } else {
            return null;
        }
    }
}

