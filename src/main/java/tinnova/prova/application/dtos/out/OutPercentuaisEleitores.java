package tinnova.prova.application.dtos.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutPercentuaisEleitores {

    @JsonProperty(required = true, value = "percentual_votos_validos")
    private String percentualValidos = "0%";

    @JsonProperty(required = true, value = "percentual_votos_brancos")
    private String percentualBrancos = "0%";

    @JsonProperty(required = true, value = "percentual_votos_nulos")
    private String percentualNulos = "0%";

    @JsonProperty(required = true, value = "informacoes_execucao")
    private ArrayList<String> mensagens = new ArrayList<>();

    public void adicionarMensagem(String mensagem) {
        mensagens.add(mensagem);
    }
}
