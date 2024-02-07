package com.example.progetto.dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Connectivity {
    public Connection conn;
    private static Connectivity SingletonClass=null;

    protected Connectivity() throws IOException, SQLException {
        InputStream input = new FileInputStream("src/main/resources/db.properties");
        Properties properties = new Properties();
        properties.load(input);
        String connection_url = properties.getProperty("CONNECTION_URL");
        String user = properties.getProperty("DB_USER");
        String pass = properties.getProperty("DB_PASS");
        conn = DriverManager.getConnection(connection_url, user, pass);

    }


    //creo un singleton per assicurarmi di avere solo un'istanza di connessione
    public synchronized static Connectivity getSingletonInstance () throws SQLException, IOException {
        if(Connectivity.SingletonClass==null) {
            Connectivity.SingletonClass = new Connectivity();
        }
        return SingletonClass;
    }
}