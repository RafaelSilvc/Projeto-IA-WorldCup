package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o mascote oficial de cada edição da Copa do Mundo.
 */
@Entity
@Table(name = "mascotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do mascote é obrigatório")
    private String nome;

    @NotNull(message = "O ano da copa é obrigatório")
    private Integer anoCopa;

    @NotBlank(message = "O país sede é obrigatório")
    private String paisSede;
}
