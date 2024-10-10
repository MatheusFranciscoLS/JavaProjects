package com.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import com.example.controllers.MaquinaController;
import com.example.models.Maquina;

public class MaquinasPanel extends JPanel {
    private MaquinaController maquinaController;
    private JTable maquinaTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarMaquina;

    public MaquinasPanel() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();

        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Nome", "Fabricante", "Modelo", "Detalhes", "Localização", "Tempo Vida"
        }, 0);

        maquinaTable = new JTable(tableModel);

        // Carregar as máquinas
        List<Maquina> maquinas = maquinaController.readMaquinas();
        for (Maquina maquina : maquinas) {
            tableModel.addRow(new Object[] {
                    maquina.getId(),
                    maquina.getNome(),
                    maquina.getFabricante(),
                    maquina.getModelo(),
                    maquina.getDetalhes(),
                    maquina.getLocalizacao(),
                    maquina.getTempoVidaEstimado()
            });
        }
        JScrollPane scrollPane = new JScrollPane(maquinaTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Salvar");
        painelInferior.add(btnCadastrarMaquina);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar Action Listener para Botões
    }
}
