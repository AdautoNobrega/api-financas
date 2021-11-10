package br.com.aann.financeiro.util;

import java.util.function.BiFunction;
import java.util.function.Function;

public class HttpUtil {

    private HttpUtil() {
        throw new IllegalStateException("Classe utilitária para operações de requisições");
    }

    public static void recuperarStatus(Function<Object, ?> requisicao, Object... args){
        try {
            requisicao.apply(args);
        } catch (Exception ex) {

        }
    }
}
