package br.com.aann.financeiro.controller;

import br.com.aann.financeiro.annotation.ApiV1;
import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.http.Response;
import br.com.aann.financeiro.service.CategoriaService;
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

@ApiV1("/v1/categorias")
@Api(value = "CRUD das categorias", tags = {"categoria"})
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @ApiOperation("Método para recuperar as categorias")
    public Response<List<CategoriaDTO>> consultarCategorias(
            @ApiParam(name = "nome") @RequestParam(value = "nome", required = false) final String nome) {
        return new Response<>(categoriaService.recuperarCategorias(nome));
    }

    @GetMapping("/{id_categoria}")
    @ApiOperation("Método para recuperar as categorias")
    public Response<CategoriaDTO> consultarCategorias(@PathVariable("id_categoria") final Long idCategoria) {
        return new Response<>(categoriaService.recuperarCategoriaPorId(idCategoria));
    }

    @PostMapping
    public Response<CategoriaDTO> criarCategoria(@RequestBody final CategoriaDTO categoriaDTO) {
        return new Response<>(categoriaService.criarCategoria(categoriaDTO));
    }

    @PutMapping
    public Response<CategoriaDTO> editarCategoria(@RequestBody final CategoriaDTO categoriaDTO) {
        return new Response<>(categoriaService.editarCategoria(categoriaDTO));
    }

    @DeleteMapping("/{id_categoria}")
    public Response<Boolean> excluirCategoria(@PathVariable("id_categoria") final Long idCategoria) {
        return new Response<>(categoriaService.excluirCategoria(idCategoria));
    }
}