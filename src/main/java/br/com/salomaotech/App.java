package br.com.salomaotech;

import br.com.salomaotech.controller.CadastroController;

public class App {

    public static void main(String[] args) {

        CadastroController cadastroController = new CadastroController();
        cadastroController.construir();

    }

}
