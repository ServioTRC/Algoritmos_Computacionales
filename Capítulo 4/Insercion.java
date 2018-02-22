class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class Insercion {
    public static int comparaciones = 0;
    private static Elemento E[];

    public static void main(String[] args){
        Elemento el1 = new Elemento();
        el1.clave = new Clave();
        el1.clave.valor = 7;
        Elemento el2 = new Elemento();
        el2.clave = new Clave();
        el2.clave.valor = 11;
        Elemento el3 = new Elemento();
        el3.clave = new Clave();
        el3.clave.valor = 28;
        Elemento el4 = new Elemento();
        el4.clave = new Clave();
        el4.clave.valor = 102;
        Elemento el5 = new Elemento();
        el5.clave = new Clave();
        el5.clave.valor = 1003;
        Elemento[] arreglo = {el3, el1, el5, el2, el4};
        E = arreglo;
        ordenInsercion(E, E.length);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+Insercion.comparaciones);
    }

    public static void ordenInsercion(Elemento[] E, int n){
        int indicex;
        for(indicex = 0; indicex < n; indicex++){
            Elemento actual = E[indicex];
            Clave x = actual.clave;
            int posX = desplaVac(E, indicex, x);
            E[posX] = actual;
        }
        return;
    }

    public static int desplaVac(Elemento[] E, int indicex, Clave x){
        int vacante, posX;
        vacante = indicex;
        posX = 0;
        while(vacante > 0){
            Insercion.comparaciones++;
            if(E[vacante-1].clave.valor <= x.valor){
                posX = vacante;
                break;
            }
            E[vacante] = E[vacante-1];
            vacante--;
        }
        return posX;
    }

    public static Elemento[] ordenInsercionRec(Elemento[] E, int n){
        int indicex;
        for(indicex = 0; indicex < n; indicex++){
            Elemento actual = E[indicex];
            Clave x = actual.clave;
            int posX = desplaVacRec(E, indicex, x);
            E[posX] = actual;
        }
        return E;
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