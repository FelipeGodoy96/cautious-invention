package br.com.cautiousinvention.CautiousInvention.model.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
