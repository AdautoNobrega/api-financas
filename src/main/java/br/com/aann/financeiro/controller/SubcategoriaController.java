package br.com.aann.financeiro.controller;

import br.com.aann.financeiro.annotation.ApiV1;
import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.http.Response;
import br.com.aann.financeiro.service.SubcategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiV1("/v1/subcategorias")
@Api(value = "CRUD das subcategorias", tags = {"subcategoria"})
@AllArgsConstructor
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    @GetMapping
    @ApiOperation("Método para recuperar as subcategorias")
    public Response<List<SubcategoriaDTO>> consultarCategorias(
            @ApiParam(name = "nome") @RequestParam(value = "nome", required = false) final String nome) {
        return new Response<>(subcategoriaService.recuperarSubcategorias(nome));
    }

    @GetMapping("/{id_subcategoria}")
    @ApiOperation("Método para recuperar uma subcategoria dado um id")
    public Response<SubcategoriaDTO> consultarCategorias(@PathVariable("id_subcategoria") final Long idSubcategoria) {
        return new Response<>(subcategoriaService.recuperarSubcategoriaPorId(idSubcategoria));
    }

    @PostMapping
    @ApiOperation("Método para criar uma subcategoria")
    public Response<SubcategoriaDTO> criarSubcategoria(@RequestBody final SubcategoriaDTO subcategoriaDTO) {
        return new Response<>(subcategoriaService.criarSubcategoria(subcategoriaDTO));
    }

    @PutMapping
    @ApiOperation("Método para editar uma subcategoria")
    public Response<SubcategoriaDTO> editarCategoria(@RequestBody final SubcategoriaDTO subcategoriaDTO) {
        return new Response<>(subcategoriaService.editarSubcategoria(subcategoriaDTO));
    }

    @DeleteMapping("/{id_subcategoria}")
    @ApiOperation("Método para excluir uma subcategoria")
    public Response<Boolean> excluirCategoria(@PathVariable("id_subcategoria") final Long idCategoria) {
        return new Response<>(subcategoriaService.excluirSubcategoria(idCategoria));
    }
}
