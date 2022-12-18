package br.com.guilherme.service;

import br.com.guilherme.modelo.Cidade;
import br.com.guilherme.repository.CidadeRepository;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){
        return cidadeRepository.save(cidade);
    }
    public void remover (Long cidadeId){
        try{
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro" +
                    "de cidade com codigo %d", cidadeId));
        }
    }
}
