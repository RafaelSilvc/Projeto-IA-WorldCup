package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um jogador de uma seleção participante.
 */
@Entity
@Table(name = "jogadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do jogador é obrigatório")
    private String nome;

    @NotBlank(message = "O país do jogador é obrigatório")
    private String pais;

    @NotBlank(message = "A posição é obrigatória")
    private String posicao; // Ex: Atacante, Meio-campo, Zagueiro, Goleiro

    @NotNull(message = "O número da camisa é obrigatório")
    @Min(value = 1, message = "O número da camisa deve ser maior que 0")
    private Integer numeroCamisa;

    @NotNull(message = "O total de gols é obrigatório")
    @Min(value = 0, message = "O total de gols não pode ser negativo")
    private Integer totalGols;

    @NotNull(message = "O número de copas disputadas é obrigatório")
    @Min(value = 0, message = "O número de copas disputadas não pode ser negativo")
    private Integer copasDisputadas;
}
