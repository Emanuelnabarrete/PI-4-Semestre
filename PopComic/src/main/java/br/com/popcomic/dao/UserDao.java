package br.com.popcomic.dao;

import br.com.popcomic.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {



    public User ValidarLogin(String email, String senha) {

        String SQL = "SELECT * FROM USERS WHERE EMAIL = ?";
        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                String hashedSenha = resultSet.getString("SENHA");
                Boolean status = resultSet.getBoolean("STATUS");
                String grupo = resultSet.getNString("GRUPO");
                String nome = resultSet.getNString("NOME");

                if(BCrypt.checkpw(senha, hashedSenha) && status && !grupo.equalsIgnoreCase("CLIENTE")){
                    User user = new User();
                    user.setIdUser(resultSet.getInt("IDUSER"));
                    user.setNome(nome);
                    user.setEmail(email);
                    user.setStatus(status);
                    user.setGrupo(grupo);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return null;
    }
}

