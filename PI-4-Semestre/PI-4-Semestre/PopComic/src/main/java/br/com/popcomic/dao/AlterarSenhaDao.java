package br.com.popcomic.dao;
import br.com.popcomic.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AlterarSenhaDao {
    public static void alterarSenha(User user) {
        Scanner input = new Scanner(System.in);

        // Inserindo a nova senha
        System.out.print("Digite a nova senha => ");
        String novaSenha = input.nextLine();

        while(novaSenha.length() < 8){
            System.out.println("A senha prcisa ter no mínimo 8 dígitos. Tente novamente!");
            novaSenha = input.nextLine();
        }

        System.out.print("Confirme a nova senha => ");
        String repetirSenha = input.nextLine();

        // Validando as senhas
        if (!novaSenha.equals(repetirSenha)) {
            System.out.println("As senhas não coincidem. A alteração não será realizada.");
            return;
        }

        // Gravando no BD
        String sql = "UPDATE USUARIO SET SENHA = ? WHERE IDUSER = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, novaSenha);
            pstmt.setInt(2, user.getIdUser());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Senha alterada com sucesso!");
                // Atualiza na main
                user.setSenha(novaSenha);
                user.setRepetirSenha(repetirSenha);
            } else {
                System.out.println("Erro ao alterar a senha no banco de dados.");
            }

        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }


}
