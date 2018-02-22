class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class QuickSort{

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
        Elemento[] arreglo = {el3, el1, el5, el2, el4, el3, el1, el2, el5, el4, el3, el1, el5, el2, el4};
        System.out.println("QuickSort normal");
        E = arreglo;
        quickSort(E, 0, E.length-1);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+QuickSort.comparaciones);

        System.out.println("\nQuickSort pequeno");
        QuickSort.comparaciones = 0;
        E = arreglo;
        quickSortPequeno(E, 0, E.length-1, 7);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+QuickSort.comparaciones);

        System.out.println("\nQuickSort ORT");
        QuickSort.comparaciones = 0;
        E = arreglo;
        quickSortORT(E, 0, E.length-1);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+QuickSort.comparaciones);
    }

    public static void quickSort(Elemento[] E, int primero, int ultimo){
        if(primero < ultimo){
            Elemento elementoPivote = E[primero];
            Clave pivote = elementoPivote.clave;
            int puntoPartir = partir(E, pivote, primero, ultimo);
            E[puntoPartir] = elementoPivote;
            quickSort(E, primero, puntoPartir-1);
            quickSort(E, puntoPartir+1, ultimo);
        }
        return;
    }

    public static int partir(Elemento[] E, Clave pivote, int primero, int ultimo){
        int bajo, alto;
        bajo = primero;
        alto = ultimo;
        while(bajo < alto){
            int vacAlta = extenderRegionGrande(E, pivote, bajo, alto);
            int vacBaja = extenderRegionChica(E, pivote, bajo+1, vacAlta);
            bajo = vacBaja;
            alto = vacAlta - 1;
        }
        return bajo;
    }

    public static int extenderRegionGrande(Elemento[] E, Clave pivote, int vacBaja, int alto){
        int vacAlta, actual;
        vacAlta = vacBaja;
        actual = alto;
        while(actual > vacBaja){
            QuickSort.comparaciones++;
            if(E[actual].clave.valor < pivote.valor){
                E[vacBaja] = E[actual];
                vacAlta = actual;
                break;
            }
            actual--;
        }
        return vacAlta;
    }

    public static int extenderRegionChica(Elemento[] E, Clave pivote, int bajo, int vacAlta){
        int vacBaja, actual;
        vacBaja = vacAlta;
        actual = bajo;
        while(actual < vacBaja){
            QuickSort.comparaciones++;
            if(E[actual].clave.valor >= pivote.valor){
                E[vacAlta] = E[actual];
                vacBaja = actual;
                break;
            }
            actual++;
        }
        return vacBaja;
    }

    public static void quickSortPequeno(Elemento[] E, int primero, int ultimo, int pequeno){
        if(ultimo - primero < pequeno){
            Elemento elementoPivote = E[primero];
            Clave pivote = elementoPivote.clave;
            int puntoPartir = partir(E, pivote, primero, ultimo);
            E[puntoPartir] = elementoPivote;
            quickSort(E, primero, puntoPartir-1);
            quickSort(E, puntoPartir+1, ultimo);
        } else {
            smallSort(E, primero, ultimo);
        }
        return;
    }

    //Ordenamiento por inserción
    public static void smallSort(Elemento[] E, int primero, int ultimo){
        int indicex;
        for(indicex = primero; indicex <= ultimo; indicex++){
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
            QuickSort.comparaciones++;
            if(E[vacante-1].clave.valor <= x.valor){
                posX = vacante;
                break;
            }
            E[vacante] = E[vacante-1];
            vacante--;
        }
        return posX;
    }

    public static void quickSortORT(Elemento[] E, int primero, int ultimo){
        int primero1, ultimo1, primero2, ultimo2;
        primero2 = primero;
        ultimo2 = ultimo;
        while(ultimo2-primero2 > 2){
            Elemento elementoPivote = E[primero];
            Clave pivote = elementoPivote.clave;
            int puntoPartir = partir(E, pivote, primero2, ultimo2);
            E[puntoPartir] = elementoPivote;
            if(puntoPartir < (primero2 + ultimo2) / 2){
                primero1 = primero2;
                ultimo1 = puntoPartir - 1;
                primero2 = puntoPartir + 1;
                ultimo2 = ultimo1;
            } else {
                primero1 = puntoPartir+1;
                ultimo1 = ultimo2;
                primero2 = primero1;
                ultimo2 = puntoPartir -1;
            }
            quickSortORT(E, primero1, ultimo1);
        }
        return;
    }

}