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

}

class Pila{

    boolean vacia;
    ListaObj lista;
    public static final Pila nil = null;
    
    public static Pila crear(){
        Pila pila = new Pila();
        pila.vacia = true;
        pila.lista = new ListaObj();
        return pila;
    }

    public static boolean estaVacia(Pila p){
        return p.vacia;
    }

    public static Object top(Pila p){
        if(!Pila.estaVacia(p))
            return ListaObj.primero(p.lista);
        else
            return ListaObj.nil;
    }

    public static void push(Pila p, Object e){
        if(Pila.estaVacia(p))
            p.vacia = false;
        p.lista = ListaObj.cons(e, p.lista);
    }

    public static void pop(Pila s){
        if(!Pila.estaVacia(s)){
            s.lista = ListaObj.resto(s.lista);
            if(s.lista == ListaObj.nil)
                s.vacia = true;   
        }
    }
}

public class Pilas{
    public static void main(String[] args){
        Pila pila = Pila.crear();
        Pila.push(pila, 1);
        Pila.push(pila, 3);
        Pila.push(pila, 2);
        Pila.pop(pila);
        Pila.pop(pila);
        System.out.println(Pila.top(pila));
    }
}