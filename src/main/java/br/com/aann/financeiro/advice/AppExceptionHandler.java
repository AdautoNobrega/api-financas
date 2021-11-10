package br.com.aann.financeiro.advice;

import br.com.aann.financeiro.http.Response;
import br.com.aann.financeiro.http.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Usuário desconectado")
    @ExceptionHandler(value = {UnauthorizedException.class})
    public Response<Object> unauthorized(RuntimeException ex, WebRequest request) {
//        log.error(ERRO_REQUEST, ex.getLocalizedMessage(), request);
        return new Response<>(ex.getMessage(), "erro_autorizacao");
    }

}
