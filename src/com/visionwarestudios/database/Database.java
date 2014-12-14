package com.visionwarestudios.database;

import java.sql.Connection;

public abstract class Database {

    public abstract Connection openConnection();

    public abstract Connection getConnection();

    public abstract boolean isOpen();

    public abstract void closeConnection();
}
