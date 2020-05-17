package JavaSqlServer;

import JavaSqlServer.DAO.Dao;
import JavaSqlServer.DTO.Funcionario;
import java.util.List;



public class Main {
    public static void main(String[] args) {

        Dao dao = new Dao();

        List<Funcionario> f = dao.getFuncionario();
        for (Funcionario f1: f){
            System.out.println(f1.toString());
        }
        System.out.println("Acabou");
    }
}

