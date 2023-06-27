package com.example.app_projeto.model;

public class Usuario {

    private long id;
    private String nome;
    private long cpf;
    private String senha;
    private String usuario_type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
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
}
