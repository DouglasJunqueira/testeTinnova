package tinnova.prova.application.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tinnova.prova.application.dtos.in.InNumeroEleitores;
import tinnova.prova.application.dtos.out.OutPercentuaisEleitores;
import tinnova.prova.core.service.math.BubbleSortService;
import tinnova.prova.core.service.math.EleitoresService;
import tinnova.prova.core.service.math.FatorialService;
import tinnova.prova.core.service.math.SomaMultiplosService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/math")
public class MathController {

    // <editor-fold defaultstate="collapsed" desc="1 - Eleitores">
    @Autowired
    private EleitoresService eleitoresService;

    @ApiOperation(value = "1 - Calcular percentuais de eleitores - com validação de campos")
    @PostMapping(value = "/calcular-percentuais", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OutPercentuaisEleitores calcularTodosOsPercentuais(@Valid @RequestBody InNumeroEleitores inputEleitores) {
        OutPercentuaisEleitores outPercentuais;
        if(inputEleitores.verificarInvalidadeValores()) {
            outPercentuais = new OutPercentuaisEleitores();
            outPercentuais.adicionarMensagem("A contagem de votos não bate, houve fraude nessa eleição!");
            return outPercentuais;
        }

        outPercentuais = new OutPercentuaisEleitores();
        outPercentuais.setPercentualValidos(eleitoresService.calcularPercentualVotosValidos(inputEleitores.getTotalEleitores(), inputEleitores.getTotalValidos()));
        outPercentuais.setPercentualBrancos(eleitoresService.calcularPercentualVotosBrancos(inputEleitores.getTotalEleitores(), inputEleitores.getTotalBrancos()));
        outPercentuais.setPercentualNulos(eleitoresService.calcularPercentualVotosNulos(inputEleitores.getTotalEleitores(), inputEleitores.getTotalNulos()));


        outPercentuais.adicionarMensagem("Seus votos foram contabilizados");
        return outPercentuais;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OutPercentuaisEleitores> validarParametros(MethodArgumentNotValidException exception) {
        OutPercentuaisEleitores outPercentuais = new OutPercentuaisEleitores();
        BindingResult binding = exception.getBindingResult();
        outPercentuais.setMensagens(getMensagens(binding.getAllErrors()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outPercentuais);
    }

    private ArrayList<String> getMensagens(Collection<ObjectError> errors) {
        ArrayList<String> mensagens = new ArrayList<>();

        errors.stream().filter(e -> e instanceof FieldError).map(FieldError.class::cast)
                .map(e -> String.format("%s - %s", e.getField(), e.getDefaultMessage())).forEach(mensagens::add);
        errors.stream().filter(e -> !(e instanceof FieldError))
                .map(DefaultMessageSourceResolvable::getDefaultMessage).forEach(mensagens::add);
        return mensagens;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="2 - Bubble">
    @Autowired
    private BubbleSortService bubbleSortService;

    @ApiOperation(value = "2.1 Ordenar ASC o array de entrada - Sem validação de campos")
    @PostMapping(value = "/bubble-ordenar-asc", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Integer> ordernarBubbleAsc(@RequestBody ArrayList<Integer> entry) {
        return bubbleSortService.ordernarArrayIntAsc(entry);
    }

    @ApiOperation(value = "2.2 Ordenar DESC o array de entrada - Sem validação de campos")
    @PostMapping(value = "/bubble-ordenar-desc", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Integer> ordernarBubbleDesc(@RequestBody ArrayList<Integer> entry) {
        return bubbleSortService.ordernarArrayIntDesc(entry);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="3 - Fatorial">
    @Autowired
    private FatorialService fatorialService;

    @ApiOperation(value = "3 - Calcular fatorial de um int - 40+ Quebra tamanho LONG")
    @PostMapping(value = "/fatorial", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long calcularFatorial(@RequestBody Integer entry) {
        return fatorialService.executarFatorial(entry);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="4 - Múltiplos de 3 e 5">
    @Autowired
    private SomaMultiplosService somaMultiplosService;

    @ApiOperation(value = "4 - Somar múltiplos de 3 e 5 até o valor de entrada")
    @PostMapping(value = "/somar-multiplos-3-5", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long somarMultiplos(@RequestBody Integer entry) {
        return somaMultiplosService.somarMultiplos(entry);
    }
    // </editor-fold>
}
