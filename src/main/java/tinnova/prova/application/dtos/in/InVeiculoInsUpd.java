package tinnova.prova.application.dtos.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinnova.prova.core.model.Marca;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Valid
public class InVeiculoInsUpd {

    @JsonProperty(required = true)
    @NotBlank(message = "O modelo do veículo deve ser informado")
    private String modelo;

    @JsonProperty(required = true)
    @NotNull(message = "Obrigatório informar a marca do veículo")
    private Marca marca;

    @JsonProperty(required = true)
    @Min(value = 1888, message = "O primeiro veículo foi inventado em 1888")
    @Max(value = 2020, message = "Não é possível adicionar veículos com fabricação após o ano atual")
    private int ano;

    @JsonProperty(required = true)
    @NotBlank(message = "A descricao do modelo deve ser informada")
    private String descricao;

    @JsonProperty(required = true, defaultValue = "false")
    @NotNull(message = "Deve informar a situação do veículo")
    private boolean vendido;

}
