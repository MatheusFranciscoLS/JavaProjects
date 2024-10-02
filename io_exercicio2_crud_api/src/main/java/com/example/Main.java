package com.example;

public class Main {
    public static void main(String[] args) {
        UsuarioController uc = new UsuarioController();
        uc.createUser(new Usuario("", "Matheus", 25, "Rua a"));
        uc.read();
    }
}