## Aqui você vai encontrar o exemplo de como acessar o banco de dados MySQLServer utilizando a liguagem Java

Você pode acessar meu [site pessoal](https://kansetsu.netlify.app/portfolio-dicas-0/) para ver as informações do projeto também!

Vou mostrar um pouco do código de como fazer a o acesso ao banco _Lembrando que você precisa ver qual sua senha do banco, usuário e o localhost, hein_?

### Conectando ao Banco

As configurações para conectar ao banco são bem simples, você vai colocar o driver e a conexão com o banco na função do próprio java pra conectar ao banco: 
```java
import java.sql.DriverManager;

public class ConexaoDB {
    private String conexao;
    private String driver;

    public ConexaoDB(String conexao, String driver) {
        this.conexao = conexao;
        this.driver = driver;
    }

    public Connection abrir() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(conexao);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
```
Os dados de acesso ao banco como senha, usuário, nome do banco e o servidor podem ser criados com um ENUM no Java (_Forma mais "limpa" de organizar a conexão_): 

```java
package JavaSqlServer.Config;

public enum SQLServerConfigConstants {

    STRING_CONNECTION("jdbc:sqlserver://localhost:1433;databaseName=Teste;user=vinicius;password=123"),
    STRING_DRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    private String v;

    SQLServerConfigConstants(String v) {
        this.v = v;
    }

    public String getV() {
        return v;
    }
}
```
Agora com o banco conectado ao nosso querido Java, vamos fazer o acesso aos dados do banco

### Acessando dados do DataBase

Depois de conectado ao banco, você pode brincar com os dados que estão lá tranquilamente, normalmente se cria uma classe chamada DAO (_Data Access Object_) que nata mais é do que um objeto que vai acessar os dados do banco, ele vai ser responsável por retornar os dados do banco para um objeto java, mais ou menos assim: 

```java

package JavaSqlServer.DAO;

import JavaSqlServer.Config.ConexaoDB;
import JavaSqlServer.Config.SQLServerConfigConstants;
import JavaSqlServer.DTO.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Dao {
    private ConexaoDB conexao;

    public Dao() {
        conexao = new ConexaoDB(
                SQLServerConfigConstants.STRING_CONNECTION.getV(),
                SQLServerConfigConstants.STRING_DRIVER.getV()
        );
    }


    public Funcionario getFuncionarioByID(Integer ID) {
        try {
            Funcionario funcionario = null;
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Funcionario WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Funcao"),
                        rs.getInt("Idade"));
            }


            rs.close();
            pst.close();

            return funcionario;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unused")
    public String getFuncionarioFuncao(int ID) {
        try {
            Funcionario funcionario = null;
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Funcionario WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Funcao"),
                        rs.getInt("Idade"));
            }


            rs.close();
            pst.close();


            assert funcionario != null;
            return funcionario.getFuncao();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    @SuppressWarnings("unused")
    public int getFuncionarioIdade(int ID) {
        try {
            Funcionario funcionario = null;
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Funcionario WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Funcao"),
                        rs.getInt("Idade"));
            }


            rs.close();
            pst.close();


            assert funcionario != null;
            return funcionario.getIdade();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String getFuncionarioName(int ID) {
        try {
            Funcionario funcionario = null;
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Funcionario WHERE ID = ?");
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Funcao"),
                        rs.getInt("Idade"));
            }


            rs.close();
            pst.close();


            assert funcionario != null;
            return funcionario.getNome();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Funcionario> getAllFuncionarios() {
        try {
            List<Funcionario> listaDeDados = new ArrayList<>();
            Connection conn = conexao.abrir();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Funcionario");
            while (rs.next()) {
                listaDeDados.add(new Funcionario(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Funcao"),
                        rs.getInt("Idade")));
            }
            st.close();
            rs.close();
            conn.close();

            return listaDeDados;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public void insertFuncionario(String nome, String funcao, Integer idade) {
        try {
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Funcionario (Nome, Funcao, Idade) VALUES (?, ?, ?)");
            pst.setString(1, nome);
            pst.setString(2, funcao);
            pst.setInt(3, idade);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void deleteFuncionario(Integer id) {
        try {
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM Funcionario WHERE ID = ?");
            pst.setInt(1, id);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public void updateFuncionario(Integer id, String nome, String funcao, Integer idade) {
        try {
            Connection conn = conexao.abrir();
            PreparedStatement pst = conn.prepareStatement("UPDATE Funcionario" +
                    " SET Nome = ?, Funcao = ?, IDADE = ?" +
                    " WHERE ID = ?");
            pst.setString(1, nome);
            pst.setString(2, funcao);
            pst.setInt(3, idade);
            pst.setInt(4, id);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
```

Aqui temos alguns métodos que eu criei pra fazer o nosso conhecido CRUD (_Creat, Read, Update and Delete_), só pra exemplificar o que o DAO faz. Esse objeto java que está recebendo os dados do banco, no meu caso, é um funcionário. Eu criei uma classe chamada _funcionario_ que é um objeto simples criado em java, que vai fazer o DTO (_Data Transfer Object_), ou seja, vai receber os dados do banco incorporar as informações dele no nosso java. 

```java
package JavaSqlServer.DTO;


public class Funcionario {
    private Integer ID;
    private String nome;
    private String funcao;
    private Integer idade;


    public Funcionario(Integer ID, String nome, String funcao, Integer idade) {
        this.ID = ID;
        this.nome = nome;
        this.funcao = funcao;
        this.idade = idade;
    }

    @SuppressWarnings("unused")
    public Integer getID() {
        return ID;
    }

    @SuppressWarnings("unused")
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    @SuppressWarnings("unused")
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getFuncao() {
        return funcao;
    }

    @SuppressWarnings("unused")
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


    public Integer getIdade() {
        return idade;
    }

    @SuppressWarnings("unused")
    public void setIdade(Integer idade) {
        this.idade = idade;
    }


    public String toString() {
        return "Funcionario: " +
                "ID = " + ID +
                "|| Nome = " + nome +
                "|| Funcao = " + funcao +
                "|| Idade = " + idade + "\n";
    }
}

```

### Utilizando na prática os dados do Banco

Agora eu criei uma interação simples com o usuário no próprio console do sistema, para poder realizar o CRUD e testar nossa aplicaçãozinha de acesso ao DB

```java
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


```

### Conclusão 

Todo o código completo e mais informações sobre o projeto estão presentes aqui no GitHub e no meu [Site pessoal](https://kansetsu.netlify.app/portfolio-dicas-0/). Se cuida!! 😁
