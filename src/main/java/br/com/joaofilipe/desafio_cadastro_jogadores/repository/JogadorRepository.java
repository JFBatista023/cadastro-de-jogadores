package br.com.joaofilipe.desafio_cadastro_jogadores.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.joaofilipe.desafio_cadastro_jogadores.model.Jogador;

@Repository
public class JogadorRepository {
    private final JdbcClient jdbcClient;

    public JogadorRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void salvar(Jogador jogador) {
        jdbcClient.sql("INSERT INTO jogadores (nome, email, telefone, codinome, grupo_codinome) VALUES (?, ?, ?, ?, ?)")
                .params(jogador.nome(), jogador.email(), jogador.telefone(), jogador.codinome(),
                        jogador.grupoCodinome().getNome())
                .update();
    }
}
