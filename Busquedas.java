
public class Busquedas{
    public static void main(String[] args) {
        

    }

    public static int busquedaSecuencial(int[] arreglo, int elemento){
        int res = -1;
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i] == elemento){
                res = i;
                break;
            }
        }
        return res;
    }

    public static int busquedaSecuencialRecursiva(int[] arreglo, int m, int num, int k){
        int respuesta;
        if(m >= num)
            respuesta = -1;
        else if (arreglo[m] == k)
            respuesta = m;
        else
            respuesta = busquedaSecuencialRecursiva(arreglo, m+1, num, k);
        return respuesta;
    }

    public static void busquedaBinaria(int[] arreglo, int elemento){
        System.out.println(busquedaBinariaRecursiva(arreglo, 0, arreglo.length-1, elemento));
        System.out.println(busquedaBinariaIterativa(arreglo, 0, arreglo.length-1, elemento));
    }

    public static int busquedaBinariaRecursiva(int[] arreglo, int primero, int ultimo, int elemento){
        int indice;
        if(ultimo < primero)
            indice = -1;
        else{
            int medio = (primero + ultimo) / 2;
            if(elemento == arreglo[medio])
                indice = medio;
            else if (elemento < arreglo[medio])
                indice = busquedaBinariaRecursiva(arreglo, primero, medio-1, elemento);
            else
                indice = busquedaBinariaRecursiva(arreglo, medio+1, ultimo, elemento);               
        }
        return indice;            
    }

    public static int busquedaBinariaIterativa(int[] arreglo, int primero, int ultimo, int elemento){
        int indice = -1, medio;
        while(ultimo >= primero){
            medio = (primero + ultimo) / 2;
            if(elemento == arreglo[medio]){
                indice = medio;
                break;
            } else if (elemento < arreglo[medio]){
                ultimo = medio-1;
            }
            else {
                primero = medio +1;
            }
        }
        return indice;
    }

}