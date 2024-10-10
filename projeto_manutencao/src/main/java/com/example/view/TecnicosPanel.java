package com.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TecnicosPanel extends JPanel {
        private JTable tecnicoTable;
        private DefaultTableModel tableModel;
        private JButton btnSalvarAlteracoes;
        private JButton btnCadastrarTecnico;

        public TecnicosPanel() {
                super(new BorderLayout());

                tableModel = new DefaultTableModel(new Object[] {
                                "ID", "Nome", "Especialidade", "Disponibilidade"
                }, 0);

                tecnicoTable = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(tecnicoTable);
                this.add(scrollPane, BorderLayout.CENTER);

                // Adicionar os botões
                JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
                btnCadastrarTecnico = new JButton("Cadastrar");
                btnSalvarAlteracoes = new JButton("Salvar");
                painelInferior.add(btnCadastrarTecnico);
                painelInferior.add(btnSalvarAlteracoes);
                this.add(painelInferior, BorderLayout.SOUTH);

                // Criar Action Listener para os Botões

        }
}
