class ListaArboles{
    Object elemento;
    ListaArboles siguiente;
    public static final ListaArboles nil = null;
    
    public static Arbol primero(ListaArboles l){
        return Arbol.construirArbol(l.elemento, Arbol.nil, ListaArboles.nil);
    }

    public static ListaArboles cons(int nuevoElemento, ListaArboles listaVieja){
        ListaArboles nuevaL = new ListaArboles();
        nuevaL.elemento = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaArboles resto(ListaArboles listaVieja){
        ListaArboles nuevaL = new ListaArboles();
        if(listaVieja.siguiente != ListaArboles.nil){
            nuevaL.elemento = ListaArboles.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = ListaArboles.nil;
        }
        return nuevaL;
    }

    public static void imprimirLista(ListaArboles lista){
        while(lista != ListaArboles.nil){
            System.out.println(ListaArboles.primero(lista));
            lista = ListaArboles.resto(lista);
        }
    }
}

class Arbol{
    Object raiz;
    Arbol hijo;
    ListaArboles subarboles;
    public static final Arbol nil = null;

    public static Arbol construirArbol(Object raiz, Arbol hijo, ListaArboles viejosArboles){
        Arbol ar = new Arbol();
        ar.raiz = raiz;
        ar.hijo = hijo;
        ar.subarboles = viejosArboles;
        return ar;
    }

    public static Object raiz(Arbol a){
        return a.raiz;
    }

    public static ListaArboles subaArboles(Arbol a){
        return a.subarboles;
    }

    public static void recorrerPreOrden(Arbol T){
        ListaArboles quedanSubArboles;
        System.out.println(Arbol.raiz(T));
        quedanSubArboles = Arbol.subaArboles(T);
        while(quedanSubArboles != ListaArboles.nil){
            Arbol subArbol = ListaArboles.primero(quedanSubArboles);
            recorrerPreOrden(subArbol);
            quedanSubArboles = ListaArboles.resto(quedanSubArboles);
        }
        recorrerPreOrden(T.hijo);
        return;
    }

    public static void recorrerInOrden(Arbol T){
        ListaArboles quedanSubArboles;
        quedanSubArboles = Arbol.subaArboles(T);
        while(quedanSubArboles != ListaArboles.nil){
            Arbol subArbol = ListaArboles.primero(quedanSubArboles);
            recorrerInOrden(subArbol);
            System.out.println(Arbol.raiz(T));
            quedanSubArboles = ListaArboles.resto(quedanSubArboles);
        }
        return;
    }

    public static void recorrerPosOrden(Arbol T){
        ListaArboles quedanSubArboles;
        quedanSubArboles = Arbol.subaArboles(T);
        while(quedanSubArboles != ListaArboles.nil){
            Arbol subArbol = ListaArboles.primero(quedanSubArboles);
            recorrerPosOrden(subArbol);
            quedanSubArboles = ListaArboles.resto(quedanSubArboles);
        }
        System.out.println(Arbol.raiz(T));
        return;
    }

}

public class ArbolGeneral{
    public static void main(String[] args){
        ListaArboles lista1 = ListaArboles.cons(1, ListaArboles.nil);
        ListaArboles lista2 = ListaArboles.cons(2, lista1);
        Arbol arbol1 = Arbol.construirArbol(3, Arbol.nil,lista2);
        Arbol.recorrerInOrden(arbol1);
        Arbol.recorrerPreOrden(arbol1);
        Arbol.recorrerPosOrden(arbol1);
    }
}