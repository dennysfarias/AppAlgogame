package com.algogame.appalgogame;

public class Jogador {

    int id;
    String nome;
    int fase_id;
    int pontuacao;
    String tempo_total;

    public int getFase_id() {
        return fase_id;
    }

    public void setFase_id(int fase_id) {
        this.fase_id = fase_id;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getTempo_total() {
        return tempo_total;
    }

    public void setTempo_total(String tempo_total) {
        this.tempo_total = tempo_total;
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
}
