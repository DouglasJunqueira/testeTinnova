package tinnova.prova.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeiculoObject {

    private long id;
    private String modelo;
    private Marca marca;
    private int ano;
    private String descricao;
    private boolean vendido;

}
