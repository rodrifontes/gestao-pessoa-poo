package br.com.sistema.exceptions;

public class PessoaIdNotFound extends Exception{

    public PessoaIdNotFound() {
        super("Pessoa com esse identificador não foi encontrada na base de dados!");
    }
    
    
    
    
}
