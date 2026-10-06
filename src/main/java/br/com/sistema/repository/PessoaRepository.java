package br.com.sistema.repository;

import br.com.sistema.exceptions.PessoaIdNotFound;
import br.com.sistema.model.Pessoa;
import java.util.List;

public interface PessoaRepository {

    void salvar(Pessoa pessoa);
    List<Pessoa> listarTodos();
    Pessoa buscarPorId(Integer id);
    void atualizar(Pessoa pessoa) throws PessoaIdNotFound;
    void deletar(Integer id);
    
}
