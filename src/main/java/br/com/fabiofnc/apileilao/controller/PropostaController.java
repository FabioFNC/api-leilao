package br.com.fabiofnc.apileilao.controller;

import br.com.fabiofnc.apileilao.controller.dto.PropostaDto;
import br.com.fabiofnc.apileilao.controller.form.AtualizarPropostaForm;
import br.com.fabiofnc.apileilao.controller.form.PropostaForm;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<PropostaDto>> pegarTodasPropostas() {
        List<Proposta> propostas = propostaRepository.findAll();
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
        propostaRepository.save(proposta);
        URI uri = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDto(proposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PropostaDto> atualizarProposta(@PathVariable Long id, @RequestBody @Valid AtualizarPropostaForm form) {
        Optional<Proposta> optionalProposta = propostaRepository.findById(id);
        if (optionalProposta.isPresent()) {
            Proposta proposta = form.atualizar(id, propostaRepository);
            propostaRepository.save(proposta);
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
