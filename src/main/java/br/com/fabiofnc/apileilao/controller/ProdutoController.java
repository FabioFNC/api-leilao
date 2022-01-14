package br.com.fabiofnc.apileilao.controller;

import br.com.fabiofnc.apileilao.controller.dto.ProdutoDto;
import br.com.fabiofnc.apileilao.controller.form.AtualizarProdutoForm;
import br.com.fabiofnc.apileilao.controller.form.ProdutoForm;
import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> pegarProdutos(@PageableDefault(page=0, size=10, sort="id", 
    		direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<Produto> produtos = produtoService.findAll(paginacao);
        return ResponseEntity.ok().body(ProdutoDto.converterTodos(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> pegarUmProduto(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(new ProdutoDto(produto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> adicionarProduto(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = form.converter(usuarioRepository);
        produtoService.save(produto);
        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return  ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
        produtoService.update(id, form);
        return ResponseEntity.ok().body(new ProdutoDto(produtoService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}