package tinnova.prova.core.service.veiculos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tinnova.prova.application.dtos.out.OutRegistros;
import tinnova.prova.core.model.Marca;
import tinnova.prova.core.model.VeiculoEntity;
import tinnova.prova.core.model.VeiculoObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VeiculosTaskTest {

    @Autowired
    private VeiculosTask veiculosTask;
    private VeiculoEntity entity;

    @BeforeEach
    public void CreateEntityTest() {
        entity = new VeiculoEntity();
        entity.setVendido(false);
        entity.setMarca(Marca.VOLKSWAGEN);
        entity.setModelo("Gol 1.6");
        entity.setAno(2016);
    }

    @Test
    public void testCheckFoundNull() {
        entity = null;
        entity = veiculosTask.checkFoundNull(entity);
        assertEquals("Veículo com o id informado não foi encontrado", entity.getDescricao());

        entity.setVendido(true);
        entity = veiculosTask.checkFoundNull(entity);
        assertTrue(entity.isVendido());
    }

    @Test
    public void testVeiculoEntityToObject() {
        VeiculoObject obj = veiculosTask.veiculoEntityToObject(entity);
        assertEquals("Gol 1.6", obj.getModelo());
        assertEquals(Marca.VOLKSWAGEN, obj.getMarca());
        assertFalse(obj.isVendido());
    }

    @Test
    public void testVeiculoObjectToEntity() {
        VeiculoObject obj = veiculosTask.veiculoEntityToObject(entity);
        entity = veiculosTask.veiculoObjectToEntity(obj);
        assertEquals("Gol 1.6", entity.getModelo());
        assertEquals(Marca.VOLKSWAGEN, entity.getMarca());
        assertFalse(entity.isVendido());
    }

    @Test
    public void testProcessarStatistics() {
        List<VeiculoEntity> veiculos = new ArrayList<>();
        veiculos.add(entity);

        OutRegistros out = veiculosTask.processarStatistics(veiculos);

        assertEquals(1,out.getNaoVendidos());
        assertEquals(1, out.getUltimaSemana());
        assertFalse(out.getDistribuicaoDecadas().containsKey("1900"));
        assertTrue(out.getDistribuicaoDecadas().containsKey("2010"));
        assertTrue(out.getDistribuicaoMarcas().containsKey(Marca.VOLKSWAGEN.name()));
    }
}
