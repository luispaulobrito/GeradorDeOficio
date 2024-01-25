package com.example.geradorback.services.error;

public class NegocioException extends RuntimeException{
    private String titulo;
    private String mensagem;

    public NegocioException(String titulo, String mensagem) {
        super(titulo + " " + mensagem);
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
}
