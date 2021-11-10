package br.com.aann.financeiro.util;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Classe utilitária para operações com Strings");
    }

    public static boolean naoEstaVazio(String str) {
        return str != null && !"".equals(str);
    }

    public static boolean estaVazio(String str) {
        return str == null || "".equals(str);
    }
}
