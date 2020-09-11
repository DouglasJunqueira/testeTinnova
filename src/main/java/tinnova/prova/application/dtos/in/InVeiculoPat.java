package tinnova.prova.application.dtos.in;

import lombok.Data;
import lombok.NoArgsConstructor;
import tinnova.prova.core.model.Marca;

@Data
@NoArgsConstructor
public class InVeiculoPat {

    private String modelo;

    private Marca marca;

    private int ano;

    private String descricao;

    private boolean vendido;
}
