package br.com.popcomic.model;
import br.com.popcomic.dao.UserDao;

import java.util.Scanner;
public class Backoffice {

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("Usuário: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            UserDao userDao = new UserDao();

            User user = userDao.ValidarLogin(email, senha);

            if (user != null) {
                System.out.println("Login bem-sucedido!");
                System.out.println("Bem-vindo, " + user.getNome());
                break;
            } else {
                System.out.println(" << Não foi possivel identificar o usuário, tente novamente! >> ");
            }

        }
    }
}
