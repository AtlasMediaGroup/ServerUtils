package com.visionwarestudios.database.examples;

import com.visionwarestudios.database.mysql.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {

    public static void main(String[] args) {
        MySQL mySQL = new MySQL("localhost", "3306", "root", "root", "test");
        mySQL.openConnection();

        if (mySQL.isOpen()) {
            System.out.println("Connection open.");
        }
        else {
            System.out.println("Connection not open.");
        }

        System.out.println("Truncating users table...");
        mySQL.update("TRUNCATE TABLE `users`");

        int x = 0;

        System.out.println("Generating users...");
        while (x < 11) {
            mySQL.update("INSERT INTO `users`(`ID`, `username`, `password`) VALUES (?,?,?)", new String[]{String.valueOf(x), UUID.randomUUID().toString(), UUID.randomUUID().toString()});
            x++;
        }

        System.out.println("Selecting users...");
        ResultSet result = mySQL.query("SELECT * FROM `users`");

        try {
            if (result == null) {
                if (!mySQL.isOpen()) {
                    System.out.println("The connection isn't open.");
                }
                else {
                    System.out.println("Nothing was returned from the query.");
                }

                return;
            }

            while (result.next()) {
                System.out.println(result.getInt("ID") + " " + result.getString("username") + " " + result.getString("password"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Closing connection...");
        mySQL.closeConnection();
    }
}
