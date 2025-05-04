package teste;

import java.sql.SQLException;
import model.GerenteConexao;

public class TesteConnection {
    public static void main(String[] args)throws ClassNotFoundException, SQLException {

        System.out.println(GerenteConexao.getConnection());
    }
}
