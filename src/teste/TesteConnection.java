package teste;

import java.sql.SQLException;
import models.GerenteConexao;

public class TesteConnection {
    public static void main(String[] args)throws ClassNotFoundException, SQLException {

        System.out.println(GerenteConexao.getConnection());
    }
}
