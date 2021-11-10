package br.com.aann.financeiro.mapper;

import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.model.CategoriaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaDTO map(CategoriaEntity categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public CategoriaEntity map(CategoriaDTO categoriaDTO) {
        return modelMapper.map(categoriaDTO, CategoriaEntity.class);
    }
}
