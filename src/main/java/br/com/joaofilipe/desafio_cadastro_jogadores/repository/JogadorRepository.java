package br.com.joaofilipe.desafio_cadastro_jogadores.repository;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.joaofilipe.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.Jogador;

@Repository
public class JogadorRepository {
    private final JdbcClient jdbcClient;

    public JogadorRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Jogador salvar(Jogador jogador) {
        jdbcClient.sql("INSERT INTO jogadores (nome, email, telefone, codinome, grupo_codinome) VALUES (?, ?, ?, ?, ?)")
                .params(jogador.nome(), jogador.email(), jogador.telefone(), jogador.codinome(),
                        jogador.grupoCodinome().name())
                .update();

        return jogador;
    }

    public List<String> listarCodinomesPorGrupo(GrupoCodinome grupoCodinome) {
        return jdbcClient.sql("SELECT DISTINCT codinome FROM jogadores WHERE grupo_codinome = ?")
                .param(grupoCodinome.name())
                .query(String.class)
                .list();
    }

    public List<Jogador> listarJogadores() {
        return jdbcClient.sql("SELECT * FROM jogadores ORDER BY LOWER(nome), id")
                .query(Jogador.class)
                .list();
    }
}
