package br.com.joaofilipe.desafio_cadastro_jogadores.repository;

import br.com.joaofilipe.desafio_cadastro_jogadores.web.CodinomeDTO;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws Exception;
}
