package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenteConexao {
    private static GerenteConexao connect ;

    public static GerenteConexao getInstance(){
        if(connect == null){
            return connect = new GerenteConexao();
        }else{
            return connect;
        }
    }

    private static String conexao = "jdbc:mysql://localhost:3306/ep-oo";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(driver);

        return DriverManager.getConnection(conexao,user,password);
    }

}
