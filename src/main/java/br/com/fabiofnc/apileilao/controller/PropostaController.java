package br.com.fabiofnc.apileilao.controller;

import br.com.fabiofnc.apileilao.controller.dto.PropostaDto;
import br.com.fabiofnc.apileilao.controller.form.AtualizarPropostaForm;
import br.com.fabiofnc.apileilao.controller.form.PropostaForm;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropostaService propostaService = new PropostaService(propostaRepository);

    @GetMapping
    public ResponseEntity<Page<PropostaDto>> pegarTodasPropostas(
            @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<Proposta> propostas = propostaRepository.findAll(paginacao);
        return ResponseEntity.ok().body(PropostaDto.converterTodas(propostas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaDto> pegarTodasPropostas(@PathVariable Long id) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        return optionalProposta.map(proposta ->
                ResponseEntity.ok().body(new PropostaDto(proposta)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<PropostaDto> adicionarProposta(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriBuilder) {
        Proposta proposta = form.converter(produtoRepository, usuarioRepository);
        propostaService.save(proposta);
        URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDto(proposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PropostaDto> atualizarProposta(@PathVariable Long id, @RequestBody @Valid AtualizarPropostaForm form) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        if (optionalProposta.isPresent()) {
            propostaService.update(id, form);
            Proposta proposta = form.converter(id, propostaRepository);
            return ResponseEntity.ok().body(new PropostaDto(proposta));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarProposta(@PathVariable Long id) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        if (optionalProposta.isPresent()) {
            propostaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
