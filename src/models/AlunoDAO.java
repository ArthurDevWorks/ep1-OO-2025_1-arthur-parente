package models;

import entities.Aluno;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class AlunoDAO {
    private static Connection connection;
    private static PreparedStatement st; //Preparacao da query
    private static ResultSet rs; //Resultado da consulta

    public static List<Aluno> listarAlunos() throws Exception{
        try {
            connection = GerenteConexao.getConnection();

            String sql = "SELECT * FROM ALUNOS"; //Comando sql

            st = connection.prepareStatement(sql);

            rs = st.executeQuery("select * from department");

            while (rs.next()) {

                String nome = rs.getString("nome");
                int matricula = rs.getInt("matricula");
                Date nascimento = rs.getDate("nascimento");
                String sexo = rs.getString("sexo");
                String email = rs.getString("email");
                String curso = rs.getString("curso");
                int especial = rs.getInt("especial");

                Aluno aluno = new Aluno(nome, matricula, nascimento, sexo, email, curso, especial);
                alunos.add(aluno);
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
