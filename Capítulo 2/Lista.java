class ListaInt{
    int elemento;
    ListaInt siguiente;
    public static final ListaInt nil = null;
    
    public static int primero(ListaInt l){
        return l.elemento;
    }

    public static ListaInt cons(int nuevoElemento, ListaInt listaVieja){
        ListaInt nuevaL = new ListaInt();
        nuevaL.elemento = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaInt resto(ListaInt listaVieja){
        ListaInt nuevaL = new ListaInt();
        if(listaVieja.siguiente != ListaInt.nil){
            nuevaL.elemento = ListaInt.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = ListaInt.nil;
        }
        return nuevaL;
    }

    public static ListaInt insertar1(int nuevoElemento, ListaInt listaVieja){
        ListaInt listaNueva;
        if(listaVieja == ListaInt.nil)
            listaNueva = ListaInt.cons(nuevoElemento, listaVieja);
        else {
            int antiguoPrimero = ListaInt.primero(listaVieja);
            if(nuevoElemento == antiguoPrimero)
                listaNueva = ListaInt.cons(nuevoElemento, listaVieja);
            else {
                ListaInt antiguoResto = ListaInt.resto(listaVieja);
                ListaInt nuevoResto = insertar1(nuevoElemento, antiguoResto);
                listaNueva = ListaInt.cons(antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

    public static void imprimirLista(ListaInt lista){
        while(lista != ListaInt.nil){
            System.out.println(ListaInt.primero(lista));
            lista = ListaInt.resto(lista);
        }
    }
}

public class Lista {
    public static void main(String[] args){
        ListaInt lista = new ListaInt();
        lista = ListaInt.insertar1(1, ListaInt.nil);
        lista = ListaInt.insertar1(2, lista);
        ListaInt.imprimirLista(lista);
    }
}