package com.example;

public class Main {
    public static void main(String[] args) {
        Exercicio obj = new Exercicio();

        // Exibindo todos os clientes
        obj.exibirClientes();

        // Adicionando um novo cliente
        obj.adicionarCliente("João da Silva", "joao.silva@example.com");

        // Atualizando o e-mail de um cliente
        obj.atualizarEmail(1, "novo.email@example.com");

        // Excluindo um cliente
        obj.excluirCliente(1);

        // Exibindo todos os clientes após as operações
        obj.exibirClientes();
    }
}