package tinnova.prova.application.dtos.out;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class OutRegistros {

    private int naoVendidos = 0;
    private int ultimaSemana = 0;
    private int vendidos = 0;

    private String mensagem;

    private HashMap<String, Integer> distribuicaoDecadas = new HashMap<>();
    private HashMap<String, Integer> distribuicaoMarcas = new HashMap<>();

}
