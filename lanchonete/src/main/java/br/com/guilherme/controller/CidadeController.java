package br.com.guilherme.controller;

import br.com.guilherme.modelo.Cidade;
import br.com.guilherme.repository.CidadeRepository;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import br.com.guilherme.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ObjectInputFilter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.io.ObjectInputFilter.merge;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;


    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Optional<Cidade> cidade(@PathVariable Long cidadeId){
        return Optional.ofNullable(cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException("Cidade nao encontrada")));
    }
    @GetMapping("/{produtoId}")
    public Cidade buscar(@PathVariable Long cidadeId){
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));
    }

    @PatchMapping("/{cidadeId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long cidadeId, @RequestBody Map<String, Object> campos){
        Cidade cidadeAtual = buscar(cidadeId);

        if(cidadeAtual == null){
            return ResponseEntity.notFound().build();
        }

        merge((ObjectInputFilter) campos, (ObjectInputFilter) cidadeAtual);

        return atualizar(cidadeId, cidadeAtual);
    }



    private ResponseEntity<?> atualizar(Long cidadeId, Cidade cidadeAtual) {
        return null;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        return cadastroCidadeService.salvar(cidade);
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long cidadeId){
        cadastroCidadeService.remover(cidadeId);
    }
}
