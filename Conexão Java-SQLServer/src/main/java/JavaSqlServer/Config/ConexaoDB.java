package JavaSqlServer.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {
    private String conexao = "jdbc:sqlserver://localhost:1433;databaseName=Teste;user=vinicius;password=123";
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

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
