package br.com.aann.financeiro.advice;

import br.com.aann.financeiro.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Usuário desconectado")
    @ExceptionHandler(value = {AccessDeniedException.class})
    public Response<Object> unauthorized(RuntimeException ex, WebRequest request) {
        return new Response<>(ex.getMessage(), "erro_autorizacao");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parâmetro inválido")
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Response<Object> erroValidacao(RuntimeException ex, WebRequest request) {
        return new Response<>(ex.getMessage(), "erro_validacao");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro interno")
    @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
    public Response<Object> erroInterno(RuntimeException ex, WebRequest request) {
        return new Response<>(ex.getMessage(), "erro_interno");
    }
}
