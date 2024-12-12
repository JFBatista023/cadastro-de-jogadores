package br.com.joaofilipe.desafio_cadastro_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaofilipe.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.joaofilipe.desafio_cadastro_jogadores.model.Jogador;
import br.com.joaofilipe.desafio_cadastro_jogadores.service.JogadorService;

@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {
    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaCadastroJogador(Model model) {
        model.addAttribute("jogador", new Jogador(null, null, null, null, null));
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }

    @PostMapping
    public String cadastrarJogador(@ModelAttribute Jogador jogador) {
        try {
            jogadorService.registrarJogador(jogador);
        } catch (Exception e) {
            return "redirect:/cadastro-jogador";
        }
        return "redirect:/cadastro-jogador";
    }

}
