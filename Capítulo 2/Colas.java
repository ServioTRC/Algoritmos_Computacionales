class ListaObj{
    Object elemento;
    ListaObj siguiente;
    public static final ListaObj nil = null;
    
    public static Object primero(ListaObj l){
        return l.elemento;
    }

    public static ListaObj cons(Object nuevoElemento, ListaObj listaVieja){
        ListaObj nuevaL = new ListaObj();
        nuevaL.elemento = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaObj resto(ListaObj listaVieja){
        ListaObj nuevaL = new ListaObj();
        if(listaVieja.siguiente != ListaObj.nil){
            nuevaL.elemento = ListaObj.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = ListaObj.nil;
        }
        return nuevaL;
    }

    public static ListaObj insertar1(Object nuevoElemento, ListaObj listaVieja){
        ListaObj listaNueva;
        if(listaVieja == ListaObj.nil)
            listaNueva = ListaObj.cons(nuevoElemento, listaVieja);
        else {
            Object antiguoPrimero = ListaObj.primero(listaVieja);
            if(nuevoElemento == antiguoPrimero)
                listaNueva = ListaObj.cons(nuevoElemento, listaVieja);
            else {
                ListaObj antiguoResto = ListaObj.resto(listaVieja);
                ListaObj nuevoResto = insertar1(nuevoElemento, antiguoResto);
                listaNueva = ListaObj.cons(antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

}

class Cola{

    ListaObj lista;
    boolean vacia;
    public static final Cola nil = null;

    public static Cola crear(){
        Cola cola = new Cola();
        cola.lista = null;
        cola.vacia = true;
        return cola;
    }

    public static boolean estaVacia(Cola q){
        return q.vacia;
    }

    public static Object frente(Cola q){
        if(!Cola.estaVacia(q)){
            return ListaObj.primero(q.lista);
        }
        else
            return Cola.nil;
    }

    public static void encolar(Cola q, Object e){
        if(Cola.estaVacia(q))
            q.vacia = false;
        q.lista = ListaObj.insertar1(e, q.lista); 
    }

    public static void desencolar(Cola q){
        if(!Cola.estaVacia(q)){
            q.lista = ListaObj.resto(q.lista);
        }
    }
}

public class Colas {
    public static void main(String[] args){
        Cola cola = Cola.crear();
        Cola.encolar(cola, 3);
        Cola.encolar(cola, 2);
        Cola.encolar(cola, 4);
        Cola.desencolar(cola);
        System.out.println(Cola.frente(cola));
    }
}