package br.com.sistema.app;

import br.com.sistema.repository.ConnectionFactory;

public class TestConectionFactory {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.getConnection();
    }
    
}
