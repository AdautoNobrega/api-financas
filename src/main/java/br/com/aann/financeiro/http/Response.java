package br.com.aann.financeiro.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class Response<T> {

    private T corpo;
    private String mensagem;
    private String codigo;

    public Response(T corpo) {
        this(Objects.isNull(corpo) ? "Sem resultado" : "Sucesso", "req_sucesso");
        this.corpo = corpo;
    }

    public Response(String mensagem, String codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    public Response(T corpo, String mensagem, String codigo) {
        this(mensagem, codigo);
        this.corpo = corpo;
    }
}
