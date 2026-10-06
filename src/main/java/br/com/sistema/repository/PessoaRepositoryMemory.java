package br.com.sistema.repository;

import br.com.sistema.exceptions.PessoaIdNotFound;
import br.com.sistema.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryMemory implements PessoaRepository {

    private List<Pessoa> pessoas;
    private Integer idAtual;

    public PessoaRepositoryMemory() {
        pessoas = new ArrayList<Pessoa>();
        idAtual = 0;
    }
    
    private Integer generateId() {
        idAtual++;
        return idAtual;
    }
    
    @Override
    public void salvar(Pessoa pessoa) {
        pessoa.setId(generateId());
        pessoas.add(pessoa);
    }

    @Override
    public List<Pessoa> listarTodos() {
        return pessoas;
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        for (Pessoa pessoa : pessoas) {
            if(pessoa.getId().equals(id)) {
                return pessoa;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Pessoa pessoa) throws PessoaIdNotFound{
        Pessoa p = buscarPorId(pessoa.getId());
        if (p == null) {
            throw new PessoaIdNotFound();
        }
        p.setNome(pessoa.getNome());
        p.setEmail(pessoa.getEmail());
        p.setCpf(pessoa.getCpf());
    }

    @Override
    public void deletar(Integer id) {
        Pessoa pessoa = buscarPorId(id);
        if (pessoa != null) {
            pessoas.remove(pessoa);
        }
    }
    
}
