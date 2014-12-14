package com.visionwarestudios.database.mysql;

import com.visionwarestudios.database.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL extends Database
    {

    private final String hostname;
    private final String port;
    private final String user;
    private final String password;
    private final String database;

    private static final Logger logger = Logger.getLogger(MySQL.class.getName());

    private Connection connection;

    /**
     * Initialises a MySQL object.
     *
     * @param hostname The hostname of the MySQL server
     * @param port The port that the MySQL server is running on
     * @param user The MySQL user to connect with
     * @param password The MySQL's user's password
     * @param database The database to connect to
     */
    public MySQL(String hostname, String port, String user, String password, String database)
        {
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
        }

    /**
     * If the connection isn't open, it will connect to it.
     *
     * @return The connection to the MySQL database.
     */
    @Override
    public Connection openConnection()
        {
        if (!isOpen())
            {
            try
                {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
                }
            catch (ClassNotFoundException ex)
                {
                logger.log(Level.SEVERE, "JDBC Driver not found. Can't connect to the database.");
                }
            catch (SQLException ex)
                {
                logger.log(Level.SEVERE, "Could not connect to the MySQL Server because:\n{0}", ex);
                }
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
                logger.log(Level.SEVERE, "Could not close the MySQL connection because:\n{0}", ex);
                }
            }
        }

    public ResultSet query(String query)
        {
        return query(query, null);
        }

    /**
     * Executes a prepared statement to the database.
     *
     * @param query The SQL query to execute.
     * @param params The parameters of the connection. Read the wiki for more
     * information.
     * @return A ResultSet of the query.
     */
    public ResultSet query(String query, String[] params)
        {
        if (isOpen())
            {
            try
                {
                PreparedStatement statement = this.connection.prepareStatement(query);
                int count = 0;

                if (params != null)
                    {
                    for (String value : params)
                        {
                        for (char c : query.toCharArray())
                            {
                            if (c == '?')
                                {
                                count++;
                                statement.setString(count, value);
                                }
                            }
                        }
                    }

                return statement.executeQuery();
                }
            catch (SQLException ex)
                {
                logger.log(Level.SEVERE, "Could not query the database because:\n{0}", ex);
                return null;
                }
            }
        else
            {
            return null;
            }
        }

    /**
     * This will update the database with the query.
     *
     * @param query The SQL query to execute.
     */
    public void update(String query)
        {
        update(query, null);
        }

    public void update(String query, String[] params)
        {
        if (isOpen())
            {
            try
                {
                PreparedStatement statement = this.connection.prepareStatement(query);

                int count = 1;

                if (params != null)
                    {
                    for (String value : params)
                        {
                        for (char c : query.toCharArray())
                            {
                            if (c == '?')
                                {
                                statement.setString(count, value);
                                }
                            }
                        count++;
                        }
                    }

                statement.executeUpdate();
                }
            catch (SQLException ex)
                {
                logger.log(Level.SEVERE, "Could not update the database because:\n{0}", ex);
                }
            }
        }
    }
