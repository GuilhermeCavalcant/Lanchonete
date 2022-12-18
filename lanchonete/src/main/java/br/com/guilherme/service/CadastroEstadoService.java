package br.com.guilherme.service;

import br.com.guilherme.modelo.Estado;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import br.com.guilherme.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    //TODO: implementar a exceção DataIntegrityViolationException e inserir ela no método remover do serviço de Estado
    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro" +
                    "de estado com codigo %d", estadoId));
        }
    }
}