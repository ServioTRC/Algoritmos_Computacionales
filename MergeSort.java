class Elemento {
    Clave clave;
}

class Clave implements Comparable<Integer>{
    int valor;
}

public class MergeSort{
    public static void main(String[] args){

    }

    public static void mergeSort(Elemento[] E, int primero, int ultimo){
        if(primero < ultimo){
            int medio = (primero+ultimo)/2;
            mergeSort(E, primero, medio);
            mergeSort(E, medio+1, ultimo);
            fusionarConMerge(E, primero, medio, ultimo);
        }
    }

    public static void fusionarConMerge(Elemento[] E, int primero, int medio, int ultimo){
        int i, finalPrimero, num_elements, tmp_pos;
        Elemento[] temp = Elemento[E.length];
        finalPrimero = medio - 1;
        tmp_pos = primero;
        num_elements = ultimo - primero + 1;
        while ((primero <= finalPrimero) && (medio <= ultimo)) {
            if (E[primero].clave.valor <= E[medio].clave.valor) {
                temp[tmp_pos] = E[primero];
                tmp_pos++;
                primero++;
            } else {
                temp[tmp_pos] = E[medio];
                tmp_pos++;
                medio++;
            }
        }

        while (primero <= finalPrimero) {
            temp[tmp_pos] = E[primero];
            primero++;
            tmp_pos++;
        }
        
        while (medio <= ultimo) {
            temp[tmp_pos] = E[medio];
            medio++;
            tmp_pos++;
        }

        for (i = 0; i <= num_elements; i++, ultimo--) {
            E[ultimo] = temp[ultimo];
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
            for(i = indiceB, j = indiceC; i < m; i++, j++){
                C[indiceC] = B[indiceB];
            }
        } else {
            for(i = indiceA, j = indiceC; i < m; i++, j++){
                C[indiceC] = B[indiceA];
            }
        }
    }

}