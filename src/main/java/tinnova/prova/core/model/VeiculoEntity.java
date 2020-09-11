package tinnova.prova.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "veiculos")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String modelo;
    private Marca marca;
    private int ano;

    @Column(columnDefinition = "text")
    private String descricao;

    private boolean vendido = false;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;

}
