package br.com.exlivraria.model;

import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 11)
    private String cpf;

    @NotNull
    @Size(min = 5, max = 250)
    private String nome;

    @NotNull
    @Size(min = 35, max = 500)
    private double endereco_entrega;
}
