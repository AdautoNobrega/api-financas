package br.com.aann.financeiro.security;

import br.com.aann.financeiro.http.i18n.MessageProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 3)
public class SecurityFilter extends OncePerRequestFilter {

    private final MessageProperties messageProperties;
    private final HandlerExceptionResolver resolver;
    private final String key;
    private final String token;

    @Autowired
    public SecurityFilter(MessageProperties messageProperties,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
            @Value("${aplicacao.key:api-key}") String key, @Value("${aplicacao.token:aXRhw7o=}") String token) {
        this.messageProperties = messageProperties;
        this.resolver = resolver;
        this.key = key;
        this.token = token;
    }

    @Override
    public void doFilterInternal(HttpServletRequest httpReq, @NonNull HttpServletResponse httpRes,
            @NonNull FilterChain filterChain)
            throws IOException, ServletException {

        if (httpReq.getMethod().contains("OPTIONS")) {
            filterChain.doFilter(httpReq, httpRes);
            return;
        }
        try {
            if (httpReq.getRequestURL().toString().contains("/v1/") &&
                    !token.equals(httpReq.getHeader(key))) {
                resolver.resolveException(httpReq, httpRes, null,
                        new AccessDeniedException(messageProperties.getMessage("usuario.termonaoaceito")));
                return;
            }
        } catch (Exception ex) {
            log.error("Erro ao comparar autenticação do header", ex);
            resolver.resolveException(httpReq, httpRes, null, ex);
            return;
        }

        filterChain.doFilter(httpReq, httpRes);

    }
}
