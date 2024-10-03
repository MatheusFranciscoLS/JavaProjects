package com.example;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private double valorTotal;

    public Produto(String nome, int quantidade, double preco, double valorTotal) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.valorTotal = valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", valorTotal=" + valorTotal +
                '}';
    }
}