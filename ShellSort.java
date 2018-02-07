class Elemento {
    Clave clave;
}

class Clave implements Comparable<Integer>{
    int valor;
}

public class ShellSort{
    public static void main(String[] args){

    }

    public static void shellSort(Elemento[] E, int n, int[] h, int t){
        int indicex, s;
        for(s=t; s >= 1; s--){
            for(indicex = h[s]; indicex < n; indicex++){
                Elemento actual = E[indicex];
                Clave x = actual.clave;
                int posX = desplaVacH(E, h[s], indicex, x);
                E[posX] = actual;
            }
        }
        return;
    }

    public static int desplaVacH(Elemento[] E, int h, int indicex, Clave x){
        int vacante, posX;
        vacante = indicex;
        posX = 0;
        while(vacante >= h){
            if(E[vacante-h].clave.valor <= x.valor){
                posX = vacante;
                break;
            }
            E[vacante] = E[vacante-h];
            vacante -= h;
        }
        return posX;
    }
}