package inmemorydatabase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    Connection createConnection(String url, String user, String password) {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(
                    this.getClass().getName() + "  " + Thread.currentThread().getStackTrace()[1].getMethodName() + " "
                            + Thread.currentThread().getStackTrace()[1].getLineNumber());
            // e.printStackTrace();
        }
        return null;
    }

    Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(
                    this.getClass().getName() + "  " + Thread.currentThread().getStackTrace()[1].getMethodName() + " "
                            + Thread.currentThread().getStackTrace()[1].getLineNumber());
            // e.printStackTrace();
        }
        return null;

    }

    void createTable(Connection connection, Statement statement, String sql) {
        try {
            statement.executeUpdate(sql);
            System.out.println("table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    void insertIntoTable(Connection connection, Statement statement, String sql) {
        try {
            statement.executeUpdate(sql);
            System.out.println("inserted the data");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void showData(Connection connection, Statement statement, String sql) {
        ResultSet rs;
        try {
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                int userid = rs.getInt("userid");
                String username = rs.getString("username");

                // Display values
                System.out.print("\t username : " + username);
                System.out.println("\t userid : " + userid);
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void deleteRecord(Statement statement, String sql) {
        try {
            statement.executeUpdate(sql);
            System.out.println("deleted the record");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    boolean checkTableExist(Connection connection, Statement statement, String tableName) {
        String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES ";
        ResultSet rs;
        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(tableName)) {
                    System.out.println(table_name);
                    return true;
                }
            }
            rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return false;
    }

    void dropTable(Connection connection, Statement statement, String tableName) {
        String sql = "DROP TABLE IF EXISTS  " + tableName;
        try {
            statement.executeUpdate(sql);
            System.out.println("drop the Table");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
