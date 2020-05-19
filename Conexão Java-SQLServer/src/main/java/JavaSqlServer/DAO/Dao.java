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
            List<Funcionario> listaDeDados = new ArrayList<Funcionario>();
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