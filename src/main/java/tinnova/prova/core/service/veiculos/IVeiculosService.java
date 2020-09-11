package tinnova.prova.core.service.veiculos;

import org.springframework.stereotype.Repository;
import tinnova.prova.core.model.VeiculoEntity;

import java.util.List;

@Repository
public interface IVeiculosService {

    List<VeiculoEntity> getAllVeiculos();

    VeiculoEntity buscarVeiculo(long id);
    VeiculoEntity atualizarVeiculo(VeiculoEntity veiculo);
    VeiculoEntity cadastrarVeiculo(VeiculoEntity veiculo);
    void excluirVeiculo(long idVeiculo);

}
