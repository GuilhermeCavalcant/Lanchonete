package br.com.guilherme.repository;

import br.com.guilherme.modelo.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
