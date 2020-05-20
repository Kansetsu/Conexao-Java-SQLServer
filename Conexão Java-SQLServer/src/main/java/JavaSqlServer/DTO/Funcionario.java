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
