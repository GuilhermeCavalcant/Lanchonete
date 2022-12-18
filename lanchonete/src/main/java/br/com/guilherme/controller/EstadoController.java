package br.com.guilherme.controller;

import br.com.guilherme.modelo.Estado;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.guilherme.service.CadastroEstadoService;
import br.com.guilherme.repository.EstadoRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CadastroEstadoService cadastroEstadoService;


    @GetMapping("/{estadoId}")
    public Optional<Estado> buscar(@PathVariable Long estadoId){
        return Optional.of(estadoRepository.findById(estadoId)).orElseThrow(()-> new EntidadeNaoEncontradaException("Estado não encontrado"));
    }

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){
        return cadastroEstadoService.salvar(estado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long estadoId){
        cadastroEstadoService.remover(estadoId);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado>
    atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
        if (estadoAtual.isPresent()) {
            BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
            Estado estadoSalvo = cadastroEstadoService.salvar(estadoAtual.get());
            return ResponseEntity.ok(estadoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

}
