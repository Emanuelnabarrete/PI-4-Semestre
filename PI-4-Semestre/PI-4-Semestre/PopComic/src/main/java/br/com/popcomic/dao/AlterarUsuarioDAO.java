package br.com.popcomic.dao;

import br.com.popcomic.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AlterarUsuarioDAO extends User {

    public class AlterarCpf {

        public void alterarCpf(User user) {
            Scanner input = new Scanner(System.in);

            System.out.println("Digite o novo CPF:");
            String novoCpf = input.nextLine();

            // Verifica 11 dígitos
            if (novoCpf.length() != 11) {
                System.out.println("O CPF deve ter 11 dígitos.");
                return;
            }

            Connection connection = null;
            try {
                // Conexão com o BD
                connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

                // Grava CPF no BD
                String sql = "UPDATE User SET cpf = ? WHERE IDUSER = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, novoCpf);
                statement.setInt(2, user.getIdUser());

                int linhasAfetadas = statement.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("CPF atualizado com sucesso.");
                    user.setCpf(novoCpf); // Atualiza o CPF no objeto User
                } else {
                    System.out.println("Erro ao atualizar CPF. Usuário não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Finaliza conexão com o BD
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void alterarNome(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome: ");

        String novoNome = input.nextLine();
        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("O nome não pode ser vazio.");
            return;
        }

        user.setNome(novoNome);
        System.out.println("Nome alterado com sucesso para: " + user.getNome());

        // Aqui você pode adicionar a lógica para salvar a alteração no banco de dados
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            String sql = "UPDATE USUARIO SET NOME = ? WHERE IDUSER = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, novoNome);
            statement.setInt(2, user.getIdUser());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nome alterado no banco de dados com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarGrupo(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Seu grupo atual é: " + getGrupo());

        System.out.println("Digite o novo grupo (Administrador/Estorquista): ");
        String novoGrupo = input.nextLine();
        if (novoGrupo.equals(getGrupo())) {
            System.out.println("Grupo já atribuído ao usuário");
        }

        if (!novoGrupo.equalsIgnoreCase("Administrador") && !novoGrupo.equalsIgnoreCase("Estorquista")) {
            System.out.println("Grupo invalido");
            return;
        }
        user.setGrupo(novoGrupo);
        System.out.println("Grupo alterado com sucesso para: " + user.getGrupo());

        // Salva a alteração no banco de dados
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            String sql = "UPDATE USUARIO SET GRUPO = ? WHERE IDUSER = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, novoGrupo);
            statement.setInt(2, user.getIdUser());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Grupo alterado no banco de dados com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
