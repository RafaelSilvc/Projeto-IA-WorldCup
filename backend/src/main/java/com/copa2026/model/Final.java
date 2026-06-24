package com.copa2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o histórico de uma final de Copa do Mundo.
 */
@Entity
@Table(name = "finais")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Final {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ano da copa é obrigatório")
    private Integer anoCopa;

    @NotBlank(message = "O país campeão é obrigatório")
    private String paisCampeao;

    @NotBlank(message = "O país vice-campeão é obrigatório")
    private String paisVice;

    @NotNull(message = "Os gols do campeão são obrigatórios")
    @Min(value = 0, message = "Os gols não podem ser negativos")
    private Integer golsCampeao;

    @NotNull(message = "Os gols do vice são obrigatórios")
    @Min(value = 0, message = "Os gols não podem ser negativos")
    private Integer golsVice;

    @NotBlank(message = "O estádio é obrigatório")
    private String estadio;
}
