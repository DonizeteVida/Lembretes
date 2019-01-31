package br.livroandroid.lembretes.entities;

import java.io.Serializable;

public class Lembrete implements Serializable {

    private Integer codigo;
    private String titulo;
    private String conteudo;
    private Integer realizado; // 0 pra nao // 1 pra sim;
    private Integer nivel_importancia;

    public Lembrete(String titulo, String conteudo, Integer realizado, Integer nivel_importancia) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.realizado = realizado;
        this.nivel_importancia = nivel_importancia;
    }

    public Lembrete() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getRealizado() {
        return realizado;
    }

    public void setRealizado(Integer realizado) {
        this.realizado = realizado;
    }

    public Integer getNivel_importancia() {
        return nivel_importancia;
    }

    public void setNivel_importancia(Integer nivel_importancia) {
        this.nivel_importancia = nivel_importancia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
