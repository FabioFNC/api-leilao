package br.com.fabiofnc.apileilao.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fabiofnc.apileilao.controller.dto.ProdutoDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.custom.ProdutoConverter;
import br.com.fabiofnc.apileilao.controller.form.AtualizarProdutoForm;
import br.com.fabiofnc.apileilao.controller.form.ProdutoForm;
import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ProdutoConverter converter;
    
    @Autowired
    PagedResourcesAssembler<ProdutoDto> assembler;
    
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProdutoDto>>> findAll(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<ProdutoDto> produtos = produtoService.findAll(paginacao);
        return ResponseEntity.ok().body(assembler.toModel(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(produto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> create(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = converter.convertFormToEntity(form);
        produtoService.save(produto);
        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return  ResponseEntity.created(uri).body(converter.convertEntityToDTO(produto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
        produtoService.update(id, form);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(produtoService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PutMapping("/{id}/fechar")
    @Transactional
    public ResponseEntity<ProdutoDto> fecharNegociacaoDoProduto(@PathVariable Long id) {
        produtoService.fecharNegociacaoDoProduto(id);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(produtoService.findById(id)));
    }
    
}