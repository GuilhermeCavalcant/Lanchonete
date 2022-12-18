package br.com.guilherme.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity //Indica que a classe que vai virar uma tabela
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;





    public String toString(){
        return this.nome;
    }

}
