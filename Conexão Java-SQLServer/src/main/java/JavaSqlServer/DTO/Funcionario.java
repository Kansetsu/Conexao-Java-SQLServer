package JavaSqlServer.DTO;


import java.sql.ResultSet;
import java.sql.SQLException;


public class Funcionario {
    private Integer ID;
    private String nome;
    private String funcao;
    private Integer idade;

    public Funcionario(ResultSet rs) throws SQLException {
        ID = rs.getInt("ID");
        nome = rs.getString("Nome");
        funcao = rs.getString("Funcao");
        idade = rs.getInt("Idade");
    }

    public Funcionario() {

    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                ", funcao='" + funcao + '\'' +
                ", idade=" + idade +
                '}';
    }
}
