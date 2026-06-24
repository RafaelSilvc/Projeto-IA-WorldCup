package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o melhor desempenho de um jogador em uma determinada edição da copa.
 * Possui relacionamento ManyToOne com Jogador.
 */
@Entity
@Table(name = "desempenhos_jogador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesempenhoJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o jogador dono do desempenho.
    // Usamos @ManyToOne pois um jogador pode ter desempenhos em várias copas.
    @NotNull(message = "O jogador é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    @NotBlank(message = "A copa (edição) é obrigatória")
    private String copa; // Ex: "Copa 2022", "Copa 2018"

    @NotNull(message = "Os gols são obrigatórios")
    @Min(value = 0, message = "Os gols não podem ser negativos")
    private Integer gols;

    @NotNull(message = "As assistências são obrigatórias")
    @Min(value = 0, message = "As assistências não podem ser negativas")
    private Integer assistencias;

    @NotNull(message = "O número de partidas jogadas é obrigatório")
    @Min(value = 0, message = "As partidas jogadas não podem ser negativas")
    private Integer partidasJogadas;
}
