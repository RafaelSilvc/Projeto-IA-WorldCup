package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o artilheiro de uma determinada edição da Copa do Mundo.
 * Possui relacionamento ManyToOne com Jogador.
 */
@Entity
@Table(name = "artilheiros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artilheiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O jogador é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    @NotNull(message = "O ano da copa é obrigatório")
    private Integer anoCopa;

    @NotNull(message = "O total de gols é obrigatório")
    @Min(value = 0, message = "O total de gols não pode ser negativo")
    private Integer totalGols;
}
