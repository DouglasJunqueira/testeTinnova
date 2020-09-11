package tinnova.prova.core.service.math;

import org.springframework.stereotype.Service;

@Service
public class EleitoresService {

    public String calcularPercentualVotosValidos(int totalEleitores, int totalValidos) {
        return calcularPercentual(totalEleitores, totalValidos);
    }

    public String calcularPercentualVotosBrancos(int totalEleitores, int totalBrancos) {
        return calcularPercentual(totalEleitores, totalBrancos);
    }

    public String calcularPercentualVotosNulos(int totalEleitores, int totalNulos) {
        return calcularPercentual(totalEleitores, totalNulos);
    }

    private String calcularPercentual(double valorTotal, double valorBase) {
        return (valorBase * 100 / valorTotal) + "%";
    }
}
