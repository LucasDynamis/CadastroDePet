package com.example.cadastrodepets;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private double peso;
    private String raca;
    private String cor;
    private String nomeDono;
    private String telefoneDono;

    public Animal(int id, String nome, int idade, double peso, String raca, String cor, String nomeDono, String telefoneDono) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
        this.cor = cor;
        this.nomeDono = nomeDono;
        this.telefoneDono = telefoneDono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getTelefoneDono() {
        return telefoneDono;
    }

    public void setTelefoneDono(String telefoneDono) {
        this.telefoneDono = telefoneDono;
    }
}
