package br.com.joaofilipe.desafio_cadastro_jogadores;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.joaofilipe.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.Jogador;

@SpringBootTest
@AutoConfigureMockMvc
class DesafioCadastroJogadoresApplicationIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void cadastrarListarJogadorSucesso() throws Exception {
		Jogador jogador = new Jogador(
				"João Silva",
				"joao.silva@email.com",
				"11999999999",
				null,
				GrupoCodinome.LIGA_DA_JUSTICA);

		mockMvc
				.perform(post("/cadastro-jogador")
						.param("nome", jogador.nome())
						.param("email", jogador.email())
						.param("telefone", jogador.telefone())
						.param("grupoCodinome", jogador.grupoCodinome().name()))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/listagem-jogadores"));

		mockMvc
				.perform(get("/listagem-jogadores"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("listagem_jogadores"))
				.andExpect(model().attribute("jogadores", hasSize(1)))
				.andExpect(model().attribute("jogadores", contains(allOf(
						hasToString(containsString(jogador.nome())),
						hasToString(containsString(jogador.email())),
						hasToString(containsString(jogador.telefone())),
						hasToString(containsString(jogador.grupoCodinome().name()))))));
	}

}
