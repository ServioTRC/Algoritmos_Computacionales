class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class BubbleSort{

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
        bubbleSort(E, E.length);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+BubbleSort.comparaciones);
    }

    public static void bubbleSort(Elemento[] E, int n){
        int numPares;
        boolean huboCambio;
        Elemento temp;
        int j;
        numPares = n-1;
        huboCambio = true;
        while(huboCambio){
            huboCambio = false;
            for(j = 0; j < numPares; j++){
                BubbleSort.comparaciones++;
                if(E[j].clave.valor > E[j+1].clave.valor){
                    temp = E[j];
                    E[j] = E[j+1];
                    E[j+1] = temp;
                    huboCambio = true;
                }
            }
            numPares--;
        }
        return;
    }
}