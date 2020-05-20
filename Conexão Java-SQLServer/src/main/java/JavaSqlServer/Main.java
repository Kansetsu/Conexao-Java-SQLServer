package JavaSqlServer;

import JavaSqlServer.DAO.Dao;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int ID, idade;
        String nome, funcao, opcao;
        Scanner input = new Scanner(System.in);
        Dao dao = new Dao();


        while (true) {

            System.out.println("Escolha uma das opções: \n" +
                    "1 - Exibir todos os Funcionários\n" +
                    "2 - Pesquisar Funcionário\n" +
                    "3 - Adicionar um novo Funcionário\n" +
                    "4 - Remover Funcionário\n" +
                    "5 - Atualizar dados de um Funcionário\n" +
                    "0 - Sair");

            opcao = input.next();
            switch (opcao) {
                case "1":
                    System.out.println("Resultado:\n " + dao.getAllFuncionarios() + "\n");
                    break;
                case "2":
                    System.out.println("Digite o ID do Funcionário: ");
                    ID = input.nextInt();
                    System.out.println("Resultado:\n " + dao.getFuncionarioByID(ID) + "\n");
                    break;
                case "3":
                    System.out.println("Insira o nome do Funcionário: ");
                    nome = input.next();
                    System.out.println("Insira a função do Funcionário: ");
                    funcao = input.next();
                    System.out.println("Insira a idade do Funcionário: ");
                    idade = input.nextInt();
                    dao.insertFuncionario(nome, funcao, idade);
                    System.out.println("Funcionário adicionado com sucesso ao Banco!\n");
                    break;
                case "4":
                    System.out.println("Digite o ID do Funcionário: ");
                    ID = input.nextInt();
                    System.out.println("O Funcionário " + dao.getFuncionarioName(ID) + " foi Removido com Sucesso!\n");
                    dao.deleteFuncionario(ID);
                    break;
                case "5":
                    System.out.println("Digite o ID do Funcionário: ");
                    ID = input.nextInt();
                    System.out.println("Insira o novo nome do Funcionário: ");
                    nome = input.next();
                    System.out.println("Insira a nova função do Funcionário: ");
                    funcao = input.next();
                    System.out.println("Insira a idade do Funcionário: ");
                    idade = input.nextInt();
                    dao.updateFuncionario(ID, nome, funcao, idade);
                    System.out.println("Funcionário atualizado com sucesso!\n");
                case "0":
                    System.out.println("Finalizando consulta ao banco ...");
                    System.exit(0);

            }
        }
    }
}

