package lab3;

import java.sql.*;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class VocabModel {

    private static VocabModel _instance;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private VocabController controller;

    private VocabModel() {
        // Private constructor to prevent direct instantiation
    }

    public static VocabModel getInstance() {
        if (_instance == null) {
            _instance = new VocabModel();
        }
        return _instance;
    }

    public void setController(VocabController c) {
        this.controller = c;
    }

    public void connectDB() {
        // Step 1: Load Database Driver CLass        
        try {
            Class.forName("org.sqlite.JDBC"); //SQLite
        } catch (ClassNotFoundException cnfex) {
            controller.view.showMessage("Problem in loading or registering SQLite JDBC driver", false);
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {
            String dbURL = "jdbc:sqlite:vocab.db";

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();

            // Step 3: Creating a table for holding the data *if it does not exist already*
            String sql = "CREATE TABLE IF NOT EXISTS vocab_table (\n"
                    + "	entry text PRIMARY KEY,\n"
                    + "	meaning text,\n"
                    + "	word_class text\n"
                    + ");";

            statement.execute(sql);

        } catch (Exception sqlex) {
            sqlex.printStackTrace();
            controller.view.showMessage("Open database error", false);
        }
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public boolean add(VocabData vocabdata) {
        try {
            String sql = "INSERT OR REPLACE INTO vocab_table(entry, meaning, word_class) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, vocabdata.entry.trim());
            stmt.setString(2, vocabdata.meaning.trim());
            stmt.setString(3, vocabdata.wordClass.trim());
            stmt.executeUpdate(); 
            return true;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return false;
        }
    }

    public boolean remove(String entry) {
        try {
            String sql = "DELETE FROM vocab_table WHERE entry = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entry);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return false;
        }
    }

    public String[] lookup(String entry) {
        try {
            String sql = "SELECT meaning, word_class FROM vocab_table WHERE entry = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entry);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString(1), rs.getString(2)};
            } else {
                return null;
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }
    }

}
