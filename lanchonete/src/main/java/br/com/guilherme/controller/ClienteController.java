package br.com.guilherme.controller;

import br.com.guilherme.modelo.Cliente;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import br.com.guilherme.service.CadastroClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.guilherme.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public Optional<Cliente> buscar(@PathVariable Long clienteId){
        return Optional.ofNullable(clienteRepository.findById(clienteId).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado")));
    }

    @PostMapping
    public Cliente adicionar(@RequestBody Cliente cliente){
        return cadastroClienteService.save(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long clienteId){
        cadastroClienteService.remover(clienteId);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente>
    atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(clienteId);
        if (clienteAtual.isPresent()) {
            BeanUtils.copyProperties(cliente, clienteAtual.get(), "id");
            Cliente clienteSalvo = cadastroClienteService.save(clienteAtual.get());
            return ResponseEntity.ok(clienteSalvo);
        }
        return ResponseEntity.notFound().build();
    }
}
