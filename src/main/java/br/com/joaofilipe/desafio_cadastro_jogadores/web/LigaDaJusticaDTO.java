package br.com.joaofilipe.desafio_cadastro_jogadores.web;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "liga-da-justica")
public record LigaDaJusticaDTO(@JacksonXmlProperty(localName = "codinomes") CodinomesDTO codinomes)
        implements CodinomeDTO {

    @Override
    public List<String> getCodinomes() {
        return codinomes.codinomes();
    }

}

record CodinomesDTO(
        @JacksonXmlProperty(localName = "codinome") @JacksonXmlElementWrapper(useWrapping = false) List<String> codinomes) {
}
