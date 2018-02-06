class Elemento {
    Clave clave;
}

class Clave implements Comparable<Integer>{
    int valor;
}

public class Insercion {
    public static void main(String[] args){
        
    }

    public static void ordenInsercion(Elemento[] E, int n){
        int indicex;
        for(indicex = 0; indicex < n; indicex++){
            Elemento actual = E[indicex];
            Clave x = actual.clave;
            int posX = desplaVac(E, indiceX, x);
            E[posX] = actual;
        }
        return;
    }

    public static int desplaVac(Elemento[] E, int indicex, Clave x){
        int vacante, posX;
        vacante = indiceX;
        posX = 0;
        while(vacante > 0){
            if(E[vacante-1].clave.valor <= x.valor){
                posX = vacante;
                break;
            }
            E[vacante] = E[vacante-1];
            vacante--;
        }
        return posX;
    }

    public static int desplaVacRec(Elemento[] E, int vacante, Clave x){
        int posX;
        if(vacante == 0)
            posX = vacante;
        else if(E[vacante-1].clave.valor <= x.valor)
            posX = vacante;
        else{
            E[vacante] = E[vacante-1];
            posX = desplaVacRec(E, vacante-1, x);
        }
        return posX;
    }
}