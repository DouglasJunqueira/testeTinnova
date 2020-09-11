package tinnova.prova.core.service.math;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BubbleSortService {

    public ArrayList<Integer> ordernarArrayIntAsc(ArrayList<Integer> listaInteiros) {
        int sizeCheck = listaInteiros.size() -1;

        for (int i = 0; i < sizeCheck; i++) {
            for (int j = 0; j < sizeCheck - i; j++) {
                if (listaInteiros.get(j) > listaInteiros.get(j+1)) {
                    swapValores(j, listaInteiros);
                }
            }
        }
        return listaInteiros;
    }

    public ArrayList<Integer> ordernarArrayIntDesc(ArrayList<Integer> listaInteiros) {
        int sizeCheck = listaInteiros.size() -1;

        for (int i = 0; i < sizeCheck; i++) {
            for (int j = 0; j < sizeCheck - i; j++) {
                if (listaInteiros.get(j) < listaInteiros.get(j+1)) {
                    swapValores(j, listaInteiros);
                }
            }
        }
        return listaInteiros;
    }

    private void swapValores(int j, ArrayList<Integer> listaInteiros) {
        int temp = listaInteiros.get(j);
        listaInteiros.set(j, listaInteiros.get(j+1));
        listaInteiros.set(j+1, temp);
    }
}
