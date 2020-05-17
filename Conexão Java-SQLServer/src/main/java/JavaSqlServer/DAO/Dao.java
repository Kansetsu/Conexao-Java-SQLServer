package JavaSqlServer.DAO;

import JavaSqlServer.Config.ConexaoDB;
import JavaSqlServer.Config.SQLServerConfigConstants;
import JavaSqlServer.DTO.Funcionario;
import java.sql.Connection;
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


    public List<Funcionario> getFuncionario() {
        try {
            List<Funcionario> listaDeDados = new ArrayList<Funcionario>();
            Connection conn = conexao.abrir();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Funcionario");
            while (rs.next()) {
                listaDeDados.add(new Funcionario(rs));
            }
            st.close();
            rs.close();
            conn.close();

            return listaDeDados;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}