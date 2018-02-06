class Elemento {
    Clave clave;
}

class Clave implements Comparable<Integer>{
    int valor;
}

public class QuickSort{

    public static void main(String[] args){

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
            int vacBaja = extenerRegionChica(E, pivote, bajo+1, vacAlta);
            bajo = vacAlta;
            alto = vacAlta - 1;
        }
        return bajo;
    }

    public static int extenderRegionGrande(Elemento[] E, Clave pivote, int vacBaja, int alto){
        int vacAlta, actual;
        vacAlta = vacBaja;
        actual = alto;
        while(actual > vacBaja){
            if(E[actual].clave.valor < pivote.valor){
                E[vacBaja] = E[actual];
                vacAlta = actual;
                break;
            }
            actual--;
        }
        return vacAlta;
    }

    public static int extenderRegionChica(Elemento[] E, Clave pivote, int vacBaja, int alto){
        int vacAlta, actual;
        vacAlta = vacBaja;
        actual = bajo;
        while(actual < vacBaja){
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

    public static void quickSortORT(Elemento[] E, int primero, int ultimo){
        int primero1, ultimo1, primero2, ultimo2;
        primero2 = primero;
        ultimo2 = ultimo;
        while(ultimo2-primer2 > 2){
            Elemento elementoPivote = E[primero];
            Clave = elementoPivote.clave;
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