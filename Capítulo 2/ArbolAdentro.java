class NodoArbolAdentro{
    int datos;
    boolean raiz;
    NodoArbolAdentro padre;
    public static final NodoArbolAdentro nil = null;

    public static NodoArbolAdentro crearNodo(int d){
        NodoArbolAdentro nodo = new NodoArbolAdentro();
        nodo.datos = d;
        nodo.raiz = true;
        nodo.padre = NodoArbolAdentro.nil;
        return nodo;
    }

    public static boolean esRaiz(NodoArbolAdentro nodo){
        return nodo.raiz;
    }

    public static NodoArbolAdentro padre(NodoArbolAdentro nodo){
        return nodo.padre;
    }

    public static int datosNodo(NodoArbolAdentro nodo){
        return nodo.datos;
    }

    public static void hacerPadre(NodoArbolAdentro hijo, NodoArbolAdentro padre){
        hijo.padre = padre;
        hijo.raiz = false;
    }

    public static void ponerDatosNodo(NodoArbolAdentro nodo, int d){
        nodo.datos = d;
    }

    public static void imprimirArbol(NodoArbolAdentro nodo){
        System.out.println(NodoArbolAdentro.datosNodo(nodo));
        if(NodoArbolAdentro.esRaiz(nodo)){
            return;
        }
        imprimirArbol(NodoArbolAdentro.padre(nodo));
    }
}

public class ArbolAdentro{
    public static void main(String[] main){
        NodoArbolAdentro nodo1 = NodoArbolAdentro.crearNodo(3);
        NodoArbolAdentro nodo2 = NodoArbolAdentro.crearNodo(4);
        NodoArbolAdentro nodo3 = NodoArbolAdentro.crearNodo(5);
        NodoArbolAdentro.hacerPadre(nodo2, nodo1);
        NodoArbolAdentro.hacerPadre(nodo3, nodo2);
        NodoArbolAdentro.imprimirArbol(nodo3);
    }
}