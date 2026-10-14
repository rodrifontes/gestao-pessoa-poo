package br.com.sistema.app;

import br.com.sistema.exceptions.PessoaIdNotFound;
import br.com.sistema.model.Pessoa;
import br.com.sistema.repository.PessoaRepository;
import br.com.sistema.repository.PessoaRepositoryJDBC;
import br.com.sistema.repository.PessoaRepositoryMemory;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws PessoaIdNotFound, Exception{
        
        Scanner input = new Scanner(System.in);
        PessoaRepository repository = new PessoaRepositoryJDBC();
        Integer opcao;
        
        do {            
            System.out.println("===== GESTÃO DE PESSOAS =====");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Listar Todas");
            System.out.println("3 - Atualizar Pessoa");
            System.out.println("4 - Remover Pessoa");
            System.out.println("5 - Buscar por ID");
            System.out.println("Digite a opção desejada:");
            
            opcao = Integer.parseInt(input.nextLine());
            
            if(opcao == 1){
                System.out.println("Digite o nome da pessoa:");
                String nome = input.nextLine();
                System.out.println("Digite o CPF da pessoa:");
                String cpf = input.nextLine();
                System.out.println("Digite o e-mail da pessoa:");
                String email = input.nextLine();
                Pessoa pessoa = new Pessoa(nome, cpf, email);
                repository.salvar(pessoa);
            }
            
            if (opcao == 2) {
                List<Pessoa> pessoas = repository.listarTodos();
                for (Pessoa pessoa : pessoas) {
                    System.out.println(pessoa);
                }
            }
            
            if(opcao == 3) {
                System.out.println("Digite o ID da pessoa a ser atualizada:");
                Integer id = Integer.parseInt(input.nextLine());
                Pessoa pessoa = repository.buscarPorId(id);
                if(pessoa == null) {
                    System.out.println("Pessoa não encontrada!");
                } else {
                    System.out.println("Pessoa encontrada: " + pessoa);
                    System.out.println("Digite o novo nome:");
                    String nome = input.nextLine();
                    System.out.println("Digite o novo CPF:");
                    String cpf = input.nextLine();
                    System.out.println("Digite o novo email:");
                    String email = input.nextLine();
                    Pessoa pessoaAtualizada = new Pessoa(id, nome, cpf, email);
                    repository.atualizar(pessoaAtualizada);
                }
            }
            
            if(opcao == 4) {
                System.out.println("Digite o ID da pessoa a ser excluida:");
                Integer id = Integer.parseInt(input.nextLine());
                Pessoa pessoa = repository.buscarPorId(id);
                repository.deletar(id);
                System.out.println("Pessoa excluida com sucesso!");
            }
            
            if(opcao == 5) {
                System.out.println("Digite o ID da pessoa:");
                Integer id = Integer.parseInt(input.nextLine());
                Pessoa pessoa = repository.buscarPorId(id);
                System.out.println(pessoa);
            }
            
        } while (opcao != 0);
        
    }
    
}
