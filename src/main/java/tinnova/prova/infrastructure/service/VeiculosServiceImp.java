package tinnova.prova.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinnova.prova.core.model.VeiculoEntity;
import tinnova.prova.core.service.veiculos.IVeiculosService;
import tinnova.prova.infrastructure.repository.IVeiculoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeiculosServiceImp implements IVeiculosService {

    @Autowired
    private IVeiculoRepository veiculosRepository;

    public List<VeiculoEntity> getAllVeiculos() {
        return veiculosRepository.findAll();
    }

    @Override
    public VeiculoEntity buscarVeiculo(long id) {
        return veiculosRepository.getById(id);
    }

    @Override
    public VeiculoEntity atualizarVeiculo(VeiculoEntity veiculo) {
        veiculo.setUpdated(LocalDateTime.now());
        return cadastrarVeiculo(veiculo);
    }

    @Override
    public VeiculoEntity cadastrarVeiculo(VeiculoEntity veiculo) {
        return veiculosRepository.save(veiculo);
    }

    @Override
    public void excluirVeiculo(long idVeiculo) {
        veiculosRepository.deleteById(idVeiculo);
    }
}
