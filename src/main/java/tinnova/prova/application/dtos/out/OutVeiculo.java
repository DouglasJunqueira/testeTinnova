package tinnova.prova.application.dtos.out;

import lombok.Data;
import lombok.NoArgsConstructor;
import tinnova.prova.core.model.Marca;

@Data
@NoArgsConstructor
public class OutVeiculo {
    private long id;
    private String modelo;
    private Marca marca;
    private int ano;
    private String descricao;
    private boolean vendido;
}
