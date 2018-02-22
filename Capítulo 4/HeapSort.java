class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class HeapSort{

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
        Elemento[] arreglo = {null, el3, el1, el5, el2, el4, el3, el1, el2, el5, el4, el3, el1, el5, el2, el4};
        System.out.println("HeapSort");
        E = arreglo;
        heapSort(E, E.length-1);
        for(int i = 1; i < E.length; i++){
            System.out.print(E[i].clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+HeapSort.comparaciones);

        System.out.println("\nHeapSort Acelerado");
        HeapSort.comparaciones = 0;
        E = arreglo;
        heapSort(E, E.length-1);
        for(int i = 1; i < E.length; i++){
            System.out.print(E[i].clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+HeapSort.comparaciones);
    }

    public static void heapSort(Elemento[] E, int n){
        int tamano;
        construirMonton(E, n);
        for(tamano = n; tamano >= 2; tamano--){
            Elemento maxActual = E[1];
            Elemento k = E[tamano];
            repararMonton(E, tamano-1, 1, k);
            E[tamano] = maxActual;
        }
        return;
    }

    public static void construirMonton(Elemento[] E, int n){
        for(int i = n/2-1; i > 0; i--){
            Elemento k = E[i];
            repararMonton(E, n, i, k);
        }
    }

    public static void repararMonton(Elemento[] E, int tamano, int raiz, Elemento k){
        int izq = 2*raiz, der = 2*raiz+1;
        if(izq > tamano)
            E[raiz] = k;
        else {
            HeapSort.comparaciones += 2;
            int subMontonMayor;
            if(izq == tamano) {
                subMontonMayor = izq;
            } else if(E[izq].clave.valor > E[der].clave.valor) {
                subMontonMayor = izq;
            } else{
                subMontonMayor = der;
            }
            if(k.clave.valor >= E[subMontonMayor].clave.valor){
                E[raiz] = k;
            } else{
                E[raiz] = E[subMontonMayor];
                repararMonton(E, tamano, subMontonMayor, k);
            }   
        }
        return;
    }

    public static void subirMonton(Elemento[] E, int raiz, Elemento k, int vacante){
        if(vacante == raiz){
            E[vacante] = k;
        } else {
            int padre = vacante / 2;
            HeapSort.comparaciones++;
            if(k.clave.valor <= E[padre].clave.valor){
                E[vacante] = k;
            } else {
                E[vacante] = E[padre];
                subirMonton(E, raiz, k, vacante);
            }
        }
        return;
    }

    public static void heapSortAcel(Elemento[] E, int n){
        int tamano;
        construirMonton(E, n);
        for(tamano = n; tamano >= 2; tamano--){
            Elemento maxActual = E[1];
            Elemento k = E[tamano];
            repararMontonRapido(E, n, k, tamano-1, n/2);
            E[tamano] = maxActual;
        }
        return;
    }

    public static void repararMontonRapido(Elemento[] E, int n, Elemento k, int vacante, int h){
        if(h <= 1)
            return;
        else {
            int altParar = h/2;
            int vacParar = promover(E, altParar, vacante, h);
            int padreVac = vacParar / 2;
            HeapSort.comparaciones++;
            if(E[padreVac].clave.valor <= k.clave.valor){
                E[vacParar] = E[padreVac];
                subirMonton(E, vacante, k, padreVac);
            } else 
                repararMontonRapido(E, n, k, vacParar, altParar);
        }
    }

    public static int promover(Elemento[] E, int altParar, int vacante, int h){
        int vacParar;
        HeapSort.comparaciones++;
        if(h <= altParar){
            vacParar = vacante;
        } else if(E[2*vacante].clave.valor <= E[2*vacante+1].clave.valor){
            E[vacante] = E[2*vacante+1];
            vacParar = promover(E, altParar, 2*vacante+1, h-1);
        } else {
            E[vacante] = E[2*vacante];
            vacParar = promover(E, altParar, 2*vacante, h-1);           
        }
        return vacParar;
    }
}