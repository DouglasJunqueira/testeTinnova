package tinnova.prova.application.controllers;

import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tinnova.prova.application.dtos.in.InVeiculoInsUpd;
import tinnova.prova.application.dtos.in.InVeiculoPat;
import tinnova.prova.application.dtos.out.OutRegistros;
import tinnova.prova.application.dtos.out.OutVeiculo;
import tinnova.prova.core.model.VeiculoObject;
import tinnova.prova.core.service.veiculos.VeiculosTask;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VeiculosController {

    @Autowired
    private VeiculosTask veiculosTask;

    private final ModelMapper modelMapper = new ModelMapper();

    @ApiOperation(value = "1 - Retorna todos os veículos")
    @GetMapping(value = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OutVeiculo> getVeiculos() {
        List<OutVeiculo> outVeiculos = new ArrayList<>();
        List<VeiculoObject> objects = veiculosTask.getAllVeiculos();
        objects.forEach(veiculo -> {
            outVeiculos.add(veiculoObjectToOut(veiculo));
        });
        return outVeiculos;
    }

    @ApiOperation(value = "2 - Retorna os veículos de acordo com o termo passado parâmetro q")
    @GetMapping(value = "/veiculos/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OutVeiculo> getVeiculosFiltro() {
        // Não entendi a premissa deste exercício...
        return getVeiculos();
    }

    @ApiOperation(value = "3 - Retorna os detalhes do veículo")
    @GetMapping(value = "/veiculos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutVeiculo getVeiculo(@PathVariable(value="id") long id) {
        VeiculoObject object = veiculosTask
                .getVeiculoById(id);
        return veiculoObjectToOut(object);
    }

    @ApiOperation(value = "4 - Adiciona um novo veículo")
    @PostMapping(value = "/veiculos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OutVeiculo createVeiculo(@RequestBody InVeiculoInsUpd input) {
        VeiculoObject object = veiculosTask
                .createVeiculo(veiculoInToObject(input));
        return veiculoObjectToOut(object);
    }

    @ApiOperation(value = "5 - Atualiza os dados de um veículo")
    @PutMapping(value = "/veiculos{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OutVeiculo updateVeiculo(@PathVariable(value="id") long id, @RequestBody InVeiculoInsUpd input) {
        VeiculoObject object = veiculoInToObject(input);
        object.setId(id);

        object = veiculosTask.updateVeiculo(object);
        return veiculoObjectToOut(object);
    }

    @ApiOperation(value = "6 - Atualiza apenas alguns dados do veículo")
    @PatchMapping(value = "/veiculos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OutVeiculo patchVeiculo(@PathVariable(value="id") long id, @RequestBody InVeiculoPat input) {
        VeiculoObject object = veiculoInPatchToObject(input);
        object.setId(id);

        object = veiculosTask.patchVeiculo(object);
        return veiculoObjectToOut(object);
    }

    @ApiOperation(value = "7 - Atualiza apenas alguns dados do veículo")
    @DeleteMapping(value = "/veiculos/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String deleteVeiculo(@PathVariable(value="id") long id) {
        veiculosTask.deleteVeiculo(id);
        return "Request finalizada para o id " + id;
    }

    @ApiOperation(value = "8 - Busca estatísticas dos veículos")
    @GetMapping(value = "/veiculos/statics", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutRegistros registrosVeiculos() {
        return veiculosTask.getStatistcs();
    }

    private VeiculoObject veiculoInPatchToObject(InVeiculoPat input) {
        return modelMapper.map(input, VeiculoObject.class);
    }

    private VeiculoObject veiculoInToObject(InVeiculoInsUpd input) {
        return modelMapper.map(input, VeiculoObject.class);
    }

    private OutVeiculo veiculoObjectToOut(VeiculoObject input) {
        return modelMapper.map(input, OutVeiculo.class);
    }
}
