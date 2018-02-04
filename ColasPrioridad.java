class ListaCol{
    int elemento;
    float prioridad;
    ListaCol siguiente;
    public static final ListaCol nil = null;
    
    public static int primero(ListaCol l){
        return l.elemento;
    }

    public static float primeroPrioridad(ListaCol l){
        return l.prioridad;
    }

    public static ListaCol cons(int nuevoElemento, float prioridad, ListaCol listaVieja){
        ListaCol nuevaL = new ListaCol();
        nuevaL.elemento = nuevoElemento;
        nuevaL.prioridad = prioridad;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaCol resto(ListaCol listaVieja){
        ListaCol nuevaL;
        if(listaVieja.siguiente != ListaCol.nil){
            nuevaL = cons(ListaCol.primero(listaVieja.siguiente), 
                ListaCol.primeroPrioridad(listaVieja.siguiente), listaVieja.siguiente.siguiente);
        } else {
            nuevaL = ListaCol.nil;
        }
        return nuevaL;
    }

    public static ListaCol insertar1(int nuevoElemento, float prioridad, ListaCol listaVieja){
        ListaCol listaNueva;
        if(listaVieja == ListaCol.nil)
            listaNueva = ListaCol.cons(nuevoElemento, prioridad, listaVieja);
        else {
            float antiguoPrimero = ListaCol.primeroPrioridad(listaVieja);
            if(prioridad < antiguoPrimero)
                listaNueva = ListaCol.cons(nuevoElemento, prioridad, listaVieja);
            else {
                ListaCol antiguoResto = ListaCol.resto(listaVieja);
                ListaCol nuevoResto = insertar1(nuevoElemento, prioridad, antiguoResto);
                listaNueva = ListaCol.cons(ListaCol.primero(listaVieja), antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

    public static boolean elementoEnLista(ListaCol lista, int id){
        boolean enLista = false;
        while(lista != ListaCol.nil){
            if(id == ListaCol.primero(lista)){
                enLista = true;
                break;
            }
            lista = ListaCol.resto(lista);            
        }
        return enLista;
    }

    public static void imprimirLista(ListaCol lista){
        while(lista != ListaCol.nil){
            System.out.println(ListaCol.primero(lista));
            System.out.println(ListaCol.primeroPrioridad(lista));
            lista = ListaCol.resto(lista);
        }
    }
}

class ColaPrioridad{
    ListaCol listaPrioridad;
    boolean vacia;
    public static final ColaPrioridad nil = null;

    public static ColaPrioridad crear(){
        ColaPrioridad cola = new ColaPrioridad();
        cola.listaPrioridad = ListaCol.nil;
        cola.vacia = true;
        return cola;
    }

    public static boolean estaVacia(ColaPrioridad cola){
        return cola.vacia;
    }

    public static int obtenerMin(ColaPrioridad cola){
        if(!ColaPrioridad.estaVacia(cola))
            return ListaCol.primero(cola.listaPrioridad);
        else
            return 0;
    }

    public static void insertar(ColaPrioridad cola, int id, float w){
        if(!ListaCol.elementoEnLista(cola.listaPrioridad, id)){
            if(ColaPrioridad.estaVacia(cola))
                cola.vacia = false;
            cola.listaPrioridad = ListaCol.insertar1(id, w, cola.listaPrioridad);
        }
    }

    public static void borrarMin(ColaPrioridad cola){
        if(!ColaPrioridad.estaVacia(cola))
            cola.listaPrioridad = ListaCol.resto(cola.listaPrioridad);
        if(cola.listaPrioridad == ListaCol.nil)
            cola.vacia = true;
    }

    public static float obtenerPrioridad(ColaPrioridad cola, int id){
        if(ColaPrioridad.estaVacia(cola))
            return 0.0f;
        ListaCol lista = cola.listaPrioridad;
        while(lista != ListaCol.nil){
            if(id == ListaCol.primero(lista)){
                return ListaCol.primeroPrioridad(lista);
            }
            lista = ListaCol.resto(lista); 
        }
        return 0.0f;
    }

    public static void decrementarClave(ColaPrioridad cola, int id, float w){
        if(!ColaPrioridad.estaVacia(cola)){
            if(ListaCol.elementoEnLista(cola.listaPrioridad, id)){
                ListaCol acumulada = ListaCol.nil;
                ListaCol lista = cola.listaPrioridad;
                while(lista != ListaCol.nil){
                    if(id == ListaCol.primero(lista)){
                        lista.prioridad = w;
                    }
                    acumulada = ListaCol.insertar1(ListaCol.primero(lista), ListaCol.primeroPrioridad(lista), 
                        acumulada);
                    lista = ListaCol.resto(lista);
                }
                cola.listaPrioridad = acumulada;
            }
        }
    }
}

public class ColasPrioridad{
    public static void main(String[] args){
        ColaPrioridad cola = ColaPrioridad.crear();
        ColaPrioridad.insertar(cola, 3, 2.0f);
        ColaPrioridad.insertar(cola, 4, 1.0f);
        ColaPrioridad.insertar(cola, 2, 8.0f);
        ColaPrioridad.insertar(cola, 5, 1.4f);
        ColaPrioridad.decrementarClave(cola, 5, 0.7f);
        ListaCol.imprimirLista(cola.listaPrioridad);
    }
}