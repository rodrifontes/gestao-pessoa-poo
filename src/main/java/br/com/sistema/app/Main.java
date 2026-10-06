package br.com.sistema.app;

import br.com.sistema.exceptions.PessoaIdNotFound;
import br.com.sistema.model.Pessoa;
import br.com.sistema.repository.PessoaRepository;
import br.com.sistema.repository.PessoaRepositoryMemory;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws PessoaIdNotFound{
        
        Scanner input = new Scanner(System.in);
        PessoaRepository repository = new PessoaRepositoryMemory();
        Integer opcao;
        
        do {            
            System.out.println("===== GESTÃO DE PESSOAS =====");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Listar Todas");
            System.out.println("3 - Atualizar Pessoa");
            System.out.println("4 - Remover Pessoa");
            System.out.println("Digite a opção desejada:");
            
            opcao = input.nextInt();
            
            if(opcao == 1){
                Scanner inputCadastro = new Scanner(System.in);
                System.out.println("Digite o nome da pessoa:");
                String nome = inputCadastro.nextLine();
                System.out.println("Digite o CPF da pessoa:");
                String cpf = inputCadastro.nextLine();
                System.out.println("Digite o e-mail da pessoa:");
                String email = inputCadastro.nextLine();
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
                Scanner inputAtualizacao = new Scanner(System.in);
                System.out.println("Digite o ID da pessoa a ser atualizada:");
                Integer id = inputAtualizacao.nextInt();
                Pessoa pessoa = repository.buscarPorId(id);
                if(pessoa == null) {
                    System.out.println("Pessoa não encontrada!");
                } else {
                    inputAtualizacao = new Scanner(System.in);
                    System.out.println("Pessoa encontrada: " + pessoa);
                    System.out.println("Digite o novo nome:");
                    String nome = inputAtualizacao.nextLine();
                    System.out.println("Digite o novo CPF:");
                    String cpf = inputAtualizacao.nextLine();
                    System.out.println("Digite o novo email:");
                    String email = inputAtualizacao.nextLine();
                    Pessoa pessoaAtualizada = new Pessoa(id, nome, cpf, email);
                    repository.atualizar(pessoaAtualizada);
                }
            }
            
            if(opcao == 4) {
                Scanner inputRemocao = new Scanner(System.in);
                System.out.println("Digite o ID da pessoa a ser excluida:");
                Integer id = inputRemocao.nextInt();
                Pessoa pessoa = repository.buscarPorId(id);
                repository.deletar(id);
                System.out.println("Pessoa excluida com sucesso!");
            }
            
        } while (opcao != 0);
        
    }
    
}
