package tinnova.prova.core.service.math;

import org.springframework.stereotype.Service;

@Service
public class SomaMultiplosService {

    public long somarMultiplos(int valorMaximo) {
        int soma = 0;
        while(valorMaximo > 2) {
            if(valorMaximo % 3 == 0) {
                soma = soma + valorMaximo;
            }

            if(valorMaximo % 5 == 0) {
                soma = soma + valorMaximo;
            }
            valorMaximo--;
        }
        return soma;
    }

}
