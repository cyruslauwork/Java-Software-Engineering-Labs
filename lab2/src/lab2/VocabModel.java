package lab2;

import java.sql.*;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class VocabModel {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    private VocabController controller;

    public void setController(VocabController c) {
        this.controller = c;
    }

    public void connectDB() {
        // Step 1: Load Database Driver CLass        
        try {
            Class.forName("org.sqlite.JDBC"); //SQLite
        } catch (ClassNotFoundException cnfex) {
            controller.view.showMessage("Problem in loading or registering SQLite JDBC driver");
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
                    + "	meaning text\n"
                    + ");";

            statement.execute(sql);

        } catch (Exception sqlex) {
            sqlex.printStackTrace();
            controller.view.showMessage("Open database error");
        }
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public boolean add(String entry, String meaning) {
        try {
            String sql = "INSERT OR REPLACE INTO vocab_table(entry, meaning) VALUES(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entry);
            stmt.setString(2, meaning);
//            stmt.executeUpdate(); // If it is a void function
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if rows were affected, indicating replace operation
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

    public String lookup(String entry) {
        try {
            String sql = "SELECT meaning FROM vocab_table WHERE entry = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entry);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }
    }

}
