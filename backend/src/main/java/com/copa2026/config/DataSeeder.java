package com.copa2026.config;

import com.copa2026.model.*;
import com.copa2026.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Popula o banco H2 com dados de exemplo ao iniciar a aplicação.
 * Útil para testar os endpoints sem precisar cadastrar tudo manualmente.
 * Executado automaticamente pelo Spring Boot ao subir o contexto (CommandLineRunner).
 */
@Configuration
public class DataSeeder {

    @org.springframework.context.annotation.Bean
    public CommandLineRunner popularBanco(
            PaisRepository paisRepository,
            JogadorRepository jogadorRepository,
            EstadioRepository estadioRepository,
            BolaRepository bolaRepository,
            MascoteRepository mascoteRepository,
            PartidaRepository partidaRepository,
            FinalRepository finalRepository,
            CampeaoRepository campeaoRepository,
            MusicaRepository musicaRepository,
            DesempenhoJogadorRepository desempenhoRepository,
            ArtilheiroRepository artilheiroRepository
    ) {
        return args -> {

            // ----- Países -----
            Pais brasil = paisRepository.save(new Pais(null, "Brasil", "BRA", "CONMEBOL"));
            Pais argentina = paisRepository.save(new Pais(null, "Argentina", "ARG", "CONMEBOL"));
            Pais eua = paisRepository.save(new Pais(null, "Estados Unidos", "USA", "CONCACAF"));
            paisRepository.save(new Pais(null, "México", "MEX", "CONCACAF"));
            paisRepository.save(new Pais(null, "Canadá", "CAN", "CONCACAF"));
            paisRepository.save(new Pais(null, "França", "FRA", "UEFA"));

            // ----- Jogadores -----
            Jogador mbappe = jogadorRepository.save(new Jogador(null, "Kylian Mbappé", "França", "Atacante", 10, 48, 2));
            Jogador messi = jogadorRepository.save(new Jogador(null, "Lionel Messi", "Argentina", "Atacante", 10, 13, 5));
            jogadorRepository.save(new Jogador(null, "Vinícius Júnior", "Brasil", "Atacante", 7, 6, 1));
            jogadorRepository.save(new Jogador(null, "Rodrigo De Paul", "Argentina", "Meio-campo", 7, 4, 2));

            // ----- Estádios (Copa 2026: EUA, México e Canadá) -----
            Estadio metlife = estadioRepository.save(new Estadio(null, "MetLife Stadium", "East Rutherford", "Estados Unidos", 82500));
            estadioRepository.save(new Estadio(null, "Estadio Azteca", "Cidade do México", "México", 87523));
            estadioRepository.save(new Estadio(null, "BC Place", "Vancouver", "Canadá", 54500));

            // ----- Bolas oficiais -----
            bolaRepository.save(new Bola(null, "Al Rihla", 2022, "Adidas"));
            bolaRepository.save(new Bola(null, "Telstar 18", 2018, "Adidas"));

            // ----- Mascotes -----
            mascoteRepository.save(new Mascote(null, "La'eeb", 2022, "Catar"));
            mascoteRepository.save(new Mascote(null, "Zabivaka", 2018, "Rússia"));

            // ----- Partidas -----
            partidaRepository.save(new Partida(null, "Brasil", "Argentina", 2, 1, Fase.GRUPO, metlife.getNome(), LocalDate.of(2026, 6, 15)));
            partidaRepository.save(new Partida(null, "Estados Unidos", "México", 1, 1, Fase.OITAVAS, metlife.getNome(), LocalDate.of(2026, 6, 25)));

            // ----- Finais (histórico) -----
            finalRepository.save(new Final(null, 2022, "Argentina", "França", 4, 2, "Estádio Lusail"));
            finalRepository.save(new Final(null, 2018, "França", "Croácia", 4, 2, "Estádio Lujniki"));

            // ----- Campeões -----
            campeaoRepository.save(new Campeao(null, "Argentina", 2022, "Lionel Scaloni", "Messi"));
            campeaoRepository.save(new Campeao(null, "França", 2018, "Didier Deschamps", "Mbappé"));
            campeaoRepository.save(new Campeao(null, "Brasil", 2002, "Luiz Felipe Scolari", "Ronaldo"));

            // ----- Músicas tema -----
            musicaRepository.save(new Musica(null, "Hayya Hayya (Better Together)", "Trinidad Cardona, Davido, Aisha", 2022));
            musicaRepository.save(new Musica(null, "Live It Up", "Nicky Jam, Will Smith, Era Istrefi", 2018));

            // ----- Desempenho de jogador -----
            desempenhoRepository.save(new DesempenhoJogador(null, messi, "Copa 2022", 7, 3, 7));
            desempenhoRepository.save(new DesempenhoJogador(null, mbappe, "Copa 2022", 8, 2, 7));

            // ----- Artilheiros por edição -----
            artilheiroRepository.save(new Artilheiro(null, mbappe, 2022, 8));
            artilheiroRepository.save(new Artilheiro(null, messi, 2022, 7));
        };
    }
}
