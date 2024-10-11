package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.ManutencaoController;
import com.example.models.Manutencao;

public class ManutencaoPanel extends JPanel {
    // Atributos
    private ManutencaoController manutencaoController;
    private JTable manutencoesTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarManutencao;
    private JButton btnGerarRelatorio;

    public ManutencaoPanel() {
        super(new BorderLayout());

        // Inicializando o controlador
        manutencaoController = new ManutencaoController();

        // Inicializando o model da tabela com as colunas
        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Máquina ID", "Data", "Tipo", "Peças Trocadas", "Tempo de Parada", "Técnico ID", "Observações"
        }, 0); // Número de linhas inicial: 0

        // Criar JTable com o model
        manutencoesTable = new JTable(tableModel);

        // Preenchendo a tabela com as manutenções do controlador
        List<Manutencao> manutencoes = manutencaoController.readManutencoes();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[] {
                    manutencao.getId(),
                    manutencao.getMaquinaID(),
                    manutencao.getData(),
                    manutencao.getTipo(),
                    manutencao.getPecasTrocadas(),
                    manutencao.getTempoDeParada(),
                    manutencao.getTecnicoID(),
                    manutencao.getObservacoes()
            });
        }

        // Adicionando a JTable a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Atualizar");
        btnGerarRelatorio = new JButton("Gerar Relatório");
        painelInferior.add(btnCadastrarManutencao);
        painelInferior.add(btnSalvarAlteracoes);
        painelInferior.add(btnGerarRelatorio);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adicionando ActionListeners para os botões
        addActionListeners();
    }

    private void addActionListeners() {
        // ActionListener para o botão "Cadastrar"
        btnCadastrarManutencao.addActionListener(e -> openManutencaoDialog(null));

        // ActionListener para o botão "Salvar Manutenção"
        btnSalvarAlteracoes.addActionListener(e -> {
            int selectedRow = manutencoesTable.getSelectedRow();
            if (selectedRow != -1) {
                openManutencaoDialog(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para editar.");
            }
        });

        // ActionListener para o botão "Gerar Relatório"
        btnGerarRelatorio.addActionListener(e -> gerarRelatorioManutencao());
    }

    private void openManutencaoDialog(Integer selectedRow) {
        JDialog dialog = new JDialog((JDialog) null,
                selectedRow == null ? "Cadastrar Nova Manutenção" : "Editar Manutenção", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(0, 2));

        // Campos do formulário
        JTextField txtMaquinaId = new JTextField();
        JTextField txtData = new JTextField(); // Sugere formato "yyyy-MM-dd"
        JTextField txtTipo = new JTextField();
        JTextField txtPecasTrocadas = new JTextField();
        JTextField txtTempoDeParada = new JTextField();
        JTextField txtTecnicoId = new JTextField();
        JTextField txtObservacoes = new JTextField();

        // Se for edição, preenche os campos com os dados existentes
        if (selectedRow != null) {
            txtMaquinaId.setText((String) tableModel.getValueAt(selectedRow, 1));
            txtData.setText(((LocalDate) tableModel.getValueAt(selectedRow, 2))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            txtTipo.setText((String) tableModel.getValueAt(selectedRow, 3));
            txtPecasTrocadas.setText((String) tableModel.getValueAt(selectedRow, 4));
            txtTempoDeParada.setText(String.valueOf(tableModel.getValueAt(selectedRow, 5)));
            txtTecnicoId.setText((String) tableModel.getValueAt(selectedRow, 6));
            txtObservacoes.setText((String) tableModel.getValueAt(selectedRow, 7));
        }

        // Adiciona campos de texto ao dialog
        dialog.add(new JLabel("Máquina ID:"));
        dialog.add(txtMaquinaId);
        dialog.add(new JLabel("Data (yyyy-MM-dd):"));
        dialog.add(txtData);
        dialog.add(new JLabel("Tipo:"));
        dialog.add(txtTipo);
        dialog.add(new JLabel("Peças Trocadas:"));
        dialog.add(txtPecasTrocadas);
        dialog.add(new JLabel("Tempo de Parada:"));
        dialog.add(txtTempoDeParada);
        dialog.add(new JLabel("Técnico ID:"));
        dialog.add(txtTecnicoId);
        dialog.add(new JLabel("Observações:"));
        dialog.add(txtObservacoes);

        // Botão para cadastrar ou salvar a manutenção
        JButton btnSubmit = new JButton(selectedRow == null ? "Cadastrar" : "Salvar");
        dialog.add(btnSubmit);

        btnSubmit.addActionListener(ev -> {
            try {
                String maquinaId = txtMaquinaId.getText();
                LocalDate data = LocalDate.parse(txtData.getText());
                String tipo = txtTipo.getText();
                String pecasTrocadas = txtPecasTrocadas.getText();
                int tempoDeParada = Integer.parseInt(txtTempoDeParada.getText());
                String tecnicoId = txtTecnicoId.getText();
                String observacoes = txtObservacoes.getText();

                Manutencao manutencao;
                if (selectedRow == null) {
                    manutencao = new Manutencao(null, maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId,
                            observacoes);
                    Manutencao manutencaoCriada = manutencaoController.createManutencao(manutencao);

                    if (manutencaoCriada != null) {
                        tableModel.addRow(new Object[] {
                                manutencaoCriada.getId(),
                                maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId, observacoes
                        });
                        JOptionPane.showMessageDialog(dialog, "Manutenção cadastrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar manutenção.");
                    }
                } else {
                    String id = String.valueOf(tableModel.getValueAt(selectedRow, 0)); // ID da manutenção
                    manutencao = new Manutencao(id, maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId,
                            observacoes);
                    manutencaoController.updateManutencao(manutencao); // Atualiza a manutenção na API

                    // Atualiza a tabela
                    updateTableRow(selectedRow, manutencao);
                    JOptionPane.showMessageDialog(dialog, "Alterações salvas com sucesso!");
                }
                dialog.dispose(); // Fecha o diálogo
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao preencher os dados: " + ex.getMessage());
            }
        });

        // Mostra o formulário
        dialog.setVisible(true);
    }

    private void updateTableRow(int rowIndex, Manutencao manutencao) {
        tableModel.setValueAt(manutencao.getMaquinaID(), rowIndex, 1);
        tableModel.setValueAt(manutencao.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), rowIndex, 2);
        tableModel.setValueAt(manutencao.getTipo(), rowIndex, 3);
        tableModel.setValueAt(manutencao.getPecasTrocadas(), rowIndex, 4);
        tableModel.setValueAt(manutencao.getTempoDeParada(), rowIndex, 5);
        tableModel.setValueAt(manutencao.getTecnicoID(), rowIndex, 6);
        tableModel.setValueAt(manutencao.getObservacoes(), rowIndex, 7);
    }

   // Método para gerar o relatório de manutenção
private void gerarRelatorioManutencao() {
    String filePath = chooseFileLocation();
    if (filePath == null)
        return; // Se o usuário cancelou a escolha do arquivo

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        // Calcular métricas MTTR e MTBF
        double mttr = calculateMTTR();
        double mtbf = calculateMTBF();

        writer.write("Relatório de Manutenção\n");
        writer.write("---------------------------\n");
        writer.write(String.format("MTTR: %.2f horas\n", mttr));
        writer.write(String.format("MTBF: %.2f horas\n", mtbf));
        writer.write("---------------------------\n");
        writer.write("Detalhes das Manutenções:\n");
        writer.write("---------------------------\n");

        // Escrevendo cabeçalho da tabela
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            writer.write(String.format("%-20s", tableModel.getColumnName(col))); // Formatação de coluna
        }
        writer.newLine();

        // Escrevendo os dados da tabela
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                writer.write(String.format("%-20s", tableModel.getValueAt(row, col))); // Formatação de coluna
            }
            writer.newLine();
        }

        writer.write("---------------------------\n");
        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
    }
}


    // Método para escolher a localização do arquivo
    private String chooseFileLocation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null; // Usuário cancelou
    }

    private double calculateMTTR() {
        int totalRepairTime = 0; // Total time taken for repairs
        int totalRepairs = 0; // Total number of repairs

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int repairTime = (int) tableModel.getValueAt(row, 5); // Supondo que a coluna 5 contém o tempo de parada em
                                                                  // horas
            totalRepairTime += repairTime;

            // Contamos somente se foi uma manutenção de falha
            boolean houveFalha = (boolean) tableModel.getValueAt(row, 8); // Supondo que a coluna 8 indica falhas
            if (houveFalha) {
                totalRepairs++;
            }
        }

        return totalRepairs > 0 ? (double) totalRepairTime / totalRepairs : 0.0; // Retorna MTTR
    }

    private double calculateMTBF() {
        int totalOperationalTime = 0; // Total operational time in hours
        int totalFailures = 0; // Total number of failures

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            boolean houveFalha = (boolean) tableModel.getValueAt(row, 8); // Supondo que a coluna 8 indica falhas
            int repairTime = (int) tableModel.getValueAt(row, 5); // Tempo de parada

            // Se houve uma falha, contamos
            if (houveFalha) {
                totalFailures++;
                // Suponha que o tempo total de operação é o tempo de parada
                // Aqui pode ser mais complexo se você tiver uma lógica real de tempo
                // operacional
                totalOperationalTime += repairTime; // Este exemplo usa apenas o tempo de parada
            }
        }

        return totalFailures > 0 ? (double) totalOperationalTime / totalFailures : 0.0; // Retorna MTBF
    }

}
