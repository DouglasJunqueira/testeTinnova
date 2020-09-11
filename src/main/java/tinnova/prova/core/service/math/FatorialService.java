package tinnova.prova.core.service.math;

import org.springframework.stereotype.Service;

@Service
public class FatorialService {

    public long executarFatorial(int value) {
        return value <= 1 ? 1 : value * executarFatorial(value - 1);
    }
}
