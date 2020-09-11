package tinnova.prova.application.dtos.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Data
@Valid
@NoArgsConstructor
public class InNumeroEleitores {

    @JsonProperty(value = "total_eleitores", required = true)
    @Min(value=1, message = "O número mínimo de eleitores é 1")
    private int totalEleitores;

    @JsonProperty(value = "total_votos_validos", required = true)
    @Min(value=0, message = "Valor é menor que 0, cuidado com os fiscais de mesa...")
    private int totalValidos;

    @JsonProperty(value = "total_votos_nulos", required = true)
    @Min(value=0, message = "Olha a Polícia Federal! Manipular eleições é crime, valor negativo não é aceito")
    private int totalNulos;

    @JsonProperty(value = "total_votos_brancos", required = true)
    @Min(value=0, message = "3 + 3 = 9! Acertou miserável... Não é aceito valores negativos, verifique")
    private int totalBrancos;

    public boolean verificarInvalidadeValores() {
        return totalEleitores < (totalValidos + totalNulos + totalBrancos);
    }

}
