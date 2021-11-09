package br.com.aann.financeiro.mapper;

import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubcategoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SubcategoriaDTO map(SubcategoriaEntity subcategoria) {
        return modelMapper.map(subcategoria, SubcategoriaDTO.class);
    }

    public SubcategoriaEntity map(SubcategoriaDTO subcategoriaDTO) {
        return modelMapper.map(subcategoriaDTO, SubcategoriaEntity.class);
    }
}
