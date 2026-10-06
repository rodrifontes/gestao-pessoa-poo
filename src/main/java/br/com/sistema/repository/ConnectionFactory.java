package br.com.sistema.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private static final String drive = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost/estacio";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";
    
    public Connection getConnection() throws Exception{
        System.out.println("Conectando...");
        Class.forName(drive);
        Connection connection = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Conectado!");
        return connection;
    }
    
}
