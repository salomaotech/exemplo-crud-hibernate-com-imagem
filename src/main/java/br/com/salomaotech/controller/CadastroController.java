package br.com.salomaotech.controller;

import br.com.salomaotech.model.entidades.Cliente;
import br.com.salomaotech.model.repositorios.ClienteRepository;
import br.com.salomaotech.model.servicos.ImageLoader;
import br.com.salomaotech.model.servicos.JpaUtil;
import br.com.salomaotech.view.JFView;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class CadastroController {

    private final JpaUtil jpaUtil = new JpaUtil();
    private final JFView view = new JFView();
    private long idAberto;

    private void addEventos() {

        view.jBcadastroNovo.addActionListener((ActionEvent e) -> {

        });

        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            cadastrar();
            carregarResultados();

        });

        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            excluir();
            carregarResultados();

        });

        view.jTresultados.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = view.jTresultados.getSelectedRow();

                if (selectedRow != -1) {

                    abrirCadastro((long) view.jTresultados.getModel().getValueAt(selectedRow, 0));

                }

                view.jBcadastroExcluir.setEnabled(selectedRow != -1);

            }

        });

    }

    private void cadastrar() {

        Cliente cliente = new Cliente();
        cliente.setNome(view.jTcadastroNome.getText());
        cliente.setEmail(view.jTcadastroEmail.getText());
        cliente.setSobrenome(view.jTcadastroSobrenome.getText());
        cliente.setNumeroTelefone(view.jTcadastroTelefone.getText());
        cliente.setImagemPerfil(ImageLoader.lerBytes());

        ClienteRepository clienteRepository = new ClienteRepository(jpaUtil.manager());

        if (idAberto == 0) {

            clienteRepository.persiste(cliente);

        } else {

            cliente.setId(idAberto);
            clienteRepository.merge(cliente);

        }

    }

    private void excluir() {

        if (idAberto != 0) {

            ClienteRepository clienteRepository = new ClienteRepository(jpaUtil.manager());
            clienteRepository.remove(idAberto);

        }

    }

    private void carregarResultados() {

        ClienteRepository clienteRepository = new ClienteRepository(jpaUtil.manager());
        List<Cliente> clientes = clienteRepository.findAll();

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTresultados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;

        for (Cliente cliente : clientes) {

            Object[] linhaDefaultTableModel = new Object[]{
                cliente.getId(),
                cliente.getNome() + " " + cliente.getSobrenome(),
                cliente.getEmail(),
                cliente.getNumeroTelefone()

            };

            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    private void abrirCadastro(long id) {

        ClienteRepository clienteRepository = new ClienteRepository(jpaUtil.manager());
        Cliente cliente = clienteRepository.findById(id);
        idAberto = id;

        view.jTcadastroNome.setText(cliente.getNome());
        view.jTcadastroEmail.setText(cliente.getEmail());
        view.jTcadastroSobrenome.setText(cliente.getSobrenome());
        view.jTcadastroTelefone.setText(cliente.getNumeroTelefone());
        ImageLoader.construirImagem(cliente.getImagemPerfil(), view.jLfoto);

    }

    public void construir() {

        view.setVisible(true);
        addEventos();
        carregarResultados();

    }

}
