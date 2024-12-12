package br.com.joaofilipe.desafio_cadastro_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaofilipe.desafio_cadastro_jogadores.exception.GrupoCodinomeIndisponivelException;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.Jogador;
import br.com.joaofilipe.desafio_cadastro_jogadores.service.JogadorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {
    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaCadastroJogador(Model model) {
        return getViewAndModel(model, new Jogador(null, null, null, null, null));
    }

    @PostMapping
    public String cadastrarJogador(@ModelAttribute @Valid Jogador jogador, BindingResult result, Model model)
            throws Exception {
        if (result.hasErrors()) {
            return getViewAndModel(model, jogador);
        }

        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/cadastro-jogador";
        } catch (GrupoCodinomeIndisponivelException e) {
            result.rejectValue("grupoCodinome", "grupoCodinomeIndisponivel", e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return getViewAndModel(model, jogador);
        }
    }

    private String getViewAndModel(Model model, Jogador jogador) {
        model.addAttribute("jogador", jogador);
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }
}
