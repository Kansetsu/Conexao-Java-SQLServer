package JavaSqlServer.Config;

import java.sql.Connection;
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
