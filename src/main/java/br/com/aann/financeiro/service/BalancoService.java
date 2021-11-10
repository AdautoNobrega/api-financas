package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.BalancoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import org.springframework.stereotype.Service;

@Service
public interface BalancoService {

    BalancoDTO recuperarBalanco(BalancoFilter filtro);

}
