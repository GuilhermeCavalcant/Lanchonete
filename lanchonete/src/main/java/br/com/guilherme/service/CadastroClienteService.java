package br.com.guilherme.service;

import br.com.guilherme.modelo.Cliente;
import br.com.guilherme.repository.ClienteRepository;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void remover(Long clienteId){
        try{
            clienteRepository.deleteById(clienteId);
        }catch(InvalidDataAccessApiUsageException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe "+
                    "um cadastro de cliente com esse codigo %d", clienteId));
        }
    }
}
