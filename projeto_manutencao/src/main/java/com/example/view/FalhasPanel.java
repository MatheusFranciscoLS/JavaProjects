package com.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import com.example.controllers.FalhaController;
import com.example.models.Falha;

public class FalhasPanel extends JPanel {
    private FalhaController falhaController;
    private JTable falhaTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarFalha;

    public FalhasPanel() {
        super(new BorderLayout());
        falhaController = new FalhaController();

        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Máquina ID", "Data", "Problema", "Prioridade", "Operador"
        }, 0);

        falhaTable = new JTable(tableModel);

        // Carregar as falhas
        List<Falha> falhas = falhaController.readFalhas();
        for (Falha falha : falhas) {
            tableModel.addRow(new Object[] {
                    falha.getId(),
                    falha.getMaquinaID(),
                    falha.getData(),
                    falha.getProblema(),
                    falha.getPrioridade(),
                    falha.getOperador()
            });
        }
        JScrollPane scrollPane = new JScrollPane(falhaTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarFalha = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Salvar");
        painelInferior.add(btnCadastrarFalha);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar Action Listener para os Botões
    }
}
