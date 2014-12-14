package com.visionwarestudios.database.sqlite;

import com.visionwarestudios.database.Database;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite extends Database
    {

    private final File database;
    private Connection connection;

    private static final Logger logger = Logger.getLogger(SQLite.class.getName());

    /**
     * Initialises a SQLlite object.
     *
     * @param database
     */
    public SQLite(File database)
        {
        this.database = database;
        }

    /**
     * If the connection isn't open, it will connect to it.
     *
     * @return The connection to the SQLite database.
     */
    @Override
    public Connection openConnection()
        {
        if (!(this.database.exists()))
            {
            try
                {
                this.database.createNewFile();
                }
            catch (IOException ex)
                {
                logger.log(Level.SEVERE, "Can't create database file.");
                }
            }

        try
            {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.database);
            }
        catch (ClassNotFoundException ex)
            {
            logger.log(Level.SEVERE, "JDBC Driver not found. Can't connect to the database.");
            }
        catch (SQLException ex)
            {
            logger.log(Level.SEVERE, "Could not connect to the SQLite Server because:\n{0}", ex);
            }

        return this.connection;
        }

    /**
     * This will return the connection if it's open or not.
     *
     * @return The connection to the database.
     */
    @Override
    public Connection getConnection()
        {
        return this.connection;
        }

    /**
     * Checks if the connection is open.
     *
     * @return A boolean value if the connection is open.
     */
    @Override
    public boolean isOpen()
        {
        return connection != null;
        }

    /**
     * Closes the connection to the database.
     */
    @Override
    public void closeConnection()
        {
        if (!isOpen())
            {
            try
                {
                this.connection.close();
                }
            catch (SQLException ex)
                {
                logger.log(Level.SEVERE, "Could not close the SQLite connection because:\n{0}", ex);
                }
            }
        }
    }
