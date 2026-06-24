package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um estádio sede da Copa do Mundo 2026 (EUA, México e Canadá).
 */
@Entity
@Table(name = "estadios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estadio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do estádio é obrigatório")
    private String nome;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O país sede é obrigatório")
    private String paisSede;

    @NotNull(message = "A capacidade é obrigatória")
    @Min(value = 1, message = "A capacidade deve ser maior que 0")
    private Integer capacidade;
}
