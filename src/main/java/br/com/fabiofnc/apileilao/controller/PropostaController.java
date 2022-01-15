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

import br.com.fabiofnc.apileilao.controller.dto.PropostaDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.custom.PropostaConverter;
import br.com.fabiofnc.apileilao.controller.form.AtualizarPropostaForm;
import br.com.fabiofnc.apileilao.controller.form.PropostaForm;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.service.PropostaService;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

    @Autowired
    PagedResourcesAssembler<PropostaDto> assembler;

    @Autowired
    private PropostaService propostaService;
    
    @Autowired
    private PropostaConverter converter;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PropostaDto>>> findAll(
            @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<PropostaDto> propostas = propostaService.findAll(paginacao);
        return ResponseEntity.ok().body(assembler.toModel(propostas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaDto> findById(@PathVariable Long id) {
        Proposta proposta = propostaService.findById(id);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(proposta));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<PropostaDto> create(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder) {
        Proposta proposta = converter.convertFormToEntity(form);
        propostaService.save(proposta);
        URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(converter.convertEntityToDTO(proposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PropostaDto> update(@PathVariable Long id, @RequestBody @Valid AtualizarPropostaForm form) {
        propostaService.update(id, form);
        Proposta proposta = propostaService.findById(id);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(proposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        propostaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
