package br.com.guilherme.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
