package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um país participante da Copa do Mundo 2026.
 */
@Entity
@Table(name = "paises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do país é obrigatório")
    private String nome;

    @NotBlank(message = "A sigla do país é obrigatória")
    @Column(length = 3)
    private String sigla;

    @NotBlank(message = "A confederação é obrigatória")
    private String confederacao; // Ex: UEFA, CONMEBOL, CONCACAF, CAF, AFC, OFC
}
