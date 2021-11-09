package br.com.aann.financeiro.mapper;

import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.model.LancamentoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LancamentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoDTO map(LancamentoEntity lancamento) {
        return modelMapper.map(lancamento, LancamentoDTO.class);
    }

    public LancamentoEntity map(LancamentoDTO lancamentoDTO) {
        return modelMapper.map(lancamentoDTO, LancamentoEntity.class);
    }

    public List<LancamentoDTO> mapToList(List<LancamentoEntity> lancamentoEntityList) {
        return lancamentoEntityList.stream().map(this::map).collect(Collectors.toList());
    }
}
