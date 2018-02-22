class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

class Lista{
    Elemento elemento;
    Lista siguiente;
    public static final Lista nil = null;
    
    public static Elemento primero(Lista l){
        return l.elemento;
    }

    public static Lista cons(Elemento nuevoElemento, Lista listaVieja){
        Lista nuevaL = new Lista();
        nuevaL.elemento = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static Lista resto(Lista listaVieja){
        Lista nuevaL = new Lista();
        if(listaVieja.siguiente != Lista.nil){
            nuevaL.elemento = Lista.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = Lista.nil;
        }
        return nuevaL;
    }

    public static Lista insertar1(Elemento nuevoElemento, Lista listaVieja){
        Lista listaNueva;
        if(listaVieja == Lista.nil)
            listaNueva = Lista.cons(nuevoElemento, listaVieja);
        else {
            Elemento antiguoPrimero = Lista.primero(listaVieja);
            if(nuevoElemento.clave.valor == antiguoPrimero.clave.valor)
                listaNueva = Lista.cons(nuevoElemento, listaVieja);
            else {
                Lista antiguoResto = Lista.resto(listaVieja);
                Lista nuevoResto = insertar1(nuevoElemento, antiguoResto);
                listaNueva = Lista.cons(antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

    public static void imprimirLista(Lista lista){
        while(lista != Lista.nil){
            System.out.println(Lista.primero(lista).clave.valor);
            lista = Lista.resto(lista);
        }
    }
}

public class OrdenBase{
    public static int comparaciones = 0;

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
        Lista lis = Lista.insertar1(el1, Lista.nil);
        lis = Lista.insertar1(el2, lis);
        lis = Lista.insertar1(el3, lis);
        lis = Lista.insertar1(el4, lis);
        lis = Lista.insertar1(el5, lis);
        lis = ordenBase(lis, 10, 4);
        Lista.imprimirLista(lis);
        System.out.println("Numero de elementos: 4");
        System.out.println("Comparaciones realizadas: "+OrdenBase.comparaciones);
    }

    public static Lista ordenBase(Lista L, int base, int numCampos){
        Lista[] cubetas = new Lista[base];
        int campo;
        Lista nuevaL;
        nuevaL = L;
        for(campo = 0; campo < numCampos; campo++){
            cubetas = distribuir(nuevaL, cubetas, base, campo);
            nuevaL = combinar(cubetas, base);
        }
        return nuevaL;
    }

    public static Lista[] distribuir(Lista L, Lista[] cubetas, int base, int campo){
        Lista listaRest;
        Lista[] cubetasRes = new Lista[base];
        listaRest = L;
        while(listaRest != Lista.nil){
            Elemento K = Lista.primero(listaRest);
            int b = desplaMascara(campo, base, K.clave);
            OrdenBase.comparaciones++;
            cubetasRes[b] = Lista.cons(K, cubetasRes[b]);
            listaRest = Lista.resto(listaRest);
        }
        return cubetasRes;
    }

    public static int desplaMascara(int campo, int base, Clave k){
        int valor = k.valor;
        if(campo != 0)
            valor /= (campo*base);
        return (valor % base);
    }

    public static Lista combinar(Lista[] cubetas, int base){
        int b;
        Lista L, cubetaRest;
        L = Lista.nil;
        for(b = base-1; b >= 0; b--){
            cubetaRest = cubetas[b];
            while(cubetaRest != Lista.nil){
                Elemento k = Lista.primero(cubetaRest);
                L = Lista.cons(k, L);
                cubetaRest = Lista.resto(cubetaRest);
            }
        }
        return L;
    }
}