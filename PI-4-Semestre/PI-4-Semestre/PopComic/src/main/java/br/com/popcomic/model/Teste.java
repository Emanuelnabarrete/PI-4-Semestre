package br.com.popcomic.model;

import java.io.Console;

import static br.com.popcomic.dao.AlterarSenhaDao.alterarSenha;

public class Teste {
    public static void main(String[] args) {
        User user = new User();
        user.setIdUser(1);
        alterarSenha(user);
    }
    }

