package br.com.sistema.repository;

import br.com.sistema.exceptions.PessoaIdNotFound;
import br.com.sistema.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepositoryJDBC implements PessoaRepository {

    private Connection connection;
    
    public PessoaRepositoryJDBC() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connection = connectionFactory.getConnection();
    }
    
    @Override
    public void salvar(Pessoa pessoa) {
        try {
            String sql = "INSERT INTO pessoa (nome, cpf, email) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getCpf());
            ps.setString(3, pessoa.getEmail());
            ps.executeUpdate();  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        try {
            String sql = "SELECT * FROM pessoa ORDER BY nome;";
        
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoas.add(pessoa);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pessoas;
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM pessoa WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Pessoa pessoa = null;
            if(rs.next()){
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
            }
            return pessoa;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void atualizar(Pessoa pessoa) throws PessoaIdNotFound {
        try {
            Pessoa p = buscarPorId(pessoa.getId());
            if (p == null) {
                throw new PessoaIdNotFound();
            }
            String sql = "UPDATE pessoa SET nome = ?, email = ?, cpf = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getCpf());
            ps.setInt(4, pessoa.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        try {
            String sql = "DELETE FROM pessoa WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
