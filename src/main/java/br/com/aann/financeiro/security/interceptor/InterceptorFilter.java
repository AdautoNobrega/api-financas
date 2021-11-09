package br.com.aann.financeiro.security.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 3)
public class InterceptorFilter implements Filter {

//    @Autowired
//    @Lazy
//    private MessageProperties messageProperties;

    @Value("${aplicacao.key:api-key}")
    private String key;

    @Value("${aplicacao.token:aXRhw7o=}")
    private String token;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;

        if (httpReq.getMethod().contains("OPTIONS")) {
            filterChain.doFilter(httpReq, servletResponse);
            return;
        }
//        BaseRestException baseRestException = null;
        try {
            if (httpReq.getRequestURL().toString().contains("/v1/") &&
                    !token.equals(httpReq.getHeader(key))) {
//                baseRestException = Unauthorized.instance(messageProperties).withMessage("usuario.termonaoaceito")
//                        .withTypeMessage(TypeMessageEnum.TOAST.name()).buildException();
            }
        } catch (Exception e) {
            httpRes.setStatus(HttpStatus.BAD_REQUEST.value());
            httpRes.setContentType("application/json");
//            baseRestException =
//                    BadRequest.instance(messageProperties).withMessage("general.validationerror").withTypeMessage(
//                            TypeMessageEnum.DIALOG.name()).buildException();
        }

//        if (Objects.nonNull(baseRestException)) {
//            throw baseRestException;
//        }

        filterChain.doFilter(servletRequest, httpRes);

    }
}
