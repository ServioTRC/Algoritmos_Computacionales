class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class MergeSort{

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
        Elemento[] arreglo = {el2, el3, el2, el4, el3, el5, el1, el2, el5, el4, el3, el1, el5, el2};
        E = arreglo;
        mergeSort(E, 0, E.length-1);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+MergeSort.comparaciones);
    }

    public static void mergeSort(Elemento[] E, int primero, int ultimo){
        if(primero < ultimo){
            int medio = primero + (ultimo - primero)/2;
            mergeSort(E, primero, medio);
            mergeSort(E, medio+1, ultimo);
            fusionarConMerge(E, primero, medio, ultimo);
        }
    }

    public static void fusionarConMerge(Elemento[] E, int primero, int medio, int ultimo){
        int i, j, k;
        Elemento[] temp = new Elemento[E.length];
        for(i = primero; i <= ultimo; i++){
            temp[i] = E[i];
        }
        i = primero;
        j = medio + 1;
        k = primero;
        while(i <= medio && j <= ultimo){
            MergeSort.comparaciones++;
            if(temp[i].clave.valor <= temp[j].clave.valor){
                E[k] = temp[i];
                i++;
            } else {
                E[k] = temp[j];
                j++;
            }
            k++;
        }
        while(i <= medio){
            E[k] = temp[i];
            k++;
            i++;
        }    
    }

    public static void fusionar(Elemento[] A, int k, Elemento[] B, int m, Elemento[] C){
        int n = k + m;
        int indiceA = 0, indiceB = 0, indiceC = 0;
        while(indiceA < k && indiceB < m){
            if(A[indiceA].clave.valor <= B[indiceB].clave.valor){
                C[indiceC] = A[indiceA];
                indiceA++;
                indiceC++;
            } else {
                C[indiceC] = B[indiceB];
                indiceB++;
                indiceC++;
            }
        }
        if(indiceA >= k){
            for(int i = indiceB, j = indiceC; i < m; i++, j++){
                C[j] = B[i];
            }
        } else {
            for(int i = indiceA, j = indiceC; i < k; i++, j++){
                C[j] = A[i];
            }
        }
    }

}