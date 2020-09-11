package tinnova.prova.core.service.veiculos;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import tinnova.prova.application.dtos.out.OutRegistros;
import tinnova.prova.core.model.VeiculoEntity;
import tinnova.prova.core.model.VeiculoObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class VeiculosTask {

    @Autowired
    private IVeiculosService veiculosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<VeiculoObject> getAllVeiculos() {
        List<VeiculoObject> objects = new ArrayList<>();
        List<VeiculoEntity> veiculos = veiculosService.getAllVeiculos();
        veiculos.forEach(veiculo -> objects.add(veiculoEntityToObject(veiculo)));
        return objects;
    }

    public VeiculoObject createVeiculo(VeiculoObject input) {
        VeiculoEntity found = veiculosService.cadastrarVeiculo(veiculoObjectToEntity(input));
        return veiculoEntityToObject(found);
    }

    public VeiculoObject getVeiculoById(long id) {
        VeiculoEntity found = veiculosService.buscarVeiculo(id);
        return veiculoEntityToObject(checkFoundNull(found));
    }

    public VeiculoObject updateVeiculo(VeiculoObject input) {
        VeiculoEntity found = veiculosService.atualizarVeiculo(veiculoObjectToEntity(input));
        return veiculoEntityToObject(checkFoundNull(found));
    }

    public VeiculoObject patchVeiculo(VeiculoObject input) {
        VeiculoEntity object = veiculoObjectToEntity(input);

        VeiculoEntity original = veiculosService.buscarVeiculo(input.getId());
        if(original == null) {
            return veiculoEntityToObject(checkFoundNull(original));
        } else {
            if(!object.getDescricao().isEmpty()) {
                original.setDescricao(object.getDescricao());
            }

            if(object.getAno() > 0) {
                original.setAno(object.getAno());
            }

            if(!object.getModelo().isEmpty()) {
                original.setModelo(object.getModelo());
            }

            if(object.getMarca() != original.getMarca()) {
                original.setMarca(object.getMarca());
            }

            if(object.isVendido() != original.isVendido()) {
                original.setVendido(object.isVendido());
            }
        }

        VeiculoEntity found = veiculosService.atualizarVeiculo(original);
        return veiculoEntityToObject(found);
    }

    public void deleteVeiculo(long id) {
        if(veiculosService.buscarVeiculo(id) != null) {
            veiculosService.excluirVeiculo(id);
        }
    }

    public OutRegistros getStatistcs() {
        List<VeiculoEntity> veiculos = veiculosService.getAllVeiculos();
        return processarStatistics(veiculos);
    }

    protected OutRegistros processarStatistics(List<VeiculoEntity> veiculos) {
        OutRegistros resultados = new OutRegistros();
        if(veiculos == null || veiculos.isEmpty()) {
            resultados.setMensagem("Sem veículos para consulta");
            return resultados;
        }

        HashMap<String, Integer> decadas = new HashMap<>();
        HashMap<String, Integer> marcas = new HashMap<>();

        veiculos.forEach(veiculo -> {
            if(veiculo.isVendido()) {
                resultados.setVendidos(resultados.getVendidos() + 1);
            } else {
                resultados.setNaoVendidos(resultados.getNaoVendidos() + 1);
            }

            if(veiculo.getCreated().isAfter(LocalDateTime.now().minusDays(7)) ) {
                resultados.setUltimaSemana(resultados.getUltimaSemana() + 1);
            }

            String decada = String.valueOf(veiculo.getAno()).substring(0, 3);
            decada = decada + "0";

            if(decadas.containsKey(decada)) {
                decadas.put(decada, decadas.get(decada) + 1);
            } else {
                decadas.put(decada, 1);
            }

            String marca = veiculo.getMarca().name();
            if(marcas.containsKey(marca)) {
                marcas.put(marca, marcas.get(marca) + 1);
            } else {
                marcas.put(marca, 1);
            }
        });

        resultados.setDistribuicaoDecadas(decadas);
        resultados.setDistribuicaoMarcas(marcas);
        resultados.setMensagem("");
        return resultados;
    }

    protected VeiculoEntity checkFoundNull(VeiculoEntity found) {
        if(found == null) {
            found = new VeiculoEntity();
            found.setDescricao("Veículo com o id informado não foi encontrado");
        }
        return found;
    }

    protected VeiculoObject veiculoEntityToObject(VeiculoEntity veiculo) {
        return modelMapper.map(veiculo, VeiculoObject.class);
    }

    protected VeiculoEntity veiculoObjectToEntity(VeiculoObject veiculo) {
        return modelMapper.map(veiculo, VeiculoEntity.class);
    }

}
