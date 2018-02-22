class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class ShellSort{

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
        E = arreglo;
        int[] saltos = {6,5,4,3,2,1};
        shellSort(E, E.length, saltos, saltos.length-1);
        for(Elemento el: E){
            System.out.print(el.clave.valor+" ");
        }
        System.out.println("\nNumero de elementos: "+E.length);
        System.out.println("Comparaciones realizadas: "+ShellSort.comparaciones);
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
            ShellSort.comparaciones++;
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