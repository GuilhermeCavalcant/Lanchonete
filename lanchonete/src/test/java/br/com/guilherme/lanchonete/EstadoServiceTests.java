package br.com.guilherme.lanchonete;

import br.com.guilherme.modelo.Estado;
import br.com.guilherme.repository.EntidadeNaoEncontradaException;
import br.com.guilherme.service.CadastroCidadeService;
import br.com.guilherme.service.CadastroEstadoService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EstadoServiceTests {
    @Test
    public void deveAtribuirId_quandoCadastroEstado_ComDadosCorretos(){

        CadastroEstadoService cadastroEstadoService = null;

        // cenário
        Estado novoEstado = new Estado();
        novoEstado.setNome("Alagoas");
        novoEstado.setSigla("AL");

        // ação
        novoEstado = cadastroEstadoService.salvar(novoEstado);

        // validação
        assertThat(novoEstado).isNotNull();
        assertThat(novoEstado.getId()).isNotNull();
    }
    @Test
    public void deveFalhar_quandoCadastroEstado_SemNome() {
        CadastroEstadoService cadastroEstadoService = null;

        Estado novoEstado = new Estado();
        novoEstado.setNome(null);
        ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {cadastroEstadoService.salvar(novoEstado);});
        assertThat(erroEsperado).isNotNull();
    }
    @Test
    public void deveFalhar_QuandoExcluirEstadoInexistente(){
        CadastroEstadoService cadastroEstadoService = null;

        EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () ->{cadastroEstadoService.remover(100L);});
        assertThat(erroEsperado).isNotNull();
    }
}
