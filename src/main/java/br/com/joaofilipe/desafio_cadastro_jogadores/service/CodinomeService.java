package br.com.joaofilipe.desafio_cadastro_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.joaofilipe.desafio_cadastro_jogadores.exception.GrupoCodinomeIndisponivelException;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.joaofilipe.desafio_cadastro_jogadores.repository.CodinomeRepository;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }

    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomesDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomesEmUso);

        if (codinomesDisponiveis.isEmpty()) {
            throw new GrupoCodinomeIndisponivelException();
        }

        return sortearCodinome(codinomesDisponiveis);
    }

    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso)
            throws Exception {
        return buscarCodinomes(grupoCodinome).stream()
                .filter(codinome -> !codinomesEmUso.contains(codinome))
                .toList();
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws Exception {
        CodinomeRepository codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);
        return codinomeRepository.buscarCodinomes().getCodinomes();
    }

    private String sortearCodinome(List<String> codinomesDisponiveis) {
        return codinomesDisponiveis.get((int) (Math.random() * codinomesDisponiveis.size()));
    }
}
