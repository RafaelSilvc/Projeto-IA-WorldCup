package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a música tema oficial de uma edição da Copa do Mundo.
 */
@Entity
@Table(name = "musicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O artista é obrigatório")
    private String artista;

    @NotNull(message = "O ano da copa é obrigatório")
    private Integer anoCopa;
}
