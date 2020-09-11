package tinnova.prova.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinnova.prova.core.model.VeiculoEntity;

@Repository
public interface IVeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    VeiculoEntity getById(long id);
}
