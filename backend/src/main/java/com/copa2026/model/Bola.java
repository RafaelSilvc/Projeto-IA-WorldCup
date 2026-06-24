package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a bola oficial utilizada em cada edição da Copa do Mundo.
 */
@Entity
@Table(name = "bolas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome oficial da bola é obrigatório")
    private String nomeOficial;

    @NotNull(message = "O ano da copa é obrigatório")
    private Integer anoCopa;

    @NotBlank(message = "O fabricante é obrigatório")
    private String fabricante;
}
