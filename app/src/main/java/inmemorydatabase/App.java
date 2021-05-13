/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package inmemorydatabase;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        String url = "jdbc:h2:./mem";
        String user = "test";
        String password = "test";
        String tableName = "people";

        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.createConnection(url, user, password);
        Statement statement = databaseManager.createStatement(connection);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Database Center , please choice anyone of them!!");
        loop: while (true) {
            System.out.println("");
            System.out.println("1. Create Table");
            System.out.println("2. Insert data into User Table");
            System.out.println("3. Display User Table");
            System.out.println("4. Delete User Table");
            System.out.println("5. Exit");
            System.out.println("6. Drop the table");

            // while(scanner.hasNextLine()){ scanner.nextLine();}

            int choice = scanner.nextInt();
            System.out.println(choice);
            switch (choice) {
                case 1:
                    String createTableSQL = "CREATE TABLE  IF NOT EXISTS " + tableName + " ( " + "userid INT NOT NULL, "
                            + " username VARCHAR(50) NOT NULL " + ")";
                    databaseManager.createTable(connection, statement, createTableSQL);
                    break;
                case 2:
                    if (databaseManager.checkTableExist(connection, statement, tableName)) {
                        System.out.println("Enter the NAME To insert : ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println("Enter the ID  To insert : ");
                        int id = scanner.nextInt();

                        String insertSQL = "INSERT INTO " + tableName + " VALUES (" + id + ", '" + name + "') ";
                        databaseManager.insertIntoTable(connection, statement, insertSQL);
                    } else {
                        System.out.println(tableName + " : not exist!");
                    }
                    break;
                case 3:
                    if (databaseManager.checkTableExist(connection, statement, tableName)) {
                        String showSQL = "SELECT * FROM " + tableName;
                        System.out.println("\t<-----Table output -------->");
                        databaseManager.showData(connection, statement, showSQL);
                    } else {
                        System.out.println(tableName + " : not exist!");
                    }
                    break;
                case 4:
                    if (databaseManager.checkTableExist(connection, statement, tableName)) {
                        System.out.println("Enter the ID you want to delete : ");
                        int ids = scanner.nextInt();
                        String deleteSQL = "DELETE FROM " + tableName + " WHERE userid = " + ids;
                        databaseManager.deleteRecord(statement, deleteSQL);
                    } else {
                        System.out.println(tableName + " : not exist!");
                    }
                    break;
                case 5:
                    System.out.println("-->> We are Exiting");
                    break loop;
                case 6:
                    if (databaseManager.checkTableExist(connection, statement, tableName)) {
                        databaseManager.dropTable(connection, statement, tableName);
                    } else {
                        System.out.println(tableName + " : not exist!");
                    }
                    break;
                default:
                    System.out.println("No such choice");
                    break;
            }
        }
        scanner.close();
        databaseManager.closeStatement(statement);
        databaseManager.closeConnection(connection);

    }
}
