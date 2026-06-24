package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um país campeão da Copa do Mundo, com o ano do título.
 */
@Entity
@Table(name = "campeoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campeao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O país é obrigatório")
    private String pais;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "O técnico é obrigatório")
    private String tecnico;

    @NotBlank(message = "O artilheiro é obrigatório")
    private String artilheiro;
}
