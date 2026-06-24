package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Representa o resultado de uma partida da Copa do Mundo 2026.
 */
@Entity
@Table(name = "partidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O país mandante é obrigatório")
    private String paisMandante;

    @NotBlank(message = "O país visitante é obrigatório")
    private String paisVisitante;

    @NotNull(message = "Os gols do mandante são obrigatórios")
    @Min(value = 0, message = "Os gols não podem ser negativos")
    private Integer golsMandante;

    @NotNull(message = "Os gols do visitante são obrigatórios")
    @Min(value = 0, message = "Os gols não podem ser negativos")
    private Integer golsVisitante;

    // Enum armazenado como String no banco para legibilidade (GRUPO, OITAVAS, QUARTAS, SEMI, FINAL)
    @NotNull(message = "A fase da partida é obrigatória")
    @Enumerated(EnumType.STRING)
    private Fase fase;

    @NotBlank(message = "O estádio é obrigatório")
    private String estadio;

    @NotNull(message = "A data da partida é obrigatória")
    private LocalDate dataDaPartida;
}
