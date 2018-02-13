public enum Color{
    BLANCO(0), GRIS(1), NEGRO(2);

    private final Integer id;
    Color(int id) { this.id = id; }
    public int getValue() { return id; }
}

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

class Cola{

    ListaInt lista;
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

    public static int frente(Cola q){
        if(!Cola.estaVacia(q)){
            return ListaInt.primero(q.lista);
        }
        else
            return Cola.nil;
    }

    public static void encolar(Cola q, int e){
        if(Cola.estaVacia(q))
            q.vacia = false;
        q.lista = ListaInt.insertar1(e, q.lista); 
    }

    public static void desencolar(Cola q){
        if(!Cola.estaVacia(q)){
            q.lista = ListaInt.resto(q.lista);
        }
    }
}

public class PrimeroEnAmplitud{
    public static void main(String[] args){

    }

    public static void busquedaPrimeroEnAmplitud(ListaInt[] verticesAdya, int n, int s, int[] padre){
        int[] color = new int[n+1];
        Cola pendiente = Cola.crear();
        int i, v, w;
        for(i = 0; i < n; i++){
            color[i] = Color.BLANCO;
        }
        padre[s] = -1;
        color[s] = Color.GRIS;
        Cola.encolar(pendiente, s);
        while(pendiente != Cola.nil){
            v = Cola.frente(pendiente);
            Cola.desencolar(pendiente);
            for(i = 0; i < n; w++){
                ListaInt lista = verticesAdya[i];
                while(lista != ListaInt.nil){
                    w = ListaInt.primero(lista);
                    if(color[w] == Color.BLANCO){
                        color[w] = Color.GRIS;
                        Cola.encolar(pendiente, w);
                        padre[w] = v;
                    }
                    lista = ListaInt.resto(lista);
                }
            }
            color[v] = Color.NEGRO;
        }
        return;
    }
}