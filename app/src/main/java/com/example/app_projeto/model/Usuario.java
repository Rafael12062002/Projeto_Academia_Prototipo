package com.example.app_projeto.model;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String usuario_type;
    private boolean exists;

    private String perfil;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario_type() {
        return usuario_type;
    }

    public void setUsuario_type(String usuario_type) {
        this.usuario_type = usuario_type;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
