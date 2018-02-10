class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

public class Selecciones{
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
        Elemento el6 = new Elemento();
        el6.clave = new Clave();
        el6.clave.valor = 1117;
        Elemento[] E = {el2, el3, el2, el4, el3, el5, el1, el2, el5, el4, el3, el6, el5, el2};
        Elemento[] arreglo = hallarMaxEnMonton(E, E.length);
        System.out.println("Elemento mayor: "+arreglo[1].clave.valor);
        arreglo = hallarMaxYSegundo(E, E.length);
        System.out.println("Elemento mayor: "+arreglo[0].clave.valor+"\nSegundo mayor: "+arreglo[1].clave.valor);
    }

    public static Elemento max(Elemento e1, Elemento e2){
        if(e1.clave.valor >= e2.clave.valor)
            return e1;
        else
            return e2;
    }

    public static Elemento[] hallarMaxEnMonton(Elemento[] E, int n){
        Elemento[] E2 = new Elemento[n*2];
        int ultimo, i, temp;
        for(i = 0, temp = 0; i < n*2; i++, temp++){
            if(temp >= n)
                temp = 0;
            E2[i] = E[temp];
        }
        for(ultimo = 2*n-2; ultimo >= 2; ultimo -= 2){
            E2[ultimo/2] = max(E2[ultimo], E2[ultimo+1]);
        }
        return E2;
    }

    public static Elemento[] hallarMaxYSegundo(Elemento[] E, int n){
        Elemento[] res = new Elemento[2];
        Elemento max, segundo;
        if(E[0].clave.valor > E[1].clave.valor){
            max = E[0];
            segundo = E[1];
        } else {
            max = E[1];
            segundo = E[0];
        }
        for(int i = 2; i < n; i++){
            if(E[i].clave.valor > segundo.clave.valor){
                if(E[i].clave.valor > max.clave.valor){
                    segundo = max;
                    max = E[i];
                } else {
                    segundo = E[i];
                }
            }
        }
        res[0] = max;
        res[1] = segundo;
        return res;
    }
}