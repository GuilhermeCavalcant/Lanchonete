package br.com.guilherme.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {


    @Column(length = 50, nullable = false)
    protected String nome;
    @EqualsAndHashCode.Include
    @Column(length = 11)
    protected String cpf;
    @Column(length = 13)
    protected String telefone;
    @Column(length = 40)
    protected String email;
    protected LocalDate dataDeNascimento;
}
